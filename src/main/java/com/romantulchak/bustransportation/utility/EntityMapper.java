package com.romantulchak.bustransportation.utility;

import com.romantulchak.bustransportation.anotations.MapToDTO;
import com.romantulchak.bustransportation.exception.CreateClassInstanceException;
import org.hibernate.Hibernate;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static com.romantulchak.bustransportation.utility.ClassUtility.newInstanceOfType;

@Component
public final class EntityMapper {
    private final Collection<Class<?>> collections = new ArrayList<>();
    private Class<?> classToCheck;

    public EntityMapper() {
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
                if (field.getName().equals(busFieldOk.getName()) && checkAnnotation(field, classToCheck)) {
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

    private boolean checkAnnotation(Field field, Class<?> classToCheck) {
        MapToDTO declaredAnnotation = field.getDeclaredAnnotation(MapToDTO.class);
        if (declaredAnnotation != null && declaredAnnotation.mapClass().length != 0) {
            List<Class<?>> classes = Arrays.asList(declaredAnnotation.mapClass());
            return field.isAnnotationPresent(MapToDTO.class) && classes.contains(classToCheck);
        }
        return false;
    }

    private boolean isDTOField(Field field) {
        if (field.getGenericType() instanceof ParameterizedType) {
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            return genericType.getActualTypeArguments()[0].getTypeName().endsWith("DTO");
        }
        return field.getType().getTypeName().endsWith("DTO");
    }

    private <T> Object setInternalDTOFields(T entity, Class<?> type, Field actualField) {
        Collection<?> typeOfCollection = getTypeOfCollection(entity);
        if (typeOfCollection != null) {
            return mapPersistentBagOfElements(typeOfCollection, type, actualField);
        } else {
            return mapSingleObject(entity, type);
        }
    }



    private <T> boolean checkObjectInstance(T entity, Class<?> obj) {
        return obj.isInstance(entity);
    }

    private <T> Object mapSingleObject(T entity, Class<?> type) {
        List<Field> entityFields = new LinkedList<>(Arrays.asList(entity.getClass().getDeclaredFields()));
        Object internalDto = newInstanceOfType(type);
        handleFields(entity, entityFields, internalDto, type.getDeclaredFields());
        return internalDto;
    }

    private Collection<Object> mapPersistentBagOfElements(Collection<?> entity, Class<?> type, Field actualField) {
        Object internalDto;
        if (Hibernate.isInitialized(entity) && !entity.isEmpty()) {
            ParameterizedType genericType = (ParameterizedType) actualField.getGenericType();
            Collection<Object> collection = newCollectionInstance(type);
            type = (Class<?>) genericType.getActualTypeArguments()[0];
            for (Object object : entity) {
                List<Field> entityFields = new LinkedList<>(Arrays.asList(entity
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Collection is empty")).getClass().getDeclaredFields()));
                internalDto = newInstanceOfType(type);
                handleFields(object, entityFields, internalDto, type.getDeclaredFields());
                collection.add(internalDto);
            }
            return collection;
        }
        return Collections.emptyList();
    }

    public <T> void handleFields(T entity, List<Field> entityFields, Object internalDto, Field[] declaredFields) {
        for (Field field : declaredFields) {
            handleExistsField(entity, internalDto, entityFields, field);
        }
    }


    private Collection<Object> newCollectionInstance(Class<?> clazz) {
        if (clazz.isAssignableFrom(List.class)) {
            return new ArrayList<>();
        } else if (clazz.isAssignableFrom(Set.class)) {
            return new HashSet<>();
        } else if (clazz.isAssignableFrom(Queue.class)) {
            return new PriorityQueue<>();
        }
        throw new CreateClassInstanceException(clazz.getTypeName());
    }

    private <T> Collection<?> getTypeOfCollection(T entity) {
        if (checkObjectInstance(entity, PersistentBag.class)) {
            return (PersistentBag) entity;
        } else if (checkObjectInstance(entity, List.class)) {
            return (List<?>) entity;
        } else if (checkObjectInstance(entity, Set.class)) {
            return (Set<?>) entity;
        }
        return null;
    }

    public Class<?> getClassToCheck() {
        return classToCheck;
    }

    public void setClassToCheck(Class<?> classToCheck) {
        this.classToCheck = classToCheck;
    }
}
