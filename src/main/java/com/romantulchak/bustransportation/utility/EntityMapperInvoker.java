package com.romantulchak.bustransportation.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.romantulchak.bustransportation.utility.ClassUtility.newInstanceOfType;

@Component
public final class EntityMapperInvoker<D, E> {

    private EntityMapper entityMapper;

    @SuppressWarnings("unchecked")
    public D entityToDTO(E entity, Class<?> dtoClass, Class<?> classToCheck){
        D dto = (D) newInstanceOfType(dtoClass);
        Field[] dtoFields = dto.getClass().getDeclaredFields();
        List<Field> entityFields = new LinkedList<>(Arrays.asList(entity.getClass().getDeclaredFields()));
        entityMapper.setClassToCheck(classToCheck);
        entityMapper.handleFields(entity, entityFields, dto, dtoFields);
        return dto;
    }

    @Autowired
    public void setEntityMapper(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }
}
