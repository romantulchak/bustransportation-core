package com.romantulchak.bustransportation.exception;

public class CreateClassInstanceException extends RuntimeException{

    public CreateClassInstanceException(String className){
        super(String.format("Cannot create new instance of class %s", className));
    }
}
