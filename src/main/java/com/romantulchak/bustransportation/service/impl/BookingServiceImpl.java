package com.romantulchak.bustransportation.service.impl;

import com.mapperDTO.mapper.EntityMapperInvoker;
import com.romantulchak.bustransportation.dto.BookingDTO;
import com.romantulchak.bustransportation.dto.PageableDTO;
import com.romantulchak.bustransportation.exception.RouteNotFoundException;
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
    private final SeatRepository seatRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final TicketRepository ticketRepository;
    private final EntityMapperInvoker<Booking, BookingDTO> entityMapperInvoker;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              RouteRepository routeRepository,
                              SeatRepository seatRepository,
                              UserRepository userRepository,
                              TicketRepository ticketRepository,
                              EntityMapperInvoker<Booking, BookingDTO> entityMapperInvoker) {
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
        this.routeRepository = routeRepository;
        this.entityMapperInvoker = entityMapperInvoker;
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }


    //TODO: add a check when the user tries to book a seat
    @Transactional
    @Override
    public void create(List<BookingRequest> bookingRequests, long routeId, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findUserByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);
        Route route = routeRepository.findById(routeId).orElseThrow(RouteNotFoundException::new);
        List<Ticket> tickets = new ArrayList<>();
        Set<Integer> bookedSeats = new TreeSet<>();
        for (BookingRequest bookingRequest : bookingRequests) {
//            if (seatRepository.isSeatBooked(bookingRequest.getSeat().getId(), route.getDirectionTo()).isEmpty()) {
                Booking booking = bookingRepository.save(new Booking(user, bookingRequests.size()));
                Ticket ticket = new Ticket(bookingRequest.getFirstName(), bookingRequest.getLastName(), bookingRequest.getEmail(), bookingRequest.getSeat(), booking, route);
                tickets.add(ticket);
//            } else {
//                bookedSeats.add(bookingRequest.getSeat().getSeatNumber());
//            }
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
