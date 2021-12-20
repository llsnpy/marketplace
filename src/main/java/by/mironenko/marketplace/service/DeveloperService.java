package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Developer;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.service.impl.DeveloperServiceImpl
 * The interface describes methods for forking with data from table "developer"
 */
public interface DeveloperService extends Service<Developer> {

    Developer findByName(String developerName);
    List<Developer> sortByRating();
}
