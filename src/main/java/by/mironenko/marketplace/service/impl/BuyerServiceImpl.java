package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.BuyerDao;
import by.mironenko.marketplace.dao.KeysForDao;
import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Entity;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.exceptions.TransactionException;
import by.mironenko.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private static final Logger log = Logger.getLogger(BuyerServiceImpl.class);

    private final Transaction transaction;

    @Override
    public void create(Entity entity) throws ServiceException {
        log.info("<-SERVICE-> Creating new buyer...");
        try {
            final BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            buyerDao.create((Buyer) entity);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Buyer> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all buyers...");
        try {
            final BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            return buyerDao.findAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Buyer findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding buyer by ID...");
        try {
            final BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            return buyerDao.findById(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Entity entity) throws ServiceException {
        log.info("<-SERVICE-> Updating buyer...");
        try {
            final BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            buyerDao.update((Buyer) entity);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting buyer by ID...");
        try {
            final BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            buyerDao.delete(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Buyer findBySurname(final String surname) throws ServiceException {
        log.info("<-SERVICE-> Finding buyer by surname...");
        try {
            final BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            return buyerDao.findBySurname(surname);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //todo несколько дао в покупке и пресэйле
    @Override
    public boolean buyGame(final Game game) throws ServiceException {
        log.info("<-SERVICE-> Buyer buys the game...");
        return false;
    }

    @Override
    public boolean givePreSale(final boolean preSale) throws ServiceException {
        log.info("<-SERVICE-> Buyer makes a pre order...");
        return false;
    }
}
