package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.UserDao;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.UserService;
import by.mironenko.marketplace.service.coding.PasswordCoder;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see UserService
 * Invoke method for working with UserDao
 */
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    /**
     * @param user input parameter from controller (entity User)
     * Invoke method from DAO to creating new node in database
     */
    @Override
    public void create(final User user) {
        log.info("<-SERVICE-> Creating new user...");
        if (user == null) {
            throw new ServiceException("Incorrect input parameters for creating user.");
        }
        try {
            final UserDao userDao = factory.getUserDao();
            userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @return list of users from database
     * Invoke method from DAO to finding all users
     */
    @Override
    public List<User> findAll() {
        log.info("<-SERVICE-> Finding all users...");
        try {
            final UserDao userDao = factory.getUserDao();
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter userId
     * @return sought user (by ID)
     * Invoke method from DAO to finding user by ID
     */
    @Override
    public User findById(final Long id) {
        log.info("<-SERVICE-> Finding user by ID...");
        if (id < 1) {
            throw new ServiceException("Incorrect ID for finding user by ID.");
        }
        try {
            final UserDao userDao = factory.getUserDao();
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param user input parameter from controller (entity User)
     * Invoke method from DAO to updating the node in database
     */
    @Override
    public void update(final User user) {
       log.info("<-SERVICE-> Updating the user...");
        if (user == null) {
            throw new ServiceException("Cant define user for updating.");
        }
       try {
           final UserDao userDao = factory.getUserDao();
           userDao.update(user);
       } catch (DaoException e) {
           throw new ServiceException(e.getMessage(), e);
       }
    }

    /**
     * @param id input parameter userId
     * Invoke method from DAO to deleting the node from database by ID
     */
    @Override
    public void delete(final Long id) {
        log.info("<-SERVICE-> Deleting the user...");
        if (id < 1) {
            throw new ServiceException("Cant define ID for deleting user.");
        }
        try {
            final UserDao userDao = factory.getUserDao();
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param login input parameter user login
     * @return sought user (by login)
     * Invoke method from DAO to finding user by login
     */
    @Override
    public User findByLogin(final String login) {
        log.info("<-SERVICE-> Finding user by login...");
        if (login == null) {
            throw new ServiceException("Cant define login for finding user.");
        }
        try {
            final UserDao userDao = factory.getUserDao();
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param login input parameter (user login)
     * @return user ID
     * Invoke method from DAO to finding user ID by user login
     */
    @Override
    public Long findId(final String login) {
        log.info("<-SERVICE-> Finding user ID by login...");
        if (login == null) {
            throw new ServiceException("Cant define login for finding user ID.");
        }
        try {
            final UserDao userDao = factory.getUserDao();
            return userDao.getId(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isExist(final String login, final String password) {
        PasswordCoder pc = new PasswordCoder();
        return findByLogin(login).getLogin().equals(login) & pc.isMatch(password, findByLogin(login).getPassword()); /*findByLogin(login).getPassword().equals(password)*/
    }
}
