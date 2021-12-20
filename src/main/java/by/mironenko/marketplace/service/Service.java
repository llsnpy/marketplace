package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Entity;

import java.util.List;

/**
 * @param <T> specific entity class
 * The interface describe common methods for forking with information in database
 */
public interface Service<T extends Entity> {

    void create(T t);
    List<T> findAll();
    T findById(Long id);
    void update(T t);
    void delete(Long id);

}
