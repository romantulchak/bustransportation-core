package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.model.View;

public class UserDTO {
    @MapToDTO
    @JsonView({View.TripView.class, View.BusView.class})
    private long id;

    @MapToDTO
    @JsonView({View.TripView.class, View.BusView.class})
    private String firstName;

    @MapToDTO
    @JsonView({View.TripView.class, View.BusView.class})
    private String lastName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
