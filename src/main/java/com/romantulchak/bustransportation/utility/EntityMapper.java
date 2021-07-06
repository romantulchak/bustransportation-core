package com.romantulchak.bustransportation.utility;

import com.romantulchak.bustransportation.anotations.MapToDTO;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Component
public class EntityMapper {
    private final Collection<Class<?>> collections = new ArrayList<>();

    public EntityMapper(){
        collections.add(List.class);
        collections.add(Set.class);
        collections.add(Map.class);
        collections.add(Queue.class);
        collections.add(Collection.class);
    }


    private <T, R> void handleExistsField(T entity, R dto, List<Field> entityFields, Field field) {
        for (Field busFieldOk : entityFields) {
            try {
                busFieldOk.setAccessible(true);
                if (field.getName().equals(busFieldOk.getName()) && field.isAnnotationPresent(MapToDTO.class)) {
                    Object value = busFieldOk.get(entity);
                    entityFields.remove(busFieldOk);
                    if (value != null) {
                        field.setAccessible(true);
                        if (isDTOField(field)) {
                            value = setInternalDTOFields(value, field.getType(), field);
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

    private boolean isDTOField(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            return genericType.getActualTypeArguments()[0].getTypeName().endsWith("DTO");
        }
        return field.getType().getTypeName().endsWith("DTO");
    }

    private <T> Object setInternalDTOFields(T entity, Class<?> type, Field actualField) {
        if (entity.getClass().isAssignableFrom(PersistentBag.class)) {
            return mapListOfElements((PersistentBag) entity, type, actualField);
        } else {
            return mapSingleObject(entity, type);
        }
    }

    private <T> Object mapSingleObject(T entity, Class<?> type) {
        List<Field> entityFields = new LinkedList<>(Arrays.asList(entity.getClass().getDeclaredFields()));
        Object internalDto = newInstanceOfType(type);
        handleFields(entity, entityFields, internalDto, type.getDeclaredFields());
        return internalDto;
    }

    private Collection<Object> mapListOfElements(PersistentBag entity, Class<?> type, Field actualField) {
        Object internalDto;
        if (!entity.isEmpty()) {
            ParameterizedType genericType = (ParameterizedType) actualField.getGenericType();
            Collection<Object> collection = newCollectionInstance(type);
            type = (Class<?>) genericType.getActualTypeArguments()[0];
            for (Object object : entity) {
                List<Field> entityFields = new LinkedList<>(Arrays.asList(entity.get(0).getClass().getDeclaredFields()));
                internalDto = newInstanceOfType(type);
                handleFields(object, entityFields, internalDto, type.getDeclaredFields());
                collection.add(internalDto);
            }
            return collection;
        }
        return Collections.emptyList();
    }

    public  <T> void handleFields(T entity, List<Field> entityFields, Object internalDto, Field[] declaredFields) {
        for (Field field : declaredFields) {
            handleExistsField(entity, internalDto, entityFields, field);
        }
    }

    private Object newInstanceOfType(Class<?> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot create new instance of class " + clazz.getTypeName());
        }
    }

    private Collection<Object> newCollectionInstance(Class<?> clazz) {
        if (clazz.isAssignableFrom(List.class)) {
            return new ArrayList<>();
        }else if (clazz.isAssignableFrom(Set.class)){
            return new HashSet<>();
        }else if(clazz.isAssignableFrom(Queue.class)){
            return new PriorityQueue<>();
        }
        throw new RuntimeException("Cannot create new instance of class " + clazz.getTypeName());
    }

    private boolean isAssignableFromCollection(Class<?> clazz) {
        return collections.contains(clazz);
    }

}
