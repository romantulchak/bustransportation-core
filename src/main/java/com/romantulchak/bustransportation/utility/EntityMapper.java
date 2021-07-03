package com.romantulchak.bustransportation.utility;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.dto.TripDTO;
import com.romantulchak.bustransportation.model.Bus;
import com.romantulchak.bustransportation.model.Trip;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class EntityMapper {

    public BusDTO busToDTO(Bus bus) {
        BusDTO busDTO = new BusDTO();
        Field[] busDTOFields = BusDTO.class.getDeclaredFields();
        List<Field> busFields = new LinkedList<>(Arrays.asList(Bus.class.getDeclaredFields()));
        for (Field busDTOField : busDTOFields) {
            handleExistsField(bus, busDTO, busFields, busDTOField);
        }
        return busDTO;
    }

    public TripDTO tripToDTO(Trip trip){
        TripDTO tripDTO = new TripDTO();
        Field[] tripDTOFields = TripDTO.class.getDeclaredFields();
        List<Field> tripFields = new LinkedList<>(Arrays.asList(Trip.class.getDeclaredFields()));
        for (Field tripDTOField : tripDTOFields) {
            handleExistsField(trip, tripDTO, tripFields, tripDTOField);
        }
        return tripDTO;
    }

    private <T, R> void handleExistsField(T entity, R dto, List<Field> entityFields, Field field) {
        for (Field busFieldOk : entityFields) {
            try {
                busFieldOk.setAccessible(true);
                if(field.getName().equals(busFieldOk.getName())){
                    Object value = busFieldOk.get(entity);
                    entityFields.remove(busFieldOk);
                    if(value != null){
                        field.setAccessible(true);
                        if (field.getType().getTypeName().endsWith("DTO")) {
                            value = setInternalDTOFields(value, field.getType());
                        }
                        field.set(dto, value);
                    }
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private <T> Object setInternalDTOFields(T entity, Class<?> type){
        List<Field> entityFields = new LinkedList<>(Arrays.asList(entity.getClass().getDeclaredFields()));
        Object internalDto = newInstanceOfType(type);
        for (Field field : type.getDeclaredFields()) {
            handleExistsField(entity, internalDto, entityFields, field);
        }
        return internalDto;
    }

    private Object newInstanceOfType(Class<?> clazz){
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot create new instance");
        }
    }


}
