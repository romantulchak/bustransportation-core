package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.RouteDTO;

import java.util.List;

public interface RouteService {

    List<RouteDTO> findRoutesByDirectionAndDate(String from, String to, String date, int numberOfSeats);
}
