package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Game;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.service.impl.GameServiceImpl
 * The interface describes methods for forking with data from table "game"
 */
public interface GameService extends Service<Game> {

    Game findByGameName(String gameName);
    List<Game> findByDeveloperName(String developerName);
    List<Game> findByPreSaleStatus(Boolean status);
    List<Game> findByPrice(Double price);
    List<Game> sortByPrice();
    List<Game> findByBuyerID(Long id);
    List<Game> findByDeveloperId(Long id);
    Long getDeveloperId(Long id);
}
