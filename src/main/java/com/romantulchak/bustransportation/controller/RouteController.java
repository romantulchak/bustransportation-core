package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.RouteDTO;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.RouteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/route")
@CrossOrigin(value = "*", maxAge = 3600L)
public class RouteController {

    private final RouteServiceImpl routeService;

    @Autowired
    public RouteController(RouteServiceImpl routeService){
        this.routeService = routeService;
    }

    @GetMapping("/getRoutesByDirectionAndDate")
    @JsonView(View.RouteView.class)
    public List<RouteDTO> findRoutesByDate(@RequestParam(value = "from") String from,
                                           @RequestParam(value = "to") String to,
                                           @RequestParam(value = "date") String date,
                                           @RequestParam(value = "numberOfSeats", defaultValue = "0") int numberOfSeats) {
        return routeService.findRoutesByDirectionAndDate(from, to, date, numberOfSeats);
    }
}
