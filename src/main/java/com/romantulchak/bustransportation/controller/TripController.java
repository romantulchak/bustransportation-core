package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.TripServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public TripController(TripServiceImpl tripService) {
        this.tripService = tripService;
    }

    @GetMapping("/trips")
    @JsonView(View.TripView.class)
    public List<TripDTO> trips() {
        return tripService.getTrips();
    }

    @PostMapping("/createTrip")
    public void createTrip(@RequestBody @Valid Trip trip, Authentication authentication) {
        tripService.create(trip, authentication);
    }

    @GetMapping("/tripById/{id}")
    @JsonView(View.TripView.class)
    public TripDTO getTripById(@PathVariable("id") long id) {
        return tripService.getById(id);
    }

    @GetMapping("/tripsForUser/{page}")
    public PageableDTO<TripDTO> getTripsForUser(@PathVariable("page") int page, Authentication authentication) {
        return tripService.getTripsForUser(page, authentication);
    }
    @GetMapping("/trip-stops/{id}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(View.TripTemplateStopsView.class)
    public List<CityStop> getTripStopsById(@PathVariable("id") long id){
        return tripService.getStopsForTrip(id);
    }

    @PutMapping("edit-trip-bus/{id}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(View.TripView.class)
    public TripDTO editTripBus(@RequestBody Trip trip, long busId, Authentication authentication){
        return tripService.editTripBus(trip, busId, authentication);
    }

    @DeleteMapping("/full-delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public void fullDeleteTrip(@PathVariable("id") long id){
        tripService.delete(id);
    }

    @PutMapping("/pre-delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public void preDeleteTrip(@PathVariable("id") long id){
        tripService.preDelete(id);
    }

    @GetMapping("/pre-deleted-trips/{page}")
    @PreAuthorize("hasRole('USER')")
    public PageableDTO<TripDTO> getPreDeletedTrips(@PathVariable("page") int page, Authentication authentication){
        return tripService.getPreDeletedTrips(page, authentication);
    }

    @GetMapping("/count-pre-deleted-trips")
    @PreAuthorize("hasRole('USER')")
    public int getCountPreDeletedTrips(Authentication authentication){
        return tripService.getCountPreDeletedTrips(authentication);
    }

    @PutMapping("/restore-trip/{id}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(View.TripView.class)
    public TripDTO restoreTrip(@PathVariable("id") long id){
        return tripService.restoreTrip(id);
    }

}
