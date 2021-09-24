package com.romantulchak.bustransportation.exception;

public class TripNotInPreDeletedState extends RuntimeException {
    public TripNotInPreDeletedState(String name) {
        super(String.format("Trip %s not in pre-deleted state", name));
    }
}
