package com.romantulchak.bustransportation.model;

import com.romantulchak.bustransportation.model.enums.TripType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDateTime date;

    @Embedded
    private Direction direction;

    @ManyToOne
    private Bus bus;

    private int numberOfSeats;

    @OneToMany(mappedBy = "trip")
    @OrderBy("seatNumber asc ")
    private List<Seat> seats = new ArrayList<>();

    @ElementCollection
    private List<IntermediatePlaces> intermediatePlaces;

    private int price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trip")
    private List<TripTemplate> tripTemplates;

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    @ManyToOne
    private User creator;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime localDateTime) {
        this.date = localDateTime;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<IntermediatePlaces> getIntermediatePlaces() {
        return intermediatePlaces;
    }

    public void setIntermediatePlaces(List<IntermediatePlaces> intermediatePlaces) {
        this.intermediatePlaces = intermediatePlaces;
    }

    public List<TripTemplate> getTripTemplates() {
        return tripTemplates;
    }

    public void setTripTemplates(List<TripTemplate> tripTemplates) {
        this.tripTemplates = tripTemplates;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
