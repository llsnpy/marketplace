package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.User;

public interface UserDao extends BaseDao<User> {

    User findUserByLogin(String login);
    Long getId(String login);

}
