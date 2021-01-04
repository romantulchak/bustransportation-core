package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;

import javax.persistence.*;
import java.util.List;

public class BusDTO {

    @JsonView(View.BusView.class)
    private long id;

    @JsonView(View.BusView.class)
    private String busName;

    @JsonView(View.BusView.class)
    private String busBrand;

    @JsonView(View.BusView.class)
    private int numberOfSeats;
    @JsonView(View.BusView.class)
    private List<Trip> trips;

    public BusDTO(){

    }
    public BusDTO(Bus bus) {
        this.id = bus.getId();
        this.busName = bus.getBusName();
        this.busBrand = bus.getBusBrand();
        this.numberOfSeats = bus.getNumberOfSeats();
        this.trips = bus.getTrips();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusBrand() {
        return busBrand;
    }

    public void setBusBrand(String carBrand) {
        this.busBrand = carBrand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> getTrips() {
        return trips;
    }
}
