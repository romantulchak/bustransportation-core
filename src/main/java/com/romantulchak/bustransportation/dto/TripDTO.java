package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class TripDTO {
    @JsonView(View.TripView.class)
    private long id;
    @JsonView(View.TripView.class)
    private LocalDateTime date;

    @JsonView(View.TripView.class)
    private Direction direction;
    @JsonView(View.TripView.class)
    private Bus bus;
    @JsonView(View.TripView.class)
    private int numberOfSeats;
    @JsonView(View.TripView.class)
    private int price;

    @JsonView(View.TripView.class)
    private List<Seat> seats;

    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.date = trip.getDate();
        this.direction = trip.getDirection();
        this.bus = trip.getBus();
        this.numberOfSeats = trip.getNumberOfSeats();
        this.seats = trip.getSeats();
        this.date = trip.getDate();
        this.price = trip.getPrice();
    }

    public TripDTO(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return date;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }
}

