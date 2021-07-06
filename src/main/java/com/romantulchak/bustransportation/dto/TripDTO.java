package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.model.*;
import com.romantulchak.bustransportation.model.enums.TripType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//TODO: Add checks if current bus has enough seats for trip,
public class TripDTO {
    @MapToDTO
    @JsonView(View.TripView.class)
    private long id;

    @MapToDTO
    @JsonView(View.TripView.class)
    private BusDTO bus;

    @MapToDTO
    @JsonView(View.TripView.class)
    private String name;

    @MapToDTO
    @JsonView(View.TripView.class)
    private LocalDateTime dateStart;

    @MapToDTO
    @JsonView(View.TripView.class)
    private LocalDateTime dateEnd;

    @MapToDTO
    @JsonView(View.TripView.class)
    private int numberOfSeats;

    @JsonView(View.TripView.class)
    private List<SeatDTO> seats = new ArrayList<>();

    @JsonView(View.TripView.class)
    private TripType tripType;

    @MapToDTO
    @JsonView(View.TripView.class)
    private UserDTO creator;

    @MapToDTO
    @JsonView(View.TripView.class)
    private List<CityDTO> cities;

    @MapToDTO
    @JsonView(View.TripView.class)
    private String departureCity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public List<CityDTO> getCities() {
        return cities;
    }

    public void setCities(List<CityDTO> cities) {
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
}

