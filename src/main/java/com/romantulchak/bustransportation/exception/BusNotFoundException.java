package com.romantulchak.bustransportation.exception;

public class BusNotFoundException extends RuntimeException{
    public BusNotFoundException(){
        super("Bus not found");
    }
}
