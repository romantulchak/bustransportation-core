package com.romantulchak.bustransportation.model;

import com.romantulchak.bustransportation.model.enums.TripType;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private Bus bus;

    private LocalDateTime dateStart;

    private int numberOfSeats;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("seatNumber asc ")
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "trip")
    private List<TripTemplate> tripTemplates;

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    @ManyToOne
    private User creator;

    @ElementCollection(targetClass = CityStop.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CityStop> stops;

    @ElementCollection(targetClass = Route.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Route> routes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public List<CityStop> getStops() {
        return stops;
    }

    public void setStops(List<CityStop> stops) {
        this.stops = stops;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
