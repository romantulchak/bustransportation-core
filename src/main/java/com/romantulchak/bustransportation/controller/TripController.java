package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/trip")
public class TripController {
    private final TripServiceImpl tripService;
    @Autowired
    public TripController(TripServiceImpl tripService){
        this.tripService =tripService;
    }

    @GetMapping("/trips")
    @JsonView(View.TripView.class)
    public List<TripDTO> trips(){
        return tripService.getTrips();
    }

    @PostMapping("/createTrip")
    public TripDTO createTrip(@RequestBody Trip trip){
        return tripService.create(trip);
    }
    @GetMapping("/tripsByDate")
    @JsonView(View.TripView.class)
    public List<TripDTO> getTripsByDate(@RequestParam(name = "date")String date, @RequestParam(name = "numberOfSeats") int numberOfSeats, @RequestParam(name="directionFrom") String directionFrom, @RequestParam(name="directionTo") String directionTo){
        return tripService.getTripsByDate(date,numberOfSeats, directionFrom, directionTo);
    }

}
