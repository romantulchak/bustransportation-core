package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.View;

@DTO
public class UserDTO {
    @MapToDTO(mapClass = {View.TripView.class, View.BusView.class, View.TripTemplateView.class})
    @JsonView({View.TripView.class, View.BusView.class, View.TripTemplateView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.BusView.class, View.TripTemplateView.class})
    @JsonView({View.TripView.class, View.BusView.class, View.TripTemplateView.class})
    private String firstName;

    @MapToDTO(mapClass = {View.TripView.class, View.BusView.class, View.TripTemplateView.class})
    @JsonView({View.TripView.class, View.BusView.class, View.TripTemplateView.class})
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
