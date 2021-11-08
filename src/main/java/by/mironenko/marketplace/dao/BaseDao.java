package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Entity;
import by.mironenko.marketplace.exceptions.DaoException;

import java.util.List;

public interface BaseDao <T extends Entity, K> extends Dao<T> {

    List<T> findAll() throws DaoException;
    T findById(K id) throws DaoException;
    void delete(K id) throws DaoException;
    void create(T t) throws DaoException;
    void update(T t) throws DaoException;

}
