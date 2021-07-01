package com.romantulchak.bustransportation.exception;

public class EmailAlreadyTakenException extends RuntimeException {
    public EmailAlreadyTakenException(String email) {
        super(String.format("Email %s already taken", email));
    }
}
