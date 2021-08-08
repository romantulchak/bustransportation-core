package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.View;

@DTO
public class BookingDTO {
    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private CityDTO city;

    private SeatDTO seat;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private String firstName;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private String lastName;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
