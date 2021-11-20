package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Entity;

import java.util.List;

public interface BaseDao <T extends Entity> {

    List<T> findAll();
    T findById(Long id);
    void delete(Long id);
    void create(T t);
    void update(T t);

}
