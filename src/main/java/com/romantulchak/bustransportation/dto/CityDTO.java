package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;

import java.time.LocalDateTime;

public class CityDTO {
    @MapToDTO
    @JsonView(View.TripView.class)
    private long id;

    @MapToDTO
    @JsonView(View.TripView.class)
    private Direction direction;

    @MapToDTO
    @JsonView(View.TripView.class)
    private long price;

    private Trip trip;

    @MapToDTO
    @JsonView(View.TripView.class)
    private LocalDateTime dateOfDeparture;

    @MapToDTO
    @JsonView(View.TripView.class)
    private LocalDateTime dateOfArrival;

    @MapToDTO
    @JsonView(View.TripView.class)
    private boolean isBusStop;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public LocalDateTime getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDateTime dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalDateTime getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDateTime dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public boolean isBusStop() {
        return isBusStop;
    }

    public void setBusStop(boolean busStop) {
        isBusStop = busStop;
    }
}
