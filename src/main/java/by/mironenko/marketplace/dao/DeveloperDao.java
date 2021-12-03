package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Developer;

import java.util.List;

public interface DeveloperDao extends BaseDao<Developer> {

    Developer findByName(String name);
    List<Developer> sortByRating();
}
