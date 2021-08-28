package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.RouteDTO;

import java.util.List;
import java.util.Map;

public interface RouteService {

    List<RouteDTO> findRoutesByDirectionAndDate(String from, String to, String date, int numberOfSeats);

    Map<String, Object> findRouteDetailsById(long id);
}
