package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.BookingDTO;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.exception.CityNotFoundException;
import com.romantulchak.bustransportation.exception.SeatsAlreadyBookedException;
import com.romantulchak.bustransportation.model.Booking;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.model.View;
import com.romantulchak.bustransportation.repository.BookingRepository;
import com.romantulchak.bustransportation.repository.CityRepository;
import com.romantulchak.bustransportation.repository.SeatRepository;
import com.romantulchak.bustransportation.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CityRepository cityRepository;
    private final SeatRepository seatRepository;
    private final EntityMapperInvoker<Booking, BookingDTO> entityMapperInvoker;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CityRepository cityRepository,
                              SeatRepository seatRepository,
                              EntityMapperInvoker<Booking, BookingDTO> entityMapperInvoker) {
        this.bookingRepository = bookingRepository;
        this.cityRepository = cityRepository;
        this.seatRepository = seatRepository;
        this.entityMapperInvoker = entityMapperInvoker;
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
        checkBookedSeats(bookedSeats);
        bookingRepository.saveAll(bookings);
    }

    @Override
    public PageableDTO<Collection<BookingDTO>> findUserBookings(int page, int size, Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        Pageable bookingDTOS = PageRequest.of(page, size);
        Page<Booking> bookings = bookingRepository.findBookingByUserId(principal.getId(), bookingDTOS);
        List<BookingDTO> content = bookings.getContent()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new PageableDTO<>(bookings.getTotalPages(), bookings.getTotalElements(), page, content);
    }

    private void checkBookedSeats(Set<Integer> bookedSeats) {
        if (!bookedSeats.isEmpty()) {
            String seats = String.join(",", bookedSeats.toString())
                    .replace("[", "")
                    .replace("]", "");
            throw new SeatsAlreadyBookedException(seats);
        }
    }

    private BookingDTO convertToDTO(Booking booking) {
        return entityMapperInvoker.entityToDTO(booking, BookingDTO.class, View.BookingView.class);
    }


}
