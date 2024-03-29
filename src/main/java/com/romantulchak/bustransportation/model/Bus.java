package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String brand;

    private int numberOfSeats;

    @OneToMany(mappedBy = "bus")
    private List<Trip> trips;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "bus")
    private List<TripTemplate> tripTemplates;

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setName(String busName) {
        this.name = busName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String busBrand) {
        this.brand = busBrand;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<TripTemplate> getTripTemplates() {
        return tripTemplates;
    }

    public void setTripTemplates(List<TripTemplate> tripTemplates) {
        this.tripTemplates = tripTemplates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bus)) return false;
        Bus bus = (Bus) o;
        return id == bus.id && numberOfSeats == bus.numberOfSeats && Objects.equals(name, bus.name) && Objects.equals(brand, bus.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, numberOfSeats);
    }
}
