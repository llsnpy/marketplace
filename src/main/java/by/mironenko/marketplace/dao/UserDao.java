package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.User;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.dao.postgresql.UserDaoImpl
 * @see DaoFactory
 * The interface describes additional methods for working with user.
 */
public interface UserDao extends BaseDao<User> {

    User findUserByLogin(String login);
    Long getId(String login);

}
