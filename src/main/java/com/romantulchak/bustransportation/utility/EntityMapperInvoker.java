package com.romantulchak.bustransportation.utility;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.dto.CityDTO;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.City;
import com.romantulchak.bustransportation.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public final class EntityMapperInvoker {

    private EntityMapper entityMapper;

    public BusDTO busToDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        Field[] busDTOFields = BusDTO.class.getDeclaredFields();
        List<Field> busFields = new LinkedList<>(Arrays.asList(Bus.class.getDeclaredFields()));
        entityMapper.handleFields(bus, busFields, busDTO, busDTOFields);
        return busDTO;
    }

    public TripDTO tripToDTO(Trip trip) {
        TripDTO tripDTO = new TripDTO();
        Field[] tripDTOFields = TripDTO.class.getDeclaredFields();
        List<Field> tripFields = new LinkedList<>(Arrays.asList(Trip.class.getDeclaredFields()));
        entityMapper.handleFields(trip, tripFields, tripDTO, tripDTOFields);
        return tripDTO;
    }


    public CityDTO cityToDTO(City trip) {
        CityDTO cityDTO = new CityDTO();
        Field[] cityDTOFields = CityDTO.class.getDeclaredFields();
        List<Field> cityFields = new LinkedList<>(Arrays.asList(City.class.getDeclaredFields()));
        entityMapper.handleFields(trip, cityFields, cityDTO, cityDTOFields);
        return cityDTO;
    }


    @Autowired
    public void setEntityMapper(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }
}
