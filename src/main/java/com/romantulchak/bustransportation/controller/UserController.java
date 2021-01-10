package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(value = "*", maxAge = 3600L)
public class UserController {
    private UserServiceImpl userService;
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/addUserToSeat/{tripId}")
    public void addUserToSeat(@RequestBody List<Seat> seats, @PathVariable("tripId")long tripId){
        userService.addUserToSeat(seats, tripId);
    }
}
