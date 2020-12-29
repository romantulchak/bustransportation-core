package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.repository.BusRepository;
import com.romantulchak.bustransportation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;


    @Autowired
    public BusServiceImpl(BusRepository busRepository){
        this.busRepository = busRepository;
    }

    @Override
    public void createBus(Bus bus) {
        if(bus != null){
            busRepository.save(bus);
        }
        throw new BusNotFoundException();
    }

    @Override
    public List<BusDTO> getBuses() {
        return busRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BusDTO convertToDTO(Bus bus){
        return new BusDTO(bus);
    }
}
