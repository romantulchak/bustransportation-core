package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.DirectionDTO;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.service.impl.DirectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/direction")
public class DirectionController {

    private final DirectionServiceImpl directionService;

    @Autowired
    public DirectionController(DirectionServiceImpl directionService){
        this.directionService = directionService;
    }

    @GetMapping("/directions")
    @JsonView(View.DirectionView.class)
    public List<DirectionDTO> directions(){
        return directionService.getDirections();
    }

    @PostMapping("/createDirection")
    @JsonView(View.DirectionView.class)
    public DirectionDTO createDirection(@RequestBody Direction direction){
        return directionService.create(direction);
    }
}
