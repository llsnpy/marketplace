package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Entity;

import java.util.List;

public interface Service<T extends Entity> {

    void create(T t);
    List<T> findAll();
    T findById(Long id);
    void update(T t);
    void delete(Long id);

}
