package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.View;

import javax.persistence.*;
import java.util.List;

public class BusDTO {

    @JsonView(View.BusView.class)
    private long id;

    @JsonView(View.BusView.class)
    private String busName;

    @JsonView(View.BusView.class)
    private String carBrand;

    @JsonView(View.BusView.class)
    private int numberOfSeats;

    private List<Direction> directions;

    public BusDTO(){

    }
    public BusDTO(Bus bus) {
        this.id = bus.getId();
        this.busName = bus.getBusName();
        this.carBrand = bus.getCarBrand();
        this.numberOfSeats = bus.getNumberOfSeats();
        this.directions = bus.getDirections();
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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }
}
