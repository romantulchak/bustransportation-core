package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.View;

import javax.persistence.ManyToOne;

@DTO
public class TicketDTO {
    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView(View.TripView.class)
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView(View.TripView.class)
    private String firstName;

    @MapToDTO(mapClass = {View.TripView.class,View.BookingView.class})
    @JsonView(View.TripView.class)
    private String lastName;

    @MapToDTO(mapClass = {View.TripView.class, View.BookingView.class})
    @JsonView(View.TripView.class)
    private String email;

    private SeatDTO seat;

    private BookingDTO booking;

    private RouteDTO route;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SeatDTO getSeat() {
        return seat;
    }

    public void setSeat(SeatDTO seat) {
        this.seat = seat;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }

    public RouteDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDTO route) {
        this.route = route;
    }
}
