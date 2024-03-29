package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;

import java.util.List;

@DTO
public class SeatDTO {

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class, View.RouteView.class})
    @JsonView({View.TripView.class, View.SeatTripView.class, View.RouteView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class, View.RouteView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class, View.RouteView.class})
    private int seatNumber;

    @JsonView(View.SeatTripView.class)
    private Trip trip;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class, View.RouteView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class, View.RouteView.class})
    private List<TicketDTO> tickets;

    @JsonView(View.RouteView.class)
    private boolean isBooked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
