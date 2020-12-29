package com.romantulchak.bustransportation.service;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;

import java.util.List;

public interface BusService {

    List<BusDTO> getBuses();
    void createBus(Bus bus);

}
