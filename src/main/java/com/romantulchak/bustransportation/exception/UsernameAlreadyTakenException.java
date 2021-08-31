package com.romantulchak.bustransportation.exception;

public class UsernameAlreadyTakenException extends RuntimeException {
    public UsernameAlreadyTakenException(String username) {
        super(String.format("Username %s already taken", username));
    }
}
