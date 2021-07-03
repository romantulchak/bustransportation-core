package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.model.View;
import org.springframework.util.ClassUtils;

import java.util.List;

public class BusDTO {

    @JsonView({View.BusView.class, View.TripView.class})
    private long id;

    @JsonView({View.BusView.class, View.TripView.class})
    private String name;

    @JsonView({View.BusView.class, View.TripView.class})
    private String brand;

    @JsonView(View.BusView.class)
    private int numberOfSeats;

    @JsonView(View.BusView.class)
    private List<TripDTO> trips;

    @JsonView(View.BusView.class)
    private User user;

    public BusDTO(){
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String carBrand) {
        this.brand = carBrand;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public void setTrips(List<TripDTO> trips) {
        this.trips = trips;
    }

    public List<TripDTO> getTrips() {
        return trips;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
