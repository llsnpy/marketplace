package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.BuyerDao;
import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private static final Logger log = Logger.getLogger(BuyerServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    @Override
    public void create(final Buyer buyer) throws ServiceException {
        log.info("<-SERVICE-> Creating new buyer...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            buyerDao.create(buyer);
        } catch ( DaoException e) {/* TransactionException |*/
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Buyer> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all buyers...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Buyer findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding buyer by ID...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Buyer buyer) throws ServiceException {
        log.info("<-SERVICE-> Updating buyer...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            buyerDao.update(buyer);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting buyer by ID...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            buyerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Buyer findBySurname(final String surname) throws ServiceException {
        log.info("<-SERVICE-> Finding buyer by surname...");
        try {
            final BuyerDao buyerDao = factory.getBuyerDao();
            return buyerDao.findBySurname(surname);
        } catch ( DaoException e) { /*TransactionException |*/
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
