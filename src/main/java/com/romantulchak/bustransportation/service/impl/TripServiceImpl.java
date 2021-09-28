package com.romantulchak.bustransportation.service.impl;

import com.ecfinder.core.manager.ECFinderInvoker;
import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.exception.TripAlreadyPreDeletedException;
import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.exception.TripNotInPreDeletedState;
import com.romantulchak.bustransportation.model.*;
import com.romantulchak.bustransportation.model.enums.RemoveType;
import com.romantulchak.bustransportation.model.enums.TripType;
import com.romantulchak.bustransportation.repository.BusRepository;
import com.romantulchak.bustransportation.repository.RouteRepository;
import com.romantulchak.bustransportation.repository.SeatRepository;
import com.romantulchak.bustransportation.repository.TripRepository;
import com.romantulchak.bustransportation.service.TripService;
import com.romantulchak.bustransportation.service.TripStatisticDeletedService;
import com.romantulchak.bustransportation.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.romantulchak.bustransportation.utils.UserUtils.userInSystem;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final TripStatisticDeletedService tripStatisticDeletedService;
    private final ECFinderInvoker<CityStop> ecFinderInvoker;
    private final EntityMapperInvoker<Trip, TripDTO> entityMapperInvoker;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository,
                           SeatRepository seatRepository,
                           RouteRepository routeRepository,
                           BusRepository busRepository,
                           TripStatisticDeletedService tripStatisticDeletedService,
                           ECFinderInvoker<CityStop> ecFinderInvoker,
                           EntityMapperInvoker<Trip, TripDTO> entityMapperInvoker) {
        this.tripRepository = tripRepository;
        this.ecFinderInvoker = ecFinderInvoker;
        this.busRepository = busRepository;
        this.seatRepository = seatRepository;
        this.routeRepository = routeRepository;
        this.tripStatisticDeletedService = tripStatisticDeletedService;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Transactional
    @Override
    public void create(Trip trip, Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        if (trip.getTripType() == TripType.REGULAR) {
            long numberOfDays = ChronoUnit.DAYS.between(trip.getDateStart(), trip.getDateEnded());
            for (int i = 0; i < numberOfDays; i++) {
                Trip trip1 = trip.clone();
                trip1.getStops().forEach(cityStop -> cityStop.setDeparture(cityStop.getDeparture().plusDays(1)));
                initTrip(trip1, userDetails);
            }
        } else {
            initTrip(trip, userDetails);
        }
    }


    private void initTrip(Trip trip, UserDetailsImpl userDetails) {
        initCreator(trip, userDetails);
        trip = tripRepository.save(trip);
        addTripRoutes(trip);
        initSeats(trip);
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
                if (!(Objects.equals(previous, stop)) && stop.isBusStop() && previous.isBusStop()) {
                    int price = stop.getPrice() - previous.getPrice();
                    Route route = new Route.Builder(previous.getName(), stop.getName(), previous.getDeparture(), stop.getArrival())
                            .withPrice(price)
                            .withTrip(trip)
                            .withEntranceStop(previous.getBusStopNumber())
                            .withExitStop(stop.getBusStopNumber())
                            .build();
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
    public TripDTO editTripBus(Trip trip, long busId, Authentication authentication) {
        UserDetailsImpl user = userInSystem(authentication);
        Bus bus = busRepository.findBusByIdAndUserId(busId, user.getId()).orElseThrow(BusNotFoundException::new);
        if (!Objects.equals(trip.getBus(), bus)) {
            trip.setBus(bus);
            trip.setNumberOfSeats(bus.getNumberOfSeats());
            tripRepository.save(trip);
        }
        return convertToDTO(trip, View.TripView.class);
    }

    @Transactional
    @Override
    public void delete(long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
        tripStatisticDeletedService.create(trip);
        tripRepository.deleteById(id);
    }

    @Override
    public void preDelete(long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
        if (trip.getRemoveType() == RemoveType.PRE_REMOVE) {
            throw new TripAlreadyPreDeletedException(trip.getId(), trip.getName());
        }
        tripRepository.updateRemoveType(trip.getId(), RemoveType.PRE_REMOVE);
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
    public PageableDTO<TripDTO> getTripsForUser(int page, Authentication authentication) {
        page = PageableUtils.getCorrectPage(page);
        return getTripDTOPageableDTO(page, authentication, RemoveType.SAVED);
    }

    @Override
    public TripDTO getTripByCityId(long id) {
        Trip trip = tripRepository.findTripByCityId(id, RemoveType.PRE_REMOVE.name()).orElseThrow(TripNotFoundException::new);
        return convertToDTO(trip, View.TripView.class);
    }

    @Override
    public List<CityStop> getStopsForTrip(long id) {
        return ecFinderInvoker.invoke(id, CityStop.class, Trip.class);
    }

    @Override
    public PageableDTO<TripDTO> getPreDeletedTrips(int page, Authentication authentication) {
        return getTripDTOPageableDTO(page, authentication, RemoveType.PRE_REMOVE);
    }

    @Override
    public int getCountPreDeletedTrips(Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        return tripRepository.countAllByRemoveTypeAndCreatorId(RemoveType.PRE_REMOVE, userDetails.getId()).orElse(0);
    }

    @Override
    public TripDTO restoreTrip(long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(TripNotFoundException::new);
        if (trip.getRemoveType() == RemoveType.PRE_REMOVE){
            tripRepository.updateRemoveType(trip.getId(), RemoveType.SAVED);
            trip.setRemoveType(RemoveType.SAVED);
            return convertToDTO(trip, View.TripView.class);
        }
        throw new TripNotInPreDeletedState(trip.getName());
    }

    private PageableDTO<TripDTO> getTripDTOPageableDTO(int page, Authentication authentication, RemoveType preRemove) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Trip> tripsPage = tripRepository.findTripsForUser(userDetails.getId(), preRemove, pageable);
        List<TripDTO> trips = tripsPage.getContent()
                .stream()
                .map(trip -> convertToDTO(trip, View.TripView.class))
                .collect(Collectors.toList());
        return new PageableDTO<>(tripsPage.getTotalPages(), tripsPage.getTotalElements(), page, trips);
    }

    private TripDTO convertToDTO(Trip trip, Class<?> classToCheck) {
        return entityMapperInvoker.entityToDTO(trip, TripDTO.class, classToCheck);
    }
}
