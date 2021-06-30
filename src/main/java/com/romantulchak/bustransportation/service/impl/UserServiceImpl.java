package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.exception.OccupiedSeatException;
import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.repository.TripRepository;
import com.romantulchak.bustransportation.repository.UserRepository;
import com.romantulchak.bustransportation.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    public UserServiceImpl(UserRepository userRepository, TripRepository tripRepository){
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }


    @Override
    public void addUserToSeat(List<Seat> seats, long tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(TripNotFoundException::new);
        List<Seat> occupiedSeats = new ArrayList<>();
        List<User> users = new ArrayList<>();
        getSeats(seats, trip, occupiedSeats, users);
        if(occupiedSeats.size() > 0){
            throw new OccupiedSeatException(occupiedSeats);
        }
        userRepository.saveAll(users);
        trip.setNumberOfSeats(trip.getNumberOfSeats() - seats.size());
        tripRepository.save(trip);
    }

    private void getSeats(List<Seat> seats, Trip trip, List<Seat> occupiedSeats, List<User> users) {
        if(!trip.getSeats().isEmpty()){
            trip.getSeats().forEach(tripSeat->{
                seats.forEach(seat->{
                    if(tripSeat.getSeatNumber() == seat.getSeatNumber()){
                        if(tripSeat.getUser() == null) {
                            User user = seat.getUser();
                            user.setSeat(seat);
                            users.add(user);
                        }else{
                            occupiedSeats.add(seat);
                        }
                    }
                });
            });
        }
    }
}
