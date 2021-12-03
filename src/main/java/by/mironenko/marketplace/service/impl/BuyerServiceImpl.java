package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.BuyerDao;
import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private static final Logger log = LogManager.getLogger(BuyerServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    @Override
    public void create(final Buyer buyer) {
        log.info("<-SERVICE-> Creating new buyer...");
        if (buyer == null || buyer.getAge() < 14 || buyer.getMoney() < 0) {
            throw new ServiceException("Incorrect input parameters for creating buyer.");
        }
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            buyerDao.create(buyer);
        } catch ( DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Buyer> findAll() {
        log.info("<-SERVICE-> Finding all buyers...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Buyer> sortByMoney() {
        log.info("<-SERVICE-> Sort buyers by money...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.sortByMoney();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Buyer findById(final Long id) {
        log.info("<-SERVICE-> Finding buyer by ID...");
        if (id < 1) {
            throw new ServiceException("Incorrect ID for finding buyer by ID.");
        }
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Buyer buyer) {
        log.info("<-SERVICE-> Updating buyer...");
        if (buyer == null) {
            throw new ServiceException("Incorrect ID for updating buyer by ID.");
        }
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            buyerDao.update(buyer);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) {
        log.info("<-SERVICE-> Deleting buyer by ID...");
        if (id < 1) {
            throw new ServiceException("Incorrect ID for deleting buyer by ID.");
        }
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            buyerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Buyer findBySurname(final String surname) {
        log.info("<-SERVICE-> Finding buyer by surname...");
        if (surname == null) {
            throw new ServiceException("Incorrect surname for finding buyer by surname.");
        }
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.findBySurname(surname);
        } catch ( DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
