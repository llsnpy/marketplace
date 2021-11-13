package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DeveloperDao;
import by.mironenko.marketplace.dao.KeysForDao;
import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.exceptions.TransactionException;
import by.mironenko.marketplace.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private static final Logger log = Logger.getLogger(DeveloperServiceImpl.class);

    private final Transaction transaction;

    @Override
    public void create(final Developer developer) throws ServiceException {
        log.info("<-SERVICE-> Creating new developer...");
        try {
            final DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            developerDao.create(developer);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Developer> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all developers...");
        try {
            final DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            return developerDao.findAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Developer findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding developer by ID...");
        try {
            final DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            return developerDao.findById(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Developer developer) throws ServiceException {
        log.info("<-SERVICE-> Updating developer...");
        try {
            final DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            developerDao.update(developer);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting developer by ID...");
        try {
            final DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            developerDao.delete(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Developer findByName(final String developerName) throws ServiceException {
        log.info("<-SERVICE-> Finding developer by name...");
        try {
            final DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            return developerDao.findByName(developerName);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void createNewGame(final Game game) throws ServiceException {

    }
}
