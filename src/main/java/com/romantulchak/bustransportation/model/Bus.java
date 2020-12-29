package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String busName;

    private String carBrand;

    private int numberOfSeats;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Direction> directions;

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public long getId() {
        return id;
    }

    public String getBusName() {
        return busName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

}
