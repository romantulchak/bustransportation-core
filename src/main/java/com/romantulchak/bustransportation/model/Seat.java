package com.romantulchak.bustransportation.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.TripView.class)
    private long id;
    @JsonView(View.TripView.class)
    private int seatNumber;

    @OneToOne(mappedBy = "seat", cascade = CascadeType.ALL)
    @JsonView(View.TripView.class)
    private User user;

    @ManyToOne
    private Trip trip;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
