package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.RouteDTO;
import com.romantulchak.bustransportation.model.Route;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.RouteRepository;
import com.romantulchak.bustransportation.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm";
    private final RouteRepository routeRepository;
    private final EntityMapperInvoker<Route, RouteDTO> entityMapperInvoker;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository,
                            EntityMapperInvoker<Route, RouteDTO> entityMapperInvoker){
        this.routeRepository = routeRepository;
        this.entityMapperInvoker = entityMapperInvoker;
    }

    @Override
    public List<RouteDTO> findRoutesByDirectionAndDate(String from, String to, String date, int numberOfSeats) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate dateOfDeparture = LocalDateTime.parse(date, dateTimeFormatter).toLocalDate();
        List<Route> routes = routeRepository.findRoutesByDateAndDirection(from, to, dateOfDeparture);
        return routes.stream()
                .map(route -> convertToDTO(route, View.RouteView.class))
                .collect(Collectors.toList());
    }

    private RouteDTO convertToDTO(Route trip, Class<?> classToCheck) {
        return entityMapperInvoker.entityToDTO(trip, RouteDTO.class, classToCheck);
    }
}
