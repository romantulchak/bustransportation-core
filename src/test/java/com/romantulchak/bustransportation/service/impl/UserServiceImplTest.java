package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.exception.TripNotFoundException;
import com.romantulchak.bustransportation.model.Trip;
import com.romantulchak.bustransportation.repository.TripRepository;
import com.romantulchak.bustransportation.repository.UserRepository;
import com.romantulchak.bustransportation.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TripRepository tripRepository;

    UserService userService;


    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository,tripRepository);
    }

    @Test
    public void addUserToSeat() {
        Trip trip = new Trip();
        trip.setId(22);
        when(tripRepository.findById(22L)).thenReturn(Optional.of(trip));

        assertEquals(22, trip.getId());

    }
}