package com.romantulchak.bustransportation.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

@Entity
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(View.BusView.class)
    private long id;
    @JsonView(View.BusView.class)
    private String directionFrom;
    @JsonView(View.BusView.class)
    private String directionTo;
    @JsonView({View.TripView.class})
    private String direction;
    @JsonView(View.BusView.class)
    private int distance;

    public String getDirection() {
        return direction;
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

}
