package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Developer;

public interface DeveloperDao extends BaseDao<Developer> {

    Developer findByName(String name);
}
