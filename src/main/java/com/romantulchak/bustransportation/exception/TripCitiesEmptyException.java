package com.romantulchak.bustransportation.exception;

public class TripCitiesEmptyException extends RuntimeException {
    public TripCitiesEmptyException(){
        super("Trip cities are empty");
    }
}
