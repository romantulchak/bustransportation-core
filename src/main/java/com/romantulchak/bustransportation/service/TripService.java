package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.Trip;

import java.time.LocalDateTime;
import java.util.List;

public interface TripService {
    List<TripDTO> getTrips();

    List<TripDTO> getTripsByDate(String date, int numberOfSeats, String directionFrom, String directionTo);
}
