package com.romantulchak.bustransportation.model;

import com.romantulchak.bustransportation.validator.constraint.DateFormatConstraint;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Departure from is empty")
    private String departureFrom;

    @NotBlank(message = "Arrival from is empty")
    private String arrivalTo;

    @DateFormatConstraint
    private LocalDateTime departureTime;

    @DateFormatConstraint
    private LocalDateTime arrivalTime;

    private int price;

    @ManyToOne
    private Trip trip;

    private int entranceStop;

    private int exitStop;

    public Route(){}

    public Route(Builder builder){
        this.departureFrom = builder.departureFrom;
        this.arrivalTo = builder.arrivalTo;
        this.departureTime = builder.departureTime;
        this.arrivalTime = builder.arrivalTime;
        this.price = builder.price;
        this.trip = builder.trip;
        this.entranceStop = builder.entranceStop;
        this.exitStop = builder.exitStop;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getArrivalTo() {
        return arrivalTo;
    }

    public void setArrivalTo(String arrivalTo) {
        this.arrivalTo = arrivalTo;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getEntranceStop() {
        return entranceStop;
    }

    public void setEntranceStop(int entranceStop) {
        this.entranceStop = entranceStop;
    }

    public int getExitStop() {
        return exitStop;
    }

    public void setExitStop(int exitStop) {
        this.exitStop = exitStop;
    }

    public static class Builder{

        private final String departureFrom;

        private final String arrivalTo;

        private final LocalDateTime departureTime;

        private final LocalDateTime arrivalTime;

        private int price;

        private Trip trip;

        private int entranceStop;

        private int exitStop;

        public Builder(String departureFrom, String arrivalTo, LocalDateTime departureTime, LocalDateTime arrivalTime){
            this.departureFrom = departureFrom;
            this.arrivalTo = arrivalTo;
            this.departureTime = departureTime;
            this.arrivalTime = arrivalTime;
        }

        public Builder withPrice(int price){
            this.price = price;
            return this;
        }

        public Builder withTrip(Trip trip){
            this.trip = trip;
            return this;
        }

        public Builder withEntranceStop(int entranceStop){
            this.entranceStop = entranceStop;
            return this;
        }

        public Builder withExitStop(int exitStop){
            this.exitStop = exitStop;
            return this;
        }

        public Route build(){
            return new Route(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return Objects.equals(departureFrom, route.departureFrom) && Objects.equals(arrivalTo, route.arrivalTo) && Objects.equals(departureTime, route.departureTime) && Objects.equals(arrivalTime, route.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departureFrom, arrivalTo, departureTime, arrivalTime);
    }
}
