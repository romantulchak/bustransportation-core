package com.romantulchak.bustransportation.service;

import java.util.List;

/**
 *
 * @param <T> Entity
 * @param <R> DTO
 */
public interface CrudService<T, R>{
    R create(T entity);
    void edit(T entity);
    void delete(long id);
    R getById(long id);
}
