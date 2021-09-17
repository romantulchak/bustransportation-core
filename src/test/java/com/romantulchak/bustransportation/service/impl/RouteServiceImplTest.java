package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapper;
import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.RouteDTO;
import com.romantulchak.bustransportation.dto.SeatDTO;
import com.romantulchak.bustransportation.exception.RouteNotFoundException;
import com.romantulchak.bustransportation.model.Route;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.RouteRepository;
import com.romantulchak.bustransportation.repository.SeatRepository;
import com.romantulchak.bustransportation.service.RouteService;
import com.romantulchak.bustransportation.service.SeatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    @Mock
    RouteRepository routeRepository;

    @Mock
    SeatServiceImpl seatService;

    @InjectMocks
    EntityMapperInvoker<Route, RouteDTO> entityMapperInvoker;

    @InjectMocks
    RouteServiceImpl routeService;

    @Before
    public void setUp(){
        EntityMapper entityMapper = new EntityMapper();
        ReflectionTestUtils.setField(entityMapperInvoker, "entityMapper", entityMapper);
        ReflectionTestUtils.setField(routeService, "entityMapperInvoker", entityMapperInvoker);
    }

    @Test
    public void findRoutesByDirectionAndDate() {
        String from = "Test 1";
        String to = "Test 3";
        String dateAsString = LocalDateTime.of(2021, 9, 29, 16, 20).toString();
        LocalDate date = LocalDate.of(2021, 9, 29);
        List<Route> routes = new ArrayList<>();
        Route route = new Route.Builder(from, to, LocalDateTime.now(), LocalDateTime.now().plusDays(1)).build();
        Route route1 = new Route.Builder(from, to, LocalDateTime.now(), LocalDateTime.now().plusDays(1)).build();
        Route route2 = new Route.Builder(from, to, LocalDateTime.now(), LocalDateTime.now().plusDays(1)).build();
        routes.add(route);
        routes.add(route1);
        routes.add(route2);

        when(routeRepository.findRoutesByDateAndDirection(from, to, date)).thenReturn(routes);
        List<RouteDTO> routesByDirectionAndDate = routeService.findRoutesByDirectionAndDate(from, to, dateAsString, 4);

        assertNotNull(routesByDirectionAndDate);
        assertEquals(routes.size(), routesByDirectionAndDate.size());
        assertEquals(routes.get(0).getDepartureFrom(), routesByDirectionAndDate.get(0).getDepartureFrom());
        assertEquals(routes.get(0).getArrivalTo(), routesByDirectionAndDate.get(0).getArrivalTo());

    }

    @Test
    public void findRouteDetailsById() {
        Route route = new Route.Builder("from", "to", LocalDateTime.now(), LocalDateTime.now().plusDays(1)).build();
        Trip trip = new Trip();
        trip.setId(1);
        route.setTrip(trip);
        List<SeatDTO> seats = new ArrayList<>();
        SeatDTO seat = new SeatDTO();
        SeatDTO seat1 = new SeatDTO();
        SeatDTO seat2 = new SeatDTO();
        seat.setId(1);
        seat1.setId(1);
        seat2.setId(1);
        seats.add(seat);
        seats.add(seat1);
        seats.add(seat2);

        when(routeRepository.findById(1L)).thenReturn(Optional.of(route));
        when(seatService.findSeatsByTripId(1, route, View.RouteView.class)).thenReturn(seats);

        Map<String, Object> testMap = routeService.findRouteDetailsById(1);
        RouteDTO o = (RouteDTO) testMap.get("route");
        List<SeatDTO> seats1 = (List<SeatDTO>) testMap.get("seats");

        assertNotNull(testMap);
        assertNotNull(seats1);
        assertEquals(route.getDepartureFrom(), o.getDepartureFrom());
        assertEquals(route.getArrivalTo(), o.getArrivalTo());
        assertEquals(seats.size(), seats1.size());
        assertEquals(seats.get(0).getId(), seats1.get(0).getId());


    }

    @Test(expected = RouteNotFoundException.class)
    public void findRouteDetailsByIdThrowRouteNotFoundException() {
        when(routeRepository.findById(1L)).thenReturn(Optional.empty());
        routeService.findRouteDetailsById(1);
    }


}
