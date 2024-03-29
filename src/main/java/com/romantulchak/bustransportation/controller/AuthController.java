package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.payload.request.LoginRequest;
import com.romantulchak.bustransportation.payload.request.SignupRequest;
import com.romantulchak.bustransportation.payload.response.JwtResponse;
import com.romantulchak.bustransportation.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public JwtResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody SignupRequest signupRequest){
        authService.registerUser(signupRequest);
    }

    @GetMapping("/verify/{token}")
    public boolean activateAccount(@PathVariable("token") String token){
        return authService.activateAccount(token);
    }

    @GetMapping("/re-activate-account/{username}")
    public void reSendActivationEmail(@PathVariable("username") String username){
        authService.reSendActivationLink(username);
    }
}
