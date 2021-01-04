package com.romantulchak.bustransportation.exception;

public class TripNotFoundException extends RuntimeException{
    public TripNotFoundException(){
        super("Trip not found");
    }
}
