package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;

public interface DeveloperService extends Service<Developer> {

    Developer findByName(String developerName);
    void createNewGame(Game game);
}
