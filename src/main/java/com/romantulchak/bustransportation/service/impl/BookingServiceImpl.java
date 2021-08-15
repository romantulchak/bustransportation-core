package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.BookingDTO;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.exception.CityNotFoundException;
import com.romantulchak.bustransportation.exception.SeatsAlreadyBookedException;
import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.model.*;
import com.romantulchak.bustransportation.payload.request.BookingRequest;
import com.romantulchak.bustransportation.repository.*;
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
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final EntityMapperInvoker<Booking, BookingDTO> entityMapperInvoker;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              CityRepository cityRepository,
                              SeatRepository seatRepository,
                              UserRepository userRepository,
                              TicketRepository ticketRepository,
                              EntityMapperInvoker<Booking, BookingDTO> entityMapperInvoker) {
        this.bookingRepository = bookingRepository;
        this.cityRepository = cityRepository;
        this.seatRepository = seatRepository;
        this.entityMapperInvoker = entityMapperInvoker;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }


    @Transactional
    @Override
    public void create(List<BookingRequest> bookingRequests, long cityId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        City city = cityRepository.findById(cityId).orElseThrow(CityNotFoundException::new);
        List<Ticket> tickets = new ArrayList<>();
        Set<Integer> bookedSeats = new TreeSet<>();
        for (BookingRequest bookingRequest : bookingRequests) {
            if(seatRepository.isSeatBooked(bookingRequest.getSeat().getId(), city.getDirection().getDirectionFrom()).isEmpty()){
                Booking booking = bookingRepository.save(new Booking(city, user, bookingRequests.size()));
                Ticket ticket = new Ticket(bookingRequest.getFirstName(), bookingRequest.getLastName(), bookingRequest.getEmail(), bookingRequest.getSeat(), booking);
                tickets.add(ticket);
            }else{
                bookedSeats.add(bookingRequest.getSeat().getSeatNumber());
            }
        }
        checkBookedSeats(bookedSeats);
        ticketRepository.saveAll(tickets);
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
