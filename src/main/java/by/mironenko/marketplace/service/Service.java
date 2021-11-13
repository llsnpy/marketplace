package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Entity;
import by.mironenko.marketplace.exceptions.ServiceException;

import java.util.List;

public interface Service<T extends Entity> {

    void create(T t) throws ServiceException;
    List<T> findAll() throws ServiceException;
    T findById(Long id) throws ServiceException;
    void update(T t) throws ServiceException;
    void delete(Long id) throws ServiceException;

}
