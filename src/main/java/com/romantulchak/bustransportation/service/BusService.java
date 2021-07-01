package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BusService{

    BusDTO create(Bus bus, Authentication authentication);
    void edit(Bus bus);
    void delete(long id);
    BusDTO getById(long id);
    List<BusDTO> getBuses();

    List<BusDTO> findBusesForUser(long userId);
    // List<BusDTO> getBusesByDirection(String directionFrom, String directionTo);

}
