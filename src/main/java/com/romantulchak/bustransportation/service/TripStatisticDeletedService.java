package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.dto.TripStatisticDeletedDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.Trip;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TripStatisticDeletedService {

    void create(Trip trip);

    PageableDTO<TripStatisticDeletedDTO> getFullStatistics(int page, int size, Authentication authentication);

    List<CityStop> findCityStops(long id);

}
