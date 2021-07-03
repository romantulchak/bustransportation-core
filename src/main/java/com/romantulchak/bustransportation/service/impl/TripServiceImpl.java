package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.exception.DirectionNotFoundException;
import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.repository.TripRepository;
import com.romantulchak.bustransportation.service.CrudService;
import com.romantulchak.bustransportation.service.TripService;
import com.romantulchak.bustransportation.utility.EntityMapper;
import com.romantulchak.bustransportation.utility.UserUtility;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.romantulchak.bustransportation.repository.SeatRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.romantulchak.bustransportation.utility.UserUtility.*;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;
    public TripServiceImpl(TripRepository tripRepository, SeatRepository seatRepository){
        this.tripRepository = tripRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    @Override
    public TripDTO create(Trip trip, Authentication authentication) {
        if(trip != null){
            UserDetailsImpl userDetails = userInSystem(authentication);
            if (trip.getBus() != null){
                if(trip.getDirection() != null){
                    trip.getDirection().setDirection(trip.getDirection().getDirectionFrom() + "-" + trip.getDirection().getDirectionTo());
                    User user = new User();
                    user.setId(userDetails.getId());
                    trip.setCreator(user);
                    tripRepository.save(trip);
                    List<Seat> seats = initSeats(trip);
                    seatRepository.saveAll(seats);
                    return convertToDTO(trip);
                }
                throw new DirectionNotFoundException();
            }
            throw new BusNotFoundException();
        }
        throw new TripNotFoundException();
    }

    private List<Seat> initSeats(Trip trip) {
        List<Seat> seats = new ArrayList<>(trip.getNumberOfSeats());
        for (int numberOfSeat = 1; numberOfSeat <= trip.getNumberOfSeats(); numberOfSeat++){
            seats.add(new Seat(numberOfSeat,trip));
        }
        return seats;
    }

    @Override
    public void edit(Trip entity) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public TripDTO getById(long id) {
        return tripRepository
                .findById(id)
                .map(this::convertToDTO)
                .orElseThrow(TripNotFoundException::new);
    }

    @Override
    public List<TripDTO> getTrips() {
        return tripRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> getTripsByDate(String date, int numberOfSeats, String directionFrom, String directionTo) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(date,dateTimeFormatter);
        return tripRepository.findTripsByDate(localDateTime, numberOfSeats, directionFrom, directionTo)
                .stream()
                .map(this::convertToDTO)
                .filter(x-> x.getDate().isAfter(localDateTime) || x.getDate().isEqual(localDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public List<TripDTO> getTripsForUser(Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        return tripRepository.findTripsForUser(userDetails.getId())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TripDTO convertToDTO(Trip trip){
        EntityMapper entityMapper = new EntityMapper();
        return entityMapper.tripToDTO(trip);
    }
}
