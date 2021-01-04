package com.romantulchak.bustransportation.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.View;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class DirectionDTO {
    @JsonView(View.DirectionView.class)
    private long id;

    @JsonView(View.DirectionView.class)
    public String directionFrom;

    @JsonView(View.DirectionView.class)
    public String directionTo;

    @JsonView(View.DirectionView.class)
    public String direction;

    @JsonView(View.DirectionView.class)
    public int distance;


    public DirectionDTO(Direction direction){
        this.id = direction.getId();
        this.directionFrom = direction.getDirectionFrom();
        this.directionTo = direction.getDirectionTo();
        this.direction = direction.getDirection();
        this.distance = direction.getDistance();
    }
    public DirectionDTO(Direction direction, int maxSeats){
        this.id = direction.getId();
        this.directionFrom = direction.getDirectionFrom();
        this.directionTo = direction.getDirectionTo();
        this.direction = direction.getDirection();
        this.distance = direction.getDistance();
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

    public String getDirection() {
        return direction;
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
