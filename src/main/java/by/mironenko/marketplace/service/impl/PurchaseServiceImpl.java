package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.*;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.entity.ShopList;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private static final Logger log = LogManager.getLogger(PurchaseServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    @Override
    public void create(final ShopList shopList) {
        log.info("<-SERVICE-> Creating new bill...");
        if (shopList == null) {
            throw new ServiceException("Cant define shop list for creating.");
        }
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            shopListDao.create(shopList);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ShopList> findAll() {
        log.info("<-SERVICE-> Finding all bills...");
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            return shopListDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ShopList findById(final Long id) {
        log.info("<-SERVICE-> Finding bill by ID...");
        if (id <= 0) {
            throw new ServiceException("Incorrect ID for finding bill by ID.");
        }
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            return shopListDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ShopList> findByBuyerId(final Long buyerId) {
        log.info("<-SERVICE-> Finding bill by buyer ID...");
        if (buyerId <= 0) {
            throw new ServiceException("Incorrect ID for finding bill by ID.");
        }
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            return shopListDao.selectByBuyerId(buyerId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final ShopList shopList) {
        log.info("<-SERVICE-> Finding bill by ID...");
        throw new UnsupportedOperationException("Cant update the bill.");
    }

    @Override
    public void delete(final Long id) {
        log.info("<-SERVICE-> Deleting bill by ID...");
        throw new UnsupportedOperationException("Can't delete the bill.");
    }

    @Override
    public List<ShopList> findBillsByDate(final Date date) {
        log.info("<-SERVICE-> Finding bills by date...");
        if (date == null) {
            throw new ServiceException("Incorrect date for finding bill by date.");
        }
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            return shopListDao.findByDate(date);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ShopList> findBillsByPrice(final Double price) {
        log.info("<-SERVICE-> Finding bills by price...");
        if (price <= 0.0) {
            throw new ServiceException("Incorrect price for finding bill by price.");
        }
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            return shopListDao.findByPrice(price);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Long findMaxId() {
        log.info("<-SERVICE-> Finding max ID...");
        try {
            final ShopListDao shopListDao = factory.getShopListDao();
            return shopListDao.findLastId();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
