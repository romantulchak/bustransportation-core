package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.anotations.DTO;
import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.model.Booking;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;

import java.util.List;

public class SeatDTO {

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class, View.SeatTripView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private int seatNumber;

    @JsonView(View.SeatTripView.class)
    private Trip trip;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class})
    private List<BookingDTO> bookings;

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

    public List<BookingDTO> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingDTO> bookings) {
        this.bookings = bookings;
    }
}
