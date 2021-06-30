package com.romantulchak.bustransportation.exception;

public class UsernameAlreadyTaken extends RuntimeException {
    public UsernameAlreadyTaken(String username) {
        super(String.format("Username %s already taken", username));
    }
}
