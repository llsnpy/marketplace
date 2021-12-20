package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Developer;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.dao.postgresql.DeveloperDaoImpl
 * @see DaoFactory
 * The interface describes additional methods for working with developer.
 */
public interface DeveloperDao extends BaseDao<Developer> {

    Developer findByName(String name);
    List<Developer> sortByRating();
}
