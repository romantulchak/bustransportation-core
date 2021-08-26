package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.exception.TripCitiesEmptyException;
import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.model.*;
import com.romantulchak.bustransportation.repository.RouteRepository;
import com.romantulchak.bustransportation.repository.SeatRepository;
import com.romantulchak.bustransportation.repository.TripRepository;
import com.romantulchak.bustransportation.service.TripService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.romantulchak.bustransportation.utility.UserUtility.userInSystem;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final RouteRepository routeRepository;
    private final EntityMapperInvoker<Trip, TripDTO> entityMapperInvoker;

    public TripServiceImpl(TripRepository tripRepository,
                           SeatRepository seatRepository,
                           RouteRepository routeRepository,
                           EntityMapperInvoker<Trip, TripDTO> entityMapperInvoker) {
        this.tripRepository = tripRepository;
        this.seatRepository = seatRepository;
        this.routeRepository = routeRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Transactional
    @Override
    public TripDTO create(Trip trip, Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        if (!trip.getStops().isEmpty()) {
            if (trip.getBus() != null) {
                initCreator(trip, userDetails);
                trip = tripRepository.save(trip);
                addTripRoutes(trip);
                initSeats(trip);
                return convertToDTO(trip, View.TripView.class);
            }
            throw new BusNotFoundException();
        }
        throw new TripCitiesEmptyException();
    }

    private void initCreator(Trip trip, UserDetailsImpl userDetails) {
        User user = new User();
        user.setId(userDetails.getId());
        trip.setCreator(user);
    }

    private void addTripRoutes(Trip trip) {
        List<Route> routes = new ArrayList<>();
        List<CityStop> cityStops = new ArrayList<>(trip.getStops());
        ListIterator<CityStop> stops = cityStops.listIterator();
        while (stops.hasNext()) {
            if (cityStops.size() == 1)
                break;
            CityStop previous = stops.next();
            for (CityStop stop : cityStops) {
                if (!(Objects.equals(previous, stop)) && stop.isBusStop()) {
                    int price = stop.getPrice() - previous.getPrice();
                    Route route = new Route(previous.getName(), stop.getName(), previous.getDeparture(), stop.getArrival(), price, trip);
                    routes.add(route);
                }
            }
            stops.remove();
        }
        routeRepository.saveAll(routes);
    }

    private void initSeats(Trip trip) {
        List<Seat> seats = new ArrayList<>(trip.getNumberOfSeats());
        for (int numberOfSeat = 1; numberOfSeat <= trip.getNumberOfSeats(); numberOfSeat++) {
            seats.add(new Seat(numberOfSeat, trip));
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
                .map(trip -> convertToDTO(trip, View.TripView.class))
                .orElseThrow(TripNotFoundException::new);
    }

    @Override
    public List<TripDTO> getTrips() {
        return tripRepository.findAll()
                .stream()
                .map(trip -> convertToDTO(trip, View.TripView.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> getTripsForUser(Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        return tripRepository.findTripsForUser(userDetails.getId())
                .stream()
                .map(trip -> convertToDTO(trip, View.TripView.class))
                .collect(Collectors.toList());
    }

    @Override
    public TripDTO getTripByCityId(long id) {
        Trip trip = tripRepository.findTripByCityId(id).orElseThrow(TripNotFoundException::new);
        return convertToDTO(trip, View.TripView.class);
    }

    private TripDTO convertToDTO(Trip trip, Class<?> classToCheck) {
        return entityMapperInvoker.entityToDTO(trip, TripDTO.class, classToCheck);
    }
}
