package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.CityDTO;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.service.CityService;
import com.romantulchak.bustransportation.utility.EntityMapper;
import com.romantulchak.bustransportation.utility.EntityMapperInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final EntityMapperInvoker entityMapperInvoker;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, EntityMapperInvoker entityMapperInvoker){
        this.cityRepository = cityRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Override
    public List<CityDTO> findCitiesForTrip(long tripId) {
        return cityRepository.findCitiesForTrip(tripId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CityDTO convertToDTO(City city){
        return entityMapperInvoker.cityToDTO(city);
    }


}
