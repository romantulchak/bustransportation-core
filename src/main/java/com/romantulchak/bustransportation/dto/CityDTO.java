package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.Ticket;
import com.romantulchak.bustransportation.model.View;

import java.time.LocalDateTime;
import java.util.List;

@DTO
public class CityDTO {
    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private Direction direction;

    @MapToDTO(mapClass = {View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private long price;

    private TripDTO trip;

    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private LocalDateTime dateOfDeparture;

    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private LocalDateTime dateOfArrival;

    @MapToDTO(mapClass = {View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private boolean isBusStop;

    @MapToDTO(mapClass = {View.BookingView.class})
    @JsonView({View.TripView.class, View.BookingView.class})
    private String street;

    //    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
//    @JsonView({View.TripView.class, View.BookingView.class})
    private List<TicketDTO> tickets;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public TripDTO getTrip() {
        return trip;
    }

    public void setTrip(TripDTO trip) {
        this.trip = trip;
    }

    public LocalDateTime getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDateTime dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalDateTime getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(LocalDateTime dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
    }

    public boolean isBusStop() {
        return isBusStop;
    }

    public void setBusStop(boolean busStop) {
        isBusStop = busStop;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }
}
