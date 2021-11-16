package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.KeysForDao;
import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.dao.UserDao;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.exceptions.TransactionException;
import by.mironenko.marketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    @Override
    public void create(final User user) throws ServiceException {
        log.info("<-SERVICE-> Creating new user...");
        try {
            final UserDao userDao = factory.getUserDao();
            userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all users...");
        try {
            final UserDao userDao = factory.getUserDao();
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding user by ID...");
        try {
            final UserDao userDao = factory.getUserDao();
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final User user) throws ServiceException {
       log.info("<-SERVICE-> Updating the user...");
       try {
           final UserDao userDao = factory.getUserDao();
           userDao.update(user);
       } catch (DaoException e) {
           throw new ServiceException(e.getMessage(), e);
       }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting the user...");
        try {
            final UserDao userDao = factory.getUserDao();
            userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
