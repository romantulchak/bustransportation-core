package com.romantulchak.bustransportation.exception;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(){
        super("City not found exception");
    }
}
