package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.BusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createBus(@RequestBody Bus bus){
        busService.createBus(bus);
    }
    @GetMapping("/busses")
    @JsonView(View.BusView.class)
    public List<BusDTO> getBuses(){
        return busService.getBuses();
    }
}
