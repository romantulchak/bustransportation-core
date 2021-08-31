package com.romantulchak.bustransportation.exception;

public class UserTokenNotFoundException extends RuntimeException{
    public UserTokenNotFoundException(String token){
        super(String.format("Token %s is invalid", token));
    }
}
