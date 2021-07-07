package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.CityDTO;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/city")
public class CityController {

    private final CityServiceImpl cityService;

    @Autowired
    public CityController(CityServiceImpl cityService){
        this.cityService = cityService;
    }

    @GetMapping("/citesForTrip/{tripId}")
    @JsonView(View.TripView.class)
    public List<CityDTO> findCitiesForTrip(@PathVariable("tripId") long tripId){
        return cityService.findCitiesForTrip(tripId);
    }

    @GetMapping("/getCityTrip")
    @JsonView(View.TripView.class)
    public List<CityDTO> findCityTrip(@RequestParam(name = "date")String date, @RequestParam(name = "numberOfSeats", defaultValue = "1") int numberOfSeats, @RequestParam(name="directionFrom") String directionFrom, @RequestParam(name="directionTo") String directionTo){
        return cityService.findCityTripsByDate(date, numberOfSeats, directionFrom, directionTo);
    }
}
