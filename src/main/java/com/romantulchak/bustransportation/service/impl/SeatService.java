package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.SeatDTO;
import com.romantulchak.bustransportation.model.Route;

import java.util.List;

public interface SeatService {

    List<SeatDTO> findSeatsByTripId(long id, Route route, Class<?> classToCheck);

}
