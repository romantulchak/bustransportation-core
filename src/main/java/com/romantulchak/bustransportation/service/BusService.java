package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;

import java.util.List;

public interface BusService{

    List<BusDTO> getBuses();
   // List<BusDTO> getBusesByDirection(String directionFrom, String directionTo);

}
