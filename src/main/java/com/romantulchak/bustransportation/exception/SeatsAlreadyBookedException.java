package com.romantulchak.bustransportation.exception;

public class SeatsAlreadyBookedException extends RuntimeException {
    public SeatsAlreadyBookedException(String seats) {
        super(String.format("Seats %s already booked", seats));
    }
}
