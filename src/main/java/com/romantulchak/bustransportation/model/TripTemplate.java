package com.romantulchak.bustransportation.model;

import com.ecfinder.core.anotation.ECFEntity;
import com.romantulchak.bustransportation.model.enums.TripType;
import com.romantulchak.bustransportation.validator.constraint.BusConstraint;
import com.romantulchak.bustransportation.validator.constraint.CityStopConstraint;

import javax.persistence.*;
import java.util.List;

@ECFEntity(tablePrefix = "trip_template")
@Entity
public class TripTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @BusConstraint
    @ManyToOne
    private Bus bus;

    @ManyToOne
    private User user;

    @CityStopConstraint
    @ElementCollection
    private List<CityStop> stops;

    @Enumerated(EnumType.STRING)
    private TripType tripType;

    public TripTemplate(){}

    public TripTemplate(long id, List<CityStop> stops){
        this.id = id;
        this.stops = stops;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CityStop> getStops() {
        return stops;
    }

    public void setStops(List<CityStop> stops) {
        this.stops = stops;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
