package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//TODO: Add checks if current bus has enough seats for trip,
public class TripDTO {
    @JsonView(View.TripView.class)
    private long id;

    @JsonView(View.TripView.class)
    private LocalDateTime date;

    @JsonView(View.TripView.class)
    private Direction direction;

    @JsonView(View.TripView.class)
    private BusDTO bus;

    @JsonView(View.TripView.class)
    private int numberOfSeats;

    @JsonView(View.TripView.class)
    private int price;

    @JsonView(View.TripView.class)
    private List<SeatDTO> seats = new ArrayList<>();

    @JsonView(View.TripView.class)
    private List<IntermediatePlaces> intermediatePlaces;

    public TripDTO(){

    }

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

    public BusDTO getBus() {
        return bus;
    }

    public void setBus(BusDTO bus) {
        this.bus = bus;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
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

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    public List<IntermediatePlaces> getIntermediatePlaces() {
        return intermediatePlaces;
    }

    public void setIntermediatePlaces(List<IntermediatePlaces> intermediatePlaces) {
        this.intermediatePlaces = intermediatePlaces;
    }
}

