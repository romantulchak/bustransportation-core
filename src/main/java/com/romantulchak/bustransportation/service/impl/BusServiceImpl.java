package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.exception.BusAlreadyExistException;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.repository.BusRepository;
import com.romantulchak.bustransportation.service.BusService;
import com.romantulchak.bustransportation.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService, CrudService<Bus, BusDTO> {

    private final BusRepository busRepository;


    @Autowired
    public BusServiceImpl(BusRepository busRepository){
        this.busRepository = busRepository;
    }


    @Override
    public BusDTO create(Bus bus) {
        if(bus != null){
            if(!busRepository.existsBusByBusName(bus.getBusName())) {
                busRepository.save(bus);
                return convertToDTO(bus);
            }
            throw new BusAlreadyExistException(bus.getBusName());
        }
        throw new BusNotFoundException();
    }

    @Override
    public void edit(Bus bus) {
        if(bus != null){
            busRepository.save(bus);
        }else {
            throw new BusNotFoundException();
        }
    }

    @Override
    public void delete(long id) {
        Bus bus = busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        busRepository.delete(bus);
    }

    @Override
    public BusDTO getById(long id) {
        Bus bus = busRepository.findById(id).orElseThrow(BusNotFoundException::new);
        return convertToDTO(bus);
    }

    @Override
    public List<BusDTO> getBuses() {
        return busRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void addDirection(Bus bus, List<Direction> directions) {
        if(bus != null){
            int currentNumberOfDirections = bus.getDirections().size();
            directions.forEach(direction->{
                if(bus.getDirections().stream().noneMatch(x->x.getId() == direction.getId())){
                    bus.getDirections().add(direction);
                }
            });
            if(currentNumberOfDirections < bus.getDirections().size()) {
                busRepository.save(bus);
            }
        }else{
            throw new BusNotFoundException();
        }

    }

    private BusDTO convertToDTO(Bus bus){
        return new BusDTO(bus);
    }
}
