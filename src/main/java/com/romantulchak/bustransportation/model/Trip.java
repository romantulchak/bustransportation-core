package com.romantulchak.bustransportation.model;

import com.ecfinder.core.anotation.ECFEntity;
import com.romantulchak.bustransportation.model.enums.TripType;
import com.romantulchak.bustransportation.validator.constraint.BusConstraint;
import com.romantulchak.bustransportation.validator.constraint.CityStopConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ECFEntity(tablePrefix = "trip")
@Entity
public class Trip implements Cloneable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Trip name cannot be empty")
    private String name;

    @BusConstraint
    @ManyToOne
    private Bus bus;

    private int numberOfSeats;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("seatNumber asc ")
    private List<Seat> seats = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    @ManyToOne
    private User creator;

    @CityStopConstraint
    @ElementCollection(targetClass = CityStop.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<@Valid CityStop> stops;

    @OneToMany(mappedBy = "trip", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Route> routes;

    private LocalDate dateStart;

    private LocalDate dateEnded;

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

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(LocalDate dateEnded) {
        this.dateEnded = dateEnded;
    }

    @Override
    public Object clone() {
        Trip trip = null;
        try {
            trip = (Trip) super.clone();
        }catch (CloneNotSupportedException ex){
            ex.printStackTrace();
        }
        return trip;
    }
}
