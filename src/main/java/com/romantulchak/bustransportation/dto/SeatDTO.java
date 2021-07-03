package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.model.View;

import javax.persistence.*;

public class SeatDTO {

    @JsonView({View.TripView.class, View.SeatTripView.class})
    private long id;

    @JsonView({View.TripView.class,View.SeatTripView.class})
    private int seatNumber;

    @JsonView({View.TripView.class,View.SeatTripView.class})
    private User user;

    @JsonView(View.SeatTripView.class)
    private Trip trip;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
