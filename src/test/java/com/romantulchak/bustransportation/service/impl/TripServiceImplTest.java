package com.romantulchak.bustransportation.service.impl;

import com.ecfinder.core.manager.ECFinderInvoker;
import com.mapperDTO.mapper.EntityMapper;
import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.model.*;
import com.romantulchak.bustransportation.model.enums.RemoveType;
import com.romantulchak.bustransportation.model.enums.TripType;
import com.romantulchak.bustransportation.repository.RouteRepository;
import com.romantulchak.bustransportation.repository.SeatRepository;
import com.romantulchak.bustransportation.repository.TripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceImplTest {

    @Mock
    TripRepository tripRepository;

    @Mock
    RouteRepository routeRepository;

    @Mock
    SeatRepository seatRepository;

    @Mock
    TripServiceImpl tripServiceMock;

    @InjectMocks
    TripServiceImpl tripService;

    @Mock
    Authentication authentication;

    @Mock
    ECFinderInvoker<CityStop> ecFinderInvoker;

    @Mock
    EntityMapperInvoker<Trip, TripDTO> entityMapperInvoker;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(entityMapperInvoker, "entityMapper", new EntityMapper());
    }

    @Test
    public void createTripIrregular() {
        List<CityStop> stops = new ArrayList<>();
        LocalDateTime date = LocalDateTime.of(2021, 10, 29, 14, 10);
        LocalDateTime date1 = LocalDateTime.of(2021, 10, 29, 22, 10);
        stops.add(new CityStop("test", date, true));
        stops.add(new CityStop("test1", date1, true));
        List<Route> routes = new ArrayList<>();
        routes.add(new Route.Builder("test", "test1", date, date1).build());

        Trip trip = new Trip();
        trip.setName("Test");
        trip.setNumberOfSeats(34);
        trip.setStops(stops);
        trip.setId(1);
        trip.setTripType(TripType.IRREGULAR);
        Bus bus = new Bus();
        bus.setName("TestBus1");
        trip.setBus(bus);

        UserDetailsImpl userDetails = new UserDetailsImpl(1, "KzzxD", "test@gmail.com", "test", null, true);
        when(tripRepository.save(trip)).thenReturn(trip);
        when(authentication.getPrincipal()).thenReturn(userDetails);

    }

    @Test
    public void edit() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getByIdTripNotNull() {

        Trip trip = new Trip();
        trip.setId(1);
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());

        when(entityMapperInvoker.entityToDTO(trip, TripDTO.class, View.TripView.class)).thenReturn(tripDTO);
        when(tripRepository.findById(1L)).thenReturn(Optional.of(trip));
        TripDTO tripById = tripService.getById(1);

        assertNotNull(tripById);
        assertEquals(tripDTO.getId(), tripById.getId());
    }

    @Test(expected = TripNotFoundException.class)
    public void getByIdTripNotFoundException() {
        when(tripRepository.findById(1L)).thenThrow(TripNotFoundException.class);
        tripService.getById(1);
    }

    @Test
    public void getTripsNotNull() {
        List<Trip> trips = new ArrayList<>();
        Trip trip = new Trip();
        trip.setId(1);
        Trip trip1 = new Trip();
        trip.setId(2);
        Trip trip2 = new Trip();
        trip.setId(3);
        trips.add(trip);
        trips.add(trip1);
        trips.add(trip2);

        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        TripDTO tripDTO1 = new TripDTO();
        tripDTO1.setId(trip1.getId());
        TripDTO tripDTO2 = new TripDTO();
        tripDTO2.setId(trip2.getId());

        when(tripRepository.findAll()).thenReturn(trips);
        when(entityMapperInvoker.entityToDTO(trip, TripDTO.class, View.TripView.class)).thenReturn(tripDTO);
        when(entityMapperInvoker.entityToDTO(trip1, TripDTO.class, View.TripView.class)).thenReturn(tripDTO1);
        when(entityMapperInvoker.entityToDTO(trip2, TripDTO.class, View.TripView.class)).thenReturn(tripDTO2);

        List<TripDTO> allTrips = tripService.getTrips();


        assertNotNull(allTrips);
        assertEquals(trips.size(), allTrips.size());
        assertEquals(trips.get(0).getId(), allTrips.get(0).getId());

    }

    @Test
    public void getTripsForUserNotNull() {
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "KzzxD", "test@gmail.com", "test", null, true);
        List<Trip> trips = new ArrayList<>();
        Trip trip = new Trip();
        trip.setId(1);
        trips.add(trip);
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        Pageable pageable = PageRequest.of(1, 1);
        Page<Trip> page = new PageImpl<>(trips, pageable, 2);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(entityMapperInvoker.entityToDTO(trip, TripDTO.class, View.TripView.class)).thenReturn(tripDTO);
        when(tripRepository.findTripsForUser(1, RemoveType.SAVED, pageable)).thenReturn(page);
        PageableDTO<TripDTO> tripsForUser = tripService.getTripsForUser(1, authentication);

        assertNotNull(tripsForUser);
        assertEquals(pageable.getPageSize(), tripsForUser.getModel().size());
        assertSame(tripDTO, tripsForUser.getModel().get(0));
    }

    @Test(expected = UserNotFoundException.class)
    public void getTripsForUserThrowUserNotFound() {
        when(authentication.getPrincipal()).thenReturn(null);
        tripService.getTripsForUser(1, authentication);
    }

    @Test
    public void getTripByCityIdIsNotNull() {
        Trip trip = new Trip();
        trip.setId(1);
        trip.setName("Test 1");
        TripDTO tripDTO = new TripDTO();
        tripDTO.setId(trip.getId());
        tripDTO.setName(trip.getName());

        when(tripRepository.findTripByCityId(1, RemoveType.PRE_REMOVE.name())).thenReturn(Optional.of(trip));
        when(entityMapperInvoker.entityToDTO(trip, TripDTO.class, View.TripView.class)).thenReturn(tripDTO);
        TripDTO tripByCityId = tripService.getTripByCityId(1);

        assertNotNull(tripByCityId);
        assertEquals(trip.getId(), tripDTO.getId());
    }

    @Test(expected = TripNotFoundException.class)
    public void getTripByCityIdTipNotFound() {
        when(tripRepository.findTripByCityId(1, RemoveType.PRE_REMOVE.name())).thenThrow(TripNotFoundException.class);
        tripService.getTripByCityId(1);
    }

    @Test
    public void getStopsForTripNotNull() {
        LocalDateTime date = LocalDateTime.of(2021, 10, 9, 12, 20);
        List<CityStop> stops = new ArrayList<>();
        stops.add(new CityStop("Test 1", date, true));
        stops.add(new CityStop("Test 1", date.plusDays(1), true));
        stops.add(new CityStop("Test 1", date.plusDays(2), true));

        when(ecFinderInvoker.invoke(1, CityStop.class, Trip.class)).thenReturn(stops);
        List<CityStop> stopsForTrip = tripService.getStopsForTrip(1);

        assertNotNull(stopsForTrip);
        assertEquals(stops.size(), stopsForTrip.size());
        assertEquals(stops.get(0).getName(), stopsForTrip.get(0).getName());
        assertSame(stops.get(0), stopsForTrip.get(0));
    }
}
