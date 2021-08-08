package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.exception.CityNotFoundException;
import com.romantulchak.bustransportation.exception.SeatsAlreadyBookedException;
import com.romantulchak.bustransportation.model.Booking;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.repository.BookingRepository;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.repository.SeatRepository;
import com.romantulchak.bustransportation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookingServiceImpl implements BookingService {

    public final BookingRepository bookingRepository;
    public final CityRepository cityRepository;
    public final SeatRepository seatRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CityRepository cityRepository,
                              SeatRepository seatRepository) {
        this.bookingRepository = bookingRepository;
        this.cityRepository = cityRepository;
        this.seatRepository = seatRepository;
    }


    @Transactional
    @Override
    public void create(List<Booking> bookings, long cityId) {
        City city = cityRepository.findById(cityId).orElseThrow(CityNotFoundException::new);
        Set<Integer> bookedSeats = new TreeSet<>();
        bookings.forEach(booking -> {
            if (seatRepository.isSeatBooked(booking.getSeat().getId(), city.getDirection().getDirectionFrom()).isEmpty()) {
                booking.setCity(city);
            } else {
                bookedSeats.add(booking.getSeat().getSeatNumber());
            }
        });
        if (!bookedSeats.isEmpty()){
        String seats = String.join(",", bookedSeats.toString())
                .replace("[", "")
                .replace("]", "");
        throw new SeatsAlreadyBookedException(seats);
        }
        bookingRepository.saveAll(bookings);
    }
}
