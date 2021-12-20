package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.User;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.service.impl.UserServiceImpl
 * The interface describes methods for forking with data from table "users"
 */
public interface UserService extends Service<User> {

    User findByLogin(String login);
    boolean isExist(String login,String password);
    Long findId(final String login);
}
