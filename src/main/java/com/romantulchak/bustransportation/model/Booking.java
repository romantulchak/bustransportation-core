package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private City city;

    @ManyToOne
    @Valid
    private User user;

    @OneToMany(mappedBy = "booking", fetch = FetchType.EAGER, orphanRemoval = true)
    public List<Ticket> tickets = new ArrayList<>();

    private int totalNumberOfSeats;

    public Booking(){

    }

    public Booking(City city, User user, int totalNumberOfSeats) {
        this.city = city;
        this.user = user;
        this.totalNumberOfSeats = totalNumberOfSeats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getTotalNumberOfSeats() {
        return totalNumberOfSeats;
    }

    public void setTotalNumberOfSeats(int totalNumberOfSeats) {
        this.totalNumberOfSeats = totalNumberOfSeats;
    }
}
