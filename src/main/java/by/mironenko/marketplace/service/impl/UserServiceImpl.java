package by.mironenko.marketplace.service.impl;

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

    private final Transaction transaction;

    @Override
    public void create(final User user) throws ServiceException {
        log.info("<-SERVICE-> Creating new user...");
        try {
            final UserDao userDao = transaction.createDao(KeysForDao.USER_DAO);
            userDao.create(user);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all users...");
        try {
            final UserDao userDao = transaction.createDao(KeysForDao.USER_DAO);
            return userDao.findAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public User findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding user by ID...");
        try {
            final UserDao userDao = transaction.createDao(KeysForDao.USER_DAO);
            return userDao.findById(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final User user) throws ServiceException {
       log.info("<-SERVICE-> Updating the user...");
       try {
           final UserDao userDao = transaction.createDao(KeysForDao.USER_DAO);
           userDao.update(user);
       } catch (TransactionException | DaoException e) {
           throw new ServiceException(e.getMessage(), e);
       }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting the user...");
        try {
            final UserDao userDao = transaction.createDao(KeysForDao.USER_DAO);
            userDao.delete(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
