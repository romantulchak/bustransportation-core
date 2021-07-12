package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;

public class SeatDTO {

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class, View.SeatTripView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private int seatNumber;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private UserDTO user;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
