package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.CityDTO;
import com.romantulchak.bustransportation.exception.CityNotFoundException;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.BookingRepository;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final BookingRepository bookingRepository;
    private final EntityMapperInvoker<City, CityDTO> entityMapperInvoker;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository,
                           BookingRepository bookingRepository,
                           EntityMapperInvoker<City, CityDTO> entityMapperInvoker) {
        this.cityRepository = cityRepository;
        this.bookingRepository = bookingRepository;
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
        List<City> cities = new ArrayList<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateOfDeparture = LocalDateTime.parse(date, dateTimeFormatter);
        getCities(numberOfSeats, directionFrom, directionTo, cities, dateOfDeparture);
        return cities.stream()
                .map(city -> convertToDTO(city, View.TripView.class))
                .collect(Collectors.toList());
    }

    private void getCities(int numberOfSeats, String directionFrom, String directionTo, List<City> cities, LocalDateTime dateOfDeparture) {
        List<City> citiesTrip = cityRepository.findCitiesTrip(dateOfDeparture, directionFrom, directionTo);
        for (City city : citiesTrip) {
            Optional<Integer> totalNumberOfSeats = bookingRepository.findTotalNumberOfBookedSeats(city.getId());
            int seats = city.getTrip().getNumberOfSeats();
            if(totalNumberOfSeats.isPresent()){
                seats = city.getTrip().getNumberOfSeats() - totalNumberOfSeats.get();
            }
            if (seats > numberOfSeats)
                cities.add(city);
        }
    }

    @Override
    public CityDTO findCityById(long id) {
        City city = cityRepository
                .findById(id)
                .orElseThrow(CityNotFoundException::new);
        return convertToDTO(city, View.TripView.class);
    }

    private CityDTO convertToDTO(City city, Class<?> classToCheck) {
        return entityMapperInvoker.entityToDTO(city, CityDTO.class, classToCheck);
    }


}
