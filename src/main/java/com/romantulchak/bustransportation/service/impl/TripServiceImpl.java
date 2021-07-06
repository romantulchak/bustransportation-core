package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.exception.TripCitiesEmptyException;
import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.repository.TripRepository;
import com.romantulchak.bustransportation.service.TripService;
import com.romantulchak.bustransportation.utility.EntityMapperInvoker;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.romantulchak.bustransportation.repository.SeatRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.romantulchak.bustransportation.utility.UserUtility.*;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final CityRepository cityRepository;
    private final EntityMapperInvoker entityMapperInvoker;
    public TripServiceImpl(TripRepository tripRepository, SeatRepository seatRepository, CityRepository cityRepository, EntityMapperInvoker entityMapperInvoker){
        this.tripRepository = tripRepository;
        this.seatRepository = seatRepository;
        this.cityRepository = cityRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Transactional
    @Override
    public TripDTO create(Trip trip, Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        if (!trip.getCities().isEmpty()) {
            if (trip.getBus() != null) {
                User user = new User();
                user.setId(userDetails.getId());
                trip.setDateStart(trip.getCities().get(0).getDateOfDeparture());
                trip.setDateEnd(trip.getCities().get(trip.getCities().size() - 1).getDateOfArrival());
                trip.setCreator(user);
                Trip tripAfterSave = tripRepository.save(trip);
                initCities(trip.getCities(), tripAfterSave);
                initSeats(trip);
                return convertToDTO(trip);
            }
            throw new BusNotFoundException();
        }
        throw new TripCitiesEmptyException();
    }

    @Transactional
    public void initCities(List<City> cities, Trip tripAfterSave) {
        cities.forEach(city -> {
            city.setTrip(tripAfterSave);
        });
        cityRepository.saveAll(cities);
    }

    @Transactional
    public void initSeats(Trip trip) {
        List<Seat> seats = new ArrayList<>(trip.getNumberOfSeats());
        for (int numberOfSeat = 1; numberOfSeat <= trip.getNumberOfSeats(); numberOfSeat++){
            seats.add(new Seat(numberOfSeat,trip));
        }
        seatRepository.saveAll(seats);
    }

    @Override
    public void edit(Trip entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public TripDTO getById(long id) {
        return tripRepository
                .findById(id)
                .map(this::convertToDTO)
                .orElseThrow(TripNotFoundException::new);
    }

    @Override
    public List<TripDTO> getTrips() {
        return tripRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> getTripsByDate(String date, int numberOfSeats, String directionFrom, String directionTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(date,dateTimeFormatter);
//        return tripRepository.findTripsByDate(localDateTime, numberOfSeats, directionFrom, directionTo)
//                .stream()
//                .map(this::convertToDTO)
//                .filter(x-> x.getDate().isAfter(localDateTime) || x.getDate().isEqual(localDateTime))
//                .collect(Collectors.toList());

        return null;
    }

    @Override
    public List<TripDTO> getTripsForUser(Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        return tripRepository.findTripsForUser(userDetails.getId())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TripDTO convertToDTO(Trip trip){
        return entityMapperInvoker.tripToDTO(trip);
    }
}
