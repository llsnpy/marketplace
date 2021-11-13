package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.ServiceException;

public interface DeveloperService extends Service<Developer> {

    Developer findByName(String developerName) throws ServiceException;
    void createNewGame(Game game) throws ServiceException;
}
