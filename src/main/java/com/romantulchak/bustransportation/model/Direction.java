package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public String directionFrom;

    public String directionTo;

    public String direction;

    public int distance;

    public int maxSeats;

    @OneToMany(mappedBy = "direction", cascade = CascadeType.ALL)
    public List<Seat> seats;

    @ManyToOne
    public Bus bus;

    public String getDirection() {
        return directionFrom + " - " + directionTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDirectionFrom() {
        return directionFrom;
    }

    public void setDirectionFrom(String directionFrom) {
        this.directionFrom = directionFrom;
    }

    public String getDirectionTo() {
        return directionTo;
    }

    public void setDirectionTo(String directionTo) {
        this.directionTo = directionTo;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
