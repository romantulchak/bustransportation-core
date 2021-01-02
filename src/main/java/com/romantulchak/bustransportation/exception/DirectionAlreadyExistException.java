package com.romantulchak.bustransportation.exception;

public class DirectionAlreadyExistException extends RuntimeException {
    public DirectionAlreadyExistException(String direction){
        super(String.format("Direction: %s already exist", direction));
    }
}
