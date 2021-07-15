package com.romantulchak.bustransportation.controller;

import com.romantulchak.bustransportation.model.Booking;
import com.romantulchak.bustransportation.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600L)
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/bookPlaces/{cityId}")
    @PreAuthorize("isAuthenticated()")
    public void bookPlaces(@RequestBody List<Booking> bookings, @PathVariable("cityId") long cityId){
        bookingService.create(bookings, cityId);
    }

}
