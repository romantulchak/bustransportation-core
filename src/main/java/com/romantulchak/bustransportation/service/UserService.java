package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.model.Seat;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.model.User;

import java.util.List;

public interface UserService {
    void addUserToSeat(List<Seat> seats, long tripId);
}
