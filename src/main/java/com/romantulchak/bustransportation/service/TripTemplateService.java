package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.TripTemplateDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.TripTemplate;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface TripTemplateService {
    void createTripTemplate(TripTemplate tripTemplate, Authentication authentication);

    List<TripTemplateDTO> getTripTemplatesForUser(Authentication authentication);

    List<CityStop> getStopsForTripTemplate(long id);
}
