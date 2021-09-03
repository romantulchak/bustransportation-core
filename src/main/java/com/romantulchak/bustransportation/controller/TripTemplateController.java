package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.TripTemplateDTO;
import com.romantulchak.bustransportation.model.CityStop;
import com.romantulchak.bustransportation.model.TripTemplate;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.TripTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/trip-template")
public class TripTemplateController {

    private final TripTemplateService tripTemplateService;

    @Autowired
    public TripTemplateController(TripTemplateService tripTemplateService){
        this.tripTemplateService = tripTemplateService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public void createTripTemplate(@RequestBody TripTemplate tripTemplate, Authentication authentication){
        tripTemplateService.createTripTemplate(tripTemplate, authentication);
    }

    @GetMapping("/user-templates")
    @JsonView(View.TripTemplateView.class)
    public List<TripTemplateDTO> getTripTemplatesForUser(Authentication authentication){
        return tripTemplateService.getTripTemplatesForUser(authentication);
    }

    @GetMapping("/template-stops/{id}")
    @PreAuthorize("hasRole('USER')")
    @JsonView(View.TripTemplateStopsView.class)
    public List<CityStop> getTripStopsById(@PathVariable("id") long id){
        return tripTemplateService.getStopsForTripTemplate(id);
    }


}
