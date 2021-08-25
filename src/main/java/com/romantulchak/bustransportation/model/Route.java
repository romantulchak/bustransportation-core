package com.romantulchak.bustransportation.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.MapToDTO;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Route {

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private String departureFrom;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private String arrivalTo;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private LocalDateTime departureTime;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private LocalDateTime arrivalTime;

    public Route(){}

    public Route(String departureFrom, String arrivalTo, LocalDateTime departureTime, LocalDateTime arrivalTime){
        this.departureFrom = departureFrom;
        this.arrivalTo = arrivalTo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
