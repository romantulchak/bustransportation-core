package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.*;
import com.romantulchak.bustransportation.model.enums.TripType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DTO
public class TripDTO {
    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private long id;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private BusDTO bus;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private String name;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private LocalDateTime dateStart;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private int numberOfSeats;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private List<SeatDTO> seats = new ArrayList<>();

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private TripType tripType;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private UserDTO creator;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private List<CityStop> stops;

    private List<Route> routes;

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

    public List<CityStop> getStops() {
        return stops;
    }

    public void setStops(List<CityStop> stops) {
        this.stops = stops;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}

