package com.romantulchak.bustransportation.model;

import com.ecfinder.core.anotation.ECF;
import com.ecfinder.core.anotation.ECFUnique;
import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.validator.constraint.DateFormatConstraint;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@DTO
@ECF(tableName = "stops")
@Embeddable
public class CityStop {

    @NotBlank(message = "City name cannot be null")
    @MapToDTO(mapClass = {View.TripView.class, View.TripTemplateStopsView.class})
    @JsonView({View.TripView.class, View.TripTemplateStopsView.class})
    @ECFUnique
    private String name;

    @DateFormatConstraint
    @MapToDTO(mapClass = {View.TripView.class, View.TripTemplateStopsView.class})
    @JsonView({View.TripView.class, View.TripTemplateStopsView.class})
    private LocalDateTime departure;

    @Column(name = "is_bus_stop")
    @MapToDTO(mapClass = {View.TripView.class, View.TripTemplateStopsView.class})
    @JsonView({View.TripView.class, View.TripTemplateStopsView.class})
    private boolean isBusStop;

    @NotBlank(message = "The place of arrival (Street) cannot be empty")
    @MapToDTO(mapClass = {View.TripView.class, View.TripTemplateStopsView.class})
    @JsonView({View.TripView.class, View.TripTemplateStopsView.class})
    private String street;

    @MapToDTO(mapClass = {View.TripView.class, View.TripTemplateStopsView.class})
    @JsonView({View.TripView.class, View.TripTemplateStopsView.class})
    private int price;

    @Column(name = "bus_stop_number")
    @MapToDTO(mapClass = {View.TripView.class, View.TripTemplateStopsView.class})
    @JsonView({View.TripView.class, View.TripTemplateStopsView.class})
    private int busStopNumber;

    public CityStop() {

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

    public int getBusStopNumber() {
        return busStopNumber;
    }

    public void setBusStopNumber(int busStopNumber) {
        this.busStopNumber = busStopNumber;
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
