package com.romantulchak.bustransportation.exception;

public class RouteNotFoundException extends RuntimeException{
    public RouteNotFoundException(){
        super("City not found exception");
    }
}
