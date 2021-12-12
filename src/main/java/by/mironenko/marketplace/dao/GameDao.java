package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Game;

import java.util.List;

public interface GameDao extends BaseDao<Game> {

    Game findByName(String name);
    List<Game> findByDeveloper(String developerName);
    List<Game> findGameByPreSaleStatus(boolean preSaleStatus);
    List<Game> findByPrice(double price);
    Long getDeveloperId(Long id);
    List<Game> sortByPrice();
    List<Game> findByBuyerId(Long id);
    List<Game> findByDeveloperId(Long id);
}
