package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Entity;

import java.util.List;

/**
 * @param <T> specific entity class
 * The interface describe common methods for forking with entity in database
 */
public interface BaseDao <T extends Entity> {

    List<T> findAll();
    T findById(Long id);
    void delete(Long id);
    void create(T t);
    void update(T t);

}
