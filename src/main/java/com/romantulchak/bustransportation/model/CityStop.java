package com.romantulchak.bustransportation.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.MapToDTO;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class CityStop {

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private String name;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private LocalDateTime departure;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private boolean isBusStop;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private String street;

    @MapToDTO(mapClass = View.TripView.class)
    @JsonView(View.TripView.class)
    private int price;

    public CityStop(){

    }

    public CityStop(String name, LocalDateTime departure, boolean isBusStop) {
        this.name = name;
        this.departure = departure;
        this.isBusStop = isBusStop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getArrival() {
        return departure;
    }

    public void setArrival(LocalDateTime departure) {
        this.departure = departure;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public boolean isBusStop() {
        return isBusStop;
    }

    public void setBusStop(boolean busStop) {
        isBusStop = busStop;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityStop)) return false;
        CityStop cityStop = (CityStop) o;
        return Objects.equals(name, cityStop.name) && Objects.equals(departure, cityStop.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, departure);
    }
}