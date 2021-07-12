package com.romantulchak.bustransportation.utility;

import java.lang.reflect.InvocationTargetException;

public final class ClassUtility {

    public static Object newInstanceOfType(Class<?> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot create new instance of class " + clazz.getTypeName());
        }
    }
}
