package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;

import java.util.List;

public interface GameDao extends BaseDao<Game> {

    Game findByName(String name) throws DaoException;
    List<Game> findByDeveloper(String developerName) throws DaoException;
    List<Game> findGameByPreSaleStatus(boolean preSaleStatus) throws DaoException;
    List<Game> findByPrice(double price) throws DaoException;
    Long getDeveloperId(Long id) throws DaoException;
}
