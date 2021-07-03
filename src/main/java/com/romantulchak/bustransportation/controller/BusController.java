package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.BusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/bus")
public class BusController {

    private BusServiceImpl busService;

    @Autowired
    public BusController(BusServiceImpl busService){
        this.busService = busService;
    }

    @PostMapping("/createBus")
    @JsonView(View.BusView.class)
    public BusDTO createBus(@RequestBody Bus bus, Authentication authentication){
        return busService.create(bus, authentication);
    }
    @GetMapping("/buses")
    @JsonView(View.BusView.class)
    public List<BusDTO> getBuses(){
        return busService.getBuses();
    }

    @GetMapping("/findBusesForUser")
    @JsonView(View.BusView.class)
    public List<BusDTO> findBusesForUser(Authentication authentication){
        return busService.findBusesForUser(authentication);
    }


}
