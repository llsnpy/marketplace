package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.UserDao;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

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

    @Override
    public boolean isExist(final String login, final String password) {
        return findByLogin(login).getLogin().equals(login) && findByLogin(login).getPassword().equals(password);
    }
}
