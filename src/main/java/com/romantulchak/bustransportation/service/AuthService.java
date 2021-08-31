package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.payload.request.LoginRequest;
import com.romantulchak.bustransportation.payload.request.SignupRequest;
import com.romantulchak.bustransportation.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    void registerUser(SignupRequest signUpRequest);

    void sendRegistrationConfirmationEmail(User user);

    boolean activateAccount(String token);
}
