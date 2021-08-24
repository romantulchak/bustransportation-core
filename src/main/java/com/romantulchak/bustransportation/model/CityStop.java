package com.romantulchak.bustransportation.model;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class CityStop {

    private String name;

    private LocalDateTime departure;

    public CityStop(){

    }

    public CityStop(String name, LocalDateTime departure) {
        this.name = name;
        this.departure = departure;
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
}
