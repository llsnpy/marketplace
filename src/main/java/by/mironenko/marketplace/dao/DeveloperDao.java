package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.exceptions.DaoException;

public interface DeveloperDao extends BaseDao<Developer, Long> {

    Developer findByName(String name) throws DaoException;
}
