package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Entity;
import by.mironenko.marketplace.exceptions.DaoException;

import java.util.List;

public interface BaseDao <T extends Entity> {

    List<T> findAll() throws DaoException;
    T findById(Long id) throws DaoException;
    void delete(Long id) throws DaoException;
    void create(T t) throws DaoException;
    void update(T t) throws DaoException;

}
