package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.model.Booking;
import com.romantulchak.bustransportation.repository.BookingRepository;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    public final BookingRepository bookingRepository;
    public final CityRepository cityRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CityRepository cityRepository){
        this.bookingRepository = bookingRepository;
        this.cityRepository = cityRepository;
    }


    @Override
    public void create(List<Booking> bookings, long cityId) {
        System.out.println(bookings);
        System.out.println(cityId);
    }
}
