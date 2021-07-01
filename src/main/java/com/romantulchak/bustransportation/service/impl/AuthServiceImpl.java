package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.exception.EmailAlreadyTakenException;
import com.romantulchak.bustransportation.exception.UsernameAlreadyTaken;
import com.romantulchak.bustransportation.model.Role;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.model.enums.ERole;
import com.romantulchak.bustransportation.payload.request.LoginRequest;
import com.romantulchak.bustransportation.payload.request.SignupRequest;
import com.romantulchak.bustransportation.payload.response.JwtResponse;
import com.romantulchak.bustransportation.repository.RoleRepository;
import com.romantulchak.bustransportation.repository.UserRepository;
import com.romantulchak.bustransportation.security.JwtUtils;
import com.romantulchak.bustransportation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }

    @Override
    public void registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyTaken(signUpRequest.getUsername());
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyTakenException(signUpRequest.getEmail());
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            user.setRoles(roles);
            userRepository.save(user);

        }
    }
}
