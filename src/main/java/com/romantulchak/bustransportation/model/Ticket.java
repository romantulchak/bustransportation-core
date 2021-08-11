package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @Email
    private String email;

    @ManyToOne
    private Seat seat;

    @ManyToOne
    private Booking booking;

    public Ticket(){

    }
    public Ticket(String firstName, String lastName, @Email String email, Seat seat, Booking booking) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.seat = seat;
        this.booking = booking;
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

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
