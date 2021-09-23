package com.romantulchak.bustransportation.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TripStatisticDeleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ElementCollection
    private List<CityStop> stops;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public TripStatisticDeleted(){}

    public TripStatisticDeleted(String name, User user, List<CityStop> stops, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.user = user;
        this.stops = stops;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CityStop> getStops() {
        return stops;
    }

    public void setStops(List<CityStop> stops) {
        this.stops = stops;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}

