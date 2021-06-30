package com.romantulchak.bustransportation.exception;

public class EmailAlreadyTaken extends RuntimeException {
    public EmailAlreadyTaken(String email) {
        super(String.format("Email %s already taken", email));
    }
}
