package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.SeatDTO;
import com.romantulchak.bustransportation.model.Route;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final EntityMapperInvoker<Seat, SeatDTO> entityMapperInvoker;

    public SeatServiceImpl(SeatRepository seatRepository,
                           EntityMapperInvoker<Seat, SeatDTO> entityMapperInvoker) {
        this.seatRepository = seatRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }


    @Override
    public List<SeatDTO> findSeatsByTripId(long tripId, Route route, Class<?> classToCheck) {
        return seatRepository.findSeatsByTripId(tripId)
                .stream()
                .map(seat -> checkBookedSeat(route, classToCheck, seat))
                .collect(Collectors.toList());
    }

    private SeatDTO checkBookedSeat(Route route, Class<?> classToCheck, Seat seat) {
        boolean isBooked = seat.getTickets()
                .stream()
                .anyMatch(ticket ->
                        ticket.getRoute().getEntranceStop() >= route.getEntranceStop() &&
                        ticket.getRoute().getExitStop() <= route.getExitStop());
        SeatDTO seatDTO = convertToDTO(seat, classToCheck);
        if (isBooked) {
            seatDTO.setBooked(true);
        }
        return seatDTO;
    }


    private SeatDTO convertToDTO(Seat seat, Class<?> classToCheck) {
        return entityMapperInvoker.entityToDTO(seat, SeatDTO.class, classToCheck);
    }
}
