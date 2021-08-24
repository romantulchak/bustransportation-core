package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.View;

import java.util.List;

@DTO
public class BookingDTO {
    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class, View.BookingView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class, View.BookingView.class})
    private long id;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class, View.BookingView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class, View.BookingView.class})
    private UserDTO user;

    @MapToDTO(mapClass = {View.TripView.class, View.SeatTripView.class, View.BookingView.class})
    @JsonView({View.TripView.class,View.SeatTripView.class, View.BookingView.class})
    private List<TicketDTO> tickets;

    private int totalNumberOfSeats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<TicketDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDTO> tickets) {
        this.tickets = tickets;
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public void setTotalNumberOfSeats(int totalNumberOfSeats) {
        this.totalNumberOfSeats = totalNumberOfSeats;
    }
}
