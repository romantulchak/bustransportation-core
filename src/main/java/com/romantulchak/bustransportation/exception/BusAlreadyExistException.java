package com.romantulchak.bustransportation.exception;

public class BusAlreadyExistException extends RuntimeException{
    public BusAlreadyExistException(String busName){
        super(String.format("Bus with name: %s already exist", busName));
    }
}
