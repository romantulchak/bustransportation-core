package com.romantulchak.bustransportation.exception;

public class TripTemplateNotFoundException extends RuntimeException{
    public TripTemplateNotFoundException(long id){
        super(String.format("Trip template with id %s not found", id));
    }
}
