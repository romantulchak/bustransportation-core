package com.romantulchak.bustransportation.exception;

public class DirectionNotFoundException extends RuntimeException{
    public DirectionNotFoundException(){
        super("Direction not found");
    }
}
