package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
public class IntermediatePlaces {

    private String city;

    private LocalDateTime dateAndTime;

    private long price;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
