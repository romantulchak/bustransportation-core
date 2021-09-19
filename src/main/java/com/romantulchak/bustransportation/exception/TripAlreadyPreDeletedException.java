package com.romantulchak.bustransportation.exception;

public class TripAlreadyPreDeletedException extends RuntimeException {
    public TripAlreadyPreDeletedException(long id, String name) {
        super(String.format("Trip id: %s name: %s already in pre delete state", id, name));
    }
}
