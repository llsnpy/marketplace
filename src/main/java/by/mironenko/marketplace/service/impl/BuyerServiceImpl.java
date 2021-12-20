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

/**
 * @author Pavel Mironenko
 * @see BuyerService
 * Invoke method for working with BuyerDao
 */
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private static final Logger log = LogManager.getLogger(BuyerServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    /**
     * @param buyer input parameter from controller (entity Buyer)
     * Invoke method from DAO to creating new node in database
     */
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

    /**
     * @return list of buyers from database
     * Invoke method from DAO to finding all buyers
     */
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

    /**
     * @return sorted by money list of buyers from database
     * Invoke method from DAO to finding all buyers sorted by money
     */
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

    /**
     * @param id input parameter buyerId
     * @return sought buyer (by ID)
     * Invoke method from DAO to finding buyer by ID
     */
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

    /**
     * @param buyer input parameter from controller (entity Buyer)
     * Invoke method from DAO to updating the node in database
     */
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

    /**
     * @param id input parameter buyerId
     * Invoke method from DAO to deleting the node from database by ID
     */
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

    /**
     * @param surname input parameter surname
     * @return sought buyer (by surname)
     * Invoke method from DAO to finding buyer by surname
     */
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
