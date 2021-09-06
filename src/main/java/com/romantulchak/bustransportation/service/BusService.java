package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BusService{

    BusDTO create(Bus bus, Authentication authentication);
    void edit(Bus bus);
    void delete(long id);
    BusDTO getById(long id);
    List<BusDTO> getBuses();

    List<BusDTO> findBusesForUser(Authentication authentication);
    // List<BusDTO> getBusesByDirection(String directionFrom, String directionTo);

}
