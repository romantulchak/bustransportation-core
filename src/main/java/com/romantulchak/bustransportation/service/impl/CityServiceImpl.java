package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.CityDTO;
import com.romantulchak.bustransportation.exception.CityNotFoundException;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final EntityMapperInvoker<City,CityDTO> entityMapperInvoker;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, EntityMapperInvoker<City,CityDTO> entityMapperInvoker){
        this.cityRepository = cityRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Override
    public List<CityDTO> findCitiesForTrip(long tripId) {
        return cityRepository.findCitiesForTrip(tripId)
                .stream()
                .map(city -> convertToDTO(city, View.TripView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CityDTO> findCityTripsByDate(String date, int numberOfSeats, String directionFrom, String directionTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateOfDeparture = LocalDateTime.parse(date,dateTimeFormatter);
        List<City> citiesTrip = cityRepository.findCitiesTrip(dateOfDeparture, numberOfSeats, directionFrom, directionTo);
        for (City city : citiesTrip) {
            city.getBookings().stream().filter(booking -> booking.getTotalNumberOfSeats() > numberOfSeats).collect(Collectors.toList());
        }
        return cityRepository.findCitiesTrip(dateOfDeparture, numberOfSeats, directionFrom, directionTo)
                .stream()
       //         .filter(city -> city.getBookings().stream().filter(booking -> booking.getTickets().size() >= numberOfSeats))
                .map(city -> convertToDTO(city, View.TripView.class))
                .collect(Collectors.toList());
    }

    @Override
    public CityDTO findCityById(long id) {
        City city = cityRepository
                .findById(id)
                .orElseThrow(CityNotFoundException::new);
        return convertToDTO(city, View.TripView.class);
    }

    private CityDTO convertToDTO(City city, Class<?> classToCheck){
        return entityMapperInvoker.entityToDTO(city, CityDTO.class, classToCheck);
    }


}
