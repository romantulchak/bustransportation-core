package com.romantulchak.bustransportation.exception;

import com.romantulchak.bustransportation.model.Seat;

import java.util.List;

public class OccupiedSeatException extends RuntimeException{

    private List<Seat> seats;
    public OccupiedSeatException(List<Seat> seats){
        this.seats = seats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
