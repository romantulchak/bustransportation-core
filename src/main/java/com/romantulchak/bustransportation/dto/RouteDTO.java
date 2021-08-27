package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.View;

import java.time.LocalDateTime;

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
    private LocalDateTime departureTime;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private LocalDateTime arrivalTime;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private int price;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private int entranceStop;

    @MapToDTO(mapClass = View.RouteView.class)
    @JsonView(View.RouteView.class)
    private int exitStop;


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

    public int getEntranceStop() {
        return entranceStop;
    }

    public void setEntranceStop(int entranceStop) {
        this.entranceStop = entranceStop;
    }

    public int getExitStop() {
        return exitStop;
    }

    public void setExitStop(int exitStop) {
        this.exitStop = exitStop;
    }
}
