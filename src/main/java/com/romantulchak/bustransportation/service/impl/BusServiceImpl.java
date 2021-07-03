package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.exception.BusAlreadyExistException;
import com.romantulchak.bustransportation.exception.BusNotFoundException;
import com.romantulchak.bustransportation.exception.UserNotFoundException;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.User;
import com.romantulchak.bustransportation.repository.BusRepository;
import com.romantulchak.bustransportation.repository.UserRepository;
import com.romantulchak.bustransportation.service.BusService;
import com.romantulchak.bustransportation.utility.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.romantulchak.bustransportation.utility.UserUtility.userInSystem;

@Service
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;
    private final UserRepository userRepository;

    @Autowired
    public BusServiceImpl(BusRepository busRepository, UserRepository userRepository){
        this.busRepository = busRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BusDTO create(Bus bus, Authentication authentication) {
        if(bus != null){
            UserDetailsImpl userDetails = userInSystem(authentication);
            User user = userRepository.findById(userDetails.getId()).orElseThrow(UserNotFoundException::new);
            if(!busRepository.existsBusByName(bus.getName())) {
                bus.setUser(user);
                busRepository.save(bus);
                return convertToDTO(bus);
            }
            throw new BusAlreadyExistException(bus.getName());
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
        return busRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusDTO> findBusesForUser(Authentication authentication) {
        UserDetailsImpl userDetails = userInSystem(authentication);
        return busRepository.findBusesByUserId(userDetails.getId())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private BusDTO convertToDTO(Bus bus){
        EntityMapper entityMapper = new EntityMapper();
        return entityMapper.busToDTO(bus);
    }


}
