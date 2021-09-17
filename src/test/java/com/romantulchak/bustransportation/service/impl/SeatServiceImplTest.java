package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapper;
import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.SeatDTO;
import com.romantulchak.bustransportation.model.Route;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.SeatRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class SeatServiceImplTest {

    @Mock
    SeatRepository seatRepository;

    @Mock
    EntityMapperInvoker<Seat, SeatDTO> entityMapperInvoker;

    @InjectMocks
    SeatServiceImpl seatService;

    @Before
    public void setUp(){
        EntityMapper entityMapper = new EntityMapper();
        ReflectionTestUtils.setField(entityMapperInvoker, "entityMapper", entityMapper);
    }


    @Test
    public void findSeatsByTripId() {
        Route route = new Route.Builder("from", "to", LocalDateTime.now(), LocalDateTime.now().plusDays(1)).build();
        List<Seat> seats = new ArrayList<>();
        Seat seat = new Seat();
        Seat seat1 = new Seat();
        Seat seat2 = new Seat();
        seat.setId(1);
        seat1.setId(1);
        seat2.setId(1);
        seats.add(seat);
        seats.add(seat1);
        seats.add(seat2);

        when(seatRepository.findSeatsByTripId(1)).thenReturn(seats);
        List<SeatDTO> seatsByTripId = seatService.findSeatsByTripId(1, route, View.RouteView.class);

    }

    @Test
    public void checkBookedSeat() {
    }
}
