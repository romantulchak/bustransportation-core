package com.romantulchak.bustransportation.dto;


import com.fasterxml.jackson.annotation.JsonView;
import com.mapperDTO.annotation.DTO;
import com.mapperDTO.annotation.MapToDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.View;

import java.time.LocalDateTime;
import java.util.List;

@DTO
public class TripStatisticDeletedDTO {

    @JsonView(View.TripDeletedStatistic.class)
    @MapToDTO(mapClass = {View.TripDeletedStatistic.class})
    private long id;

    @JsonView(View.TripDeletedStatistic.class)
    @MapToDTO(mapClass = {View.TripDeletedStatistic.class})
    private String name;

    @JsonView(View.TripDeletedStatistic.class)
    @MapToDTO(mapClass = {View.TripDeletedStatistic.class})
    private UserDTO user;

    private List<CityStop> stops;

    @JsonView(View.TripDeletedStatistic.class)
    @MapToDTO(mapClass = {View.TripDeletedStatistic.class})
    private LocalDateTime startDate;

    @JsonView(View.TripDeletedStatistic.class)
    @MapToDTO(mapClass = {View.TripDeletedStatistic.class})
    private LocalDateTime endDate;

    @JsonView(View.TripDeletedStatistic.class)
    @MapToDTO(mapClass = {View.TripDeletedStatistic.class})
    private LocalDateTime removeDate;

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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
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

    public LocalDateTime getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(LocalDateTime removeDate) {
        this.removeDate = removeDate;
    }
}
