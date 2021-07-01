package com.romantulchak.bustransportation.utility;

import com.romantulchak.bustransportation.dto.BusDTO;
import com.romantulchak.bustransportation.model.Bus;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class EntityMapper {

    public BusDTO busToDTO(Bus bus){
        BusDTO busDTO = new BusDTO();
        Field[] busDTOFields = BusDTO.class.getDeclaredFields();
        List<Field> busFields = new LinkedList<>(Arrays.asList(Bus.class.getDeclaredFields()));
        for (Field field : busDTOFields) {
            handleExistsField(bus, busDTO, busFields, field);
        }
        return busDTO;

    }

    private void handleExistsField(Bus bus, BusDTO busDTO, List<Field> busFields, Field field) {
        for (Field field1 : busFields) {
            try {
                field1.setAccessible(true);
                if(field.getName().equals(field1.getName())){
                    Object o = field1.get(bus);
                    busFields.remove(field1);
                    if(o != null){
                        field.setAccessible(true);
                        field.set(busDTO, o);
                    }
                    break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
