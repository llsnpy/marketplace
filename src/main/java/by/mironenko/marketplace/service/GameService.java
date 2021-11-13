package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.ServiceException;

import java.util.List;

public interface GameService extends Service<Game> {

    Game findByGameName(String gameName) throws ServiceException;
    List<Game> findByDeveloperName(String developerName) throws ServiceException;
    List<Game> findByPreSaleStatus(Boolean status) throws ServiceException;
    List<Game> findByPrice(Double price) throws ServiceException;
}
