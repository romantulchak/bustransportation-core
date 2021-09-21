package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.BookingDTO;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.payload.request.BookingRequest;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

public interface BookingService {
    void create(List<BookingRequest> bookingRequests, long cityId, Authentication authentication);
    PageableDTO<BookingDTO> findUserBookings(int page, int size, Authentication authentication);
}
