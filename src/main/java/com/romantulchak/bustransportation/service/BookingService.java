package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.model.Booking;

import java.util.List;

public interface BookingService {
    void create(List<Booking> bookings, long cityId);
}
