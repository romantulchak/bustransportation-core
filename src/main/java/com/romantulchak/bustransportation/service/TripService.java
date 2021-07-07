package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.Trip;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.List;

public interface TripService {

    TripDTO create(Trip trip, Authentication authentication);

    void edit(Trip trip);

    void delete(long id);

    TripDTO getById(long id);

    List<TripDTO> getTrips();

    List<TripDTO> getTripsForUser(Authentication authentication);
}
