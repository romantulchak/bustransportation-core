package com.romantulchak.bustransportation.exception;

public class UserTokenExpiredException extends RuntimeException {
    public UserTokenExpiredException(String token) {
        super(String.format("User token: %s is expired", token));
    }
}
