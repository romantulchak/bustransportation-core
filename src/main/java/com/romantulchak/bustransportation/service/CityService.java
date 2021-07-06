package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.CityDTO;

import java.util.List;

public interface CityService {

    List<CityDTO> findCitiesForTrip(long tripId);

}
