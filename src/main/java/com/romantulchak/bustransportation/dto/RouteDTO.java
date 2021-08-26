package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.View;

import java.time.LocalDate;

@DTO
public class RouteDTO {
    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private long id;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private String departureFrom;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private String arrivalTo;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private LocalDate departureTime;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private LocalDate arrivalTime;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private int price;

    private TripDTO tripDTO;

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

    public LocalDate getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDate departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDate arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TripDTO getTripDTO() {
        return tripDTO;
    }

    public void setTripDTO(TripDTO tripDTO) {
        this.tripDTO = tripDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
