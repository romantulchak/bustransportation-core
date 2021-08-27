package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
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
    public TripDTO createTrip(@RequestBody @Valid Trip trip, Authentication authentication){
        return tripService.create(trip, authentication);
    }

    @GetMapping("/tripById/{id}")
    @JsonView(View.TripView.class)
    public TripDTO getTripById(@PathVariable("id") long id){
        return tripService.getById(id);
    }

    @GetMapping("/tripsForUser")
    @JsonView(View.TripView.class)
    public List<TripDTO> getTripsForUser(Authentication authentication){
        return tripService.getTripsForUser(authentication);
    }
}
