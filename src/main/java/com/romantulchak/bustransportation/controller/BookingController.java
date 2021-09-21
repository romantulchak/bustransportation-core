package com.romantulchak.bustransportation.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.romantulchak.bustransportation.dto.BookingDTO;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.model.Booking;
import com.romantulchak.bustransportation.payload.request.BookingRequest;
import com.romantulchak.bustransportation.service.impl.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public void bookPlaces(@RequestBody List<BookingRequest> bookings, @PathVariable("cityId") long cityId, Authentication authentication){
        bookingService.create(bookings, cityId, authentication);
    }

    @GetMapping("/findUserBooking")
    @PreAuthorize("isAuthenticated()")
    public PageableDTO<BookingDTO> findUserBookings(@RequestParam(value = "page", defaultValue = "0") int page,
                                                               @RequestParam(value = "size", defaultValue = "5") int size,
                                                               Authentication authentication){
        return bookingService.findUserBookings(page,size, authentication);
    }
}
