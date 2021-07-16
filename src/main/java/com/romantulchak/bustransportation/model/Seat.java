package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int seatNumber;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Trip trip;

    @OneToMany(mappedBy = "seat", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Seat(){

    }
    public Seat(int numberOfSeat, Trip trip){
        this.seatNumber = numberOfSeat;
        this.trip = trip;
    }

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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}
