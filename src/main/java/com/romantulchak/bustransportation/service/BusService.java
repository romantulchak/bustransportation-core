package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;

import java.util.List;

public interface BusService{

    List<BusDTO> getBuses();
    void addDirection(Bus bus, List<Direction> directions);

}
