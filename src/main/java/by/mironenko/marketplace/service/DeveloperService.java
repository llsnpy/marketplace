package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;

import java.util.List;

public interface DeveloperService extends Service<Developer> {

    Developer findByName(String developerName);
    void createNewGame(Game game);
    List<Developer> sortByRating();
}
