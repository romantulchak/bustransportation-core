package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.Trip;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TripService {

    void create(Trip trip, Authentication authentication);

    TripDTO editTripBus(Trip trip, long busId, Authentication authentication);

    void delete(long id);

    void preDelete(long id);

    TripDTO getById(long id);

    List<TripDTO> getTrips();

    List<TripDTO> getTripsForUser(Authentication authentication);

    TripDTO getTripByCityId(long id);

    List<CityStop> getStopsForTrip(long id);
}
