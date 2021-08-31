package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.exception.EmailAlreadyTakenException;
import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.exception.UsernameAlreadyTakenException;
import com.romantulchak.bustransportation.model.ActivateToken;
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
import com.romantulchak.bustransportation.service.EmailService;
import com.romantulchak.bustransportation.utils.email.AccountVerificationEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
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

    private final EmailService emailService;

    private final ActivateTokenServiceImpl activateTokenService;

    @Value("${site.base.url.https}")
    private String baseUrl;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder encoder,
                           EmailService emailService,
                           ActivateTokenServiceImpl activateTokenService,
                           JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.emailService = emailService;
        this.jwtUtils = jwtUtils;
        this.activateTokenService = activateTokenService;
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

    @Transactional
    @Override
    public void registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameAlreadyTakenException(signUpRequest.getUsername());
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyTakenException(signUpRequest.getEmail());
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findRoleByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);

            user.setRoles(roles);
            user = userRepository.save(user);
            sendRegistrationConfirmationEmail(user);
        }
    }

    @Override
    public void sendRegistrationConfirmationEmail(User user) {
        ActivateToken activateToken = activateTokenService.createActivateToken(user);
        activateTokenService.saveActiveToken(activateToken);
        AccountVerificationEmail accountVerificationEmail = new AccountVerificationEmail();
        accountVerificationEmail.init(user, activateToken.getToken());
        accountVerificationEmail.buildVerificationUrl(baseUrl, activateToken.getToken());
        try {
            emailService.sendEmail(accountVerificationEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean activateAccount(String token) {
        ActivateToken activateToken = activateTokenService.findActivateToken(token);
        User user = userRepository.findById(activateToken.getUser().getId()).orElseThrow(UserNotFoundException::new);
        user.setEnabled(true);
        userRepository.save(user);
        activateTokenService.removeToken(activateToken);
        return true;
    }

}
