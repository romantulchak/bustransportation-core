package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.model.enums.TripType;

import java.util.List;

@DTO
public class TripTemplateDTO {

    @MapToDTO(mapClass = {View.TripTemplateView.class})
    @JsonView(View.TripTemplateView.class)
    private long id;

    @MapToDTO(mapClass = {View.TripTemplateView.class})
    @JsonView(View.TripTemplateView.class)
    private String name;

    @MapToDTO(mapClass = {View.TripTemplateView.class})
    @JsonView(View.TripTemplateView.class)
    private BusDTO bus;

    private UserDTO user;

    @MapToDTO(mapClass = {View.TripTemplateStopsView.class})
    @JsonView(View.TripTemplateStopsView.class)
    private List<CityStop> stops;

    @MapToDTO(mapClass = {View.TripTemplateView.class})
    @JsonView(View.TripTemplateView.class)
    private TripType tripType;


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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<CityStop> getStops() {
        return stops;
    }

    public void setStops(List<CityStop> stops) {
        this.stops = stops;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
