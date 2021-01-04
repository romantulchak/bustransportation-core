package com.romantulchak.bustransportation.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonView(View.TripView.class)
    private String busName;
    @JsonView(View.TripView.class)
    private String busBrand;

    private int numberOfSeats;

    @OneToMany(mappedBy = "bus")
    private List<Trip> trips;

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setBusName(String busName) {
        this.busName = busName;
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

    public String getBusBrand() {
        return busBrand;
    }

    public void setBusBrand(String busBrand) {
        this.busBrand = busBrand;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
