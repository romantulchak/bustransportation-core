package com.romantulchak.bustransportation.service.impl;

import com.romantulchak.bustransportation.dto.DirectionDTO;
import com.romantulchak.bustransportation.exception.DirectionAlreadyExistException;
import com.romantulchak.bustransportation.exception.DirectionNotFoundException;
import com.romantulchak.bustransportation.model.Direction;
import com.romantulchak.bustransportation.repository.DirectionRepository;
import com.romantulchak.bustransportation.service.CrudService;
import com.romantulchak.bustransportation.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DirectionServiceImpl implements DirectionService, CrudService<Direction, DirectionDTO> {


    private final DirectionRepository directionRepository;

    @Autowired
    public DirectionServiceImpl(DirectionRepository directionRepository){
        this.directionRepository = directionRepository;
    }

    @Override
    public DirectionDTO create(Direction direction) {
        if(direction != null){
            if(!directionRepository.existsDirectionByDirection(direction.getDirection())){
                directionRepository.save(direction);
                return directionToDTO(direction);
            }else{
                throw new DirectionAlreadyExistException(direction.getDirection());
            }
        }
        throw new DirectionNotFoundException();
    }

    @Override
    public void edit(Direction direction) {
        if(direction != null){
            directionRepository.save(direction);
        }else {
            throw new DirectionNotFoundException();
        }
    }

    @Override
    public void delete(long id) {
        Direction direction = directionRepository.findById(id).orElseThrow(DirectionNotFoundException::new);
        directionRepository.delete(direction);
    }

    @Override
    public DirectionDTO getById(long id) {
        Direction direction = directionRepository.findById(id).orElseThrow(DirectionNotFoundException::new);
        return directionToDTO(direction);
    }

    @Override
    public List<DirectionDTO> getDirections() {
        return directionRepository.findAll().stream().map(this::directionToDTO).collect(Collectors.toList());
    }

    private DirectionDTO directionToDTO(Direction direction){
        return new DirectionDTO(direction);
    }
}
