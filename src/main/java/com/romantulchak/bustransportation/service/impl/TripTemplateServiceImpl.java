package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.TripTemplateDTO;
import com.romantulchak.bustransportation.exception.TripTemplateNotFoundException;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.TripTemplate;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.TripTemplateRepository;
import com.romantulchak.bustransportation.service.TripTemplateService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.romantulchak.bustransportation.utils.UserUtils.userInSystem;

@Service
public class TripTemplateServiceImpl implements TripTemplateService {

    private final TripTemplateRepository tripTemplateRepository;
    private final EntityMapperInvoker<TripTemplate, TripTemplateDTO> entityMapperInvoker;

    public TripTemplateServiceImpl(TripTemplateRepository tripTemplateRepository,
                                   EntityMapperInvoker<TripTemplate, TripTemplateDTO> entityMapperInvoker) {
        this.tripTemplateRepository = tripTemplateRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Override
    public void createTripTemplate(TripTemplate tripTemplate, Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        User user = new User(userDetails.getId());
        tripTemplate.setUser(user);
        tripTemplateRepository.save(tripTemplate);
    }

    @Override
    public List<TripTemplateDTO> getTripTemplatesForUser(Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        return tripTemplateRepository.findTripTemplatesByUserId(userDetails.getId())
                .stream()
                .map(tripTemplate -> convertToDTO(tripTemplate, View.TripTemplateView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CityStop> getStopsForTripTemplate(long id) {
        TripTemplate tripTemplate = tripTemplateRepository.findTripTemplateStopsById(id)
                .orElseThrow(() -> new TripTemplateNotFoundException(id));
        return tripTemplate.getStops();
    }

    private TripTemplateDTO convertToDTO(TripTemplate tripTemplate, Class<?> classToCheck) {
        return entityMapperInvoker.entityToDTO(tripTemplate, TripTemplateDTO.class, classToCheck);
    }
}
