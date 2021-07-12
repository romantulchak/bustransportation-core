package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.model.View;
import org.springframework.util.ClassUtils;

import java.util.List;

public class BusDTO {

    @MapToDTO(mapClass = {View.BusView.class, View.TripView.class})
    @JsonView({View.BusView.class, View.TripView.class})
    private long id;

    @MapToDTO(mapClass = {View.BusView.class, View.TripView.class})
    @JsonView({View.BusView.class, View.TripView.class})
    private String name;

    @MapToDTO(mapClass = {View.BusView.class, View.TripView.class})
    @JsonView({View.BusView.class, View.TripView.class})
    private String brand;

    @MapToDTO(mapClass =View.BusView.class)
    @JsonView(View.BusView.class)
    private int numberOfSeats;

    @MapToDTO(mapClass = View.BusView.class)
    @JsonView(View.BusView.class)
    private UserDTO user;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
