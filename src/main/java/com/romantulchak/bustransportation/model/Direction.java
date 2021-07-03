package com.romantulchak.bustransportation.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Embeddable
public class Direction {

    @JsonView(View.TripView.class)
    private String directionFrom;

    @JsonView(View.TripView.class)
    private String directionTo;

    @JsonView(View.TripView.class)
    private String direction;

    @JsonView(View.TripView.class)
    private int distance;

    public String getDirection() {
        return direction;
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
