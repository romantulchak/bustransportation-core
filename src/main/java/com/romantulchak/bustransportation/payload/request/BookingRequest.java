package com.romantulchak.bustransportation.payload.request;

import com.romantulchak.bustransportation.model.Seat;

import javax.validation.constraints.Email;

public class BookingRequest {

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private Seat seat;


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
}
