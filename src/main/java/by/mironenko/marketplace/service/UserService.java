package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.User;

public interface UserService extends Service<User> {

    User findByLogin(String login);
    boolean isExist(String login,String password);
}
