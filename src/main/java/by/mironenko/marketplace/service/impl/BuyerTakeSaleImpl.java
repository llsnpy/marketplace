package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.BuyerWithSaleDao;
import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.entity.BuyersWithSale;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.BuyerTakeSale;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class BuyerTakeSaleImpl implements BuyerTakeSale {
    private static final Logger log = LogManager.getLogger(BuyerTakeSaleImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    @Override
    public void create(final BuyersWithSale buyersWithSale) {
        log.info("<-SERVICE-> Creating info about buyer with sale...");
        if (buyersWithSale == null) {
            throw new ServiceException("Incorrect input parameters for creating buyers with sale.");
        }
        try {
            final BuyerWithSaleDao dao = factory.getBuyerWithSaleDao();
            dao.create(buyersWithSale);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<BuyersWithSale> findAll() {
        log.info("<-SERVICE-> Finding all buyers with sale...");
        try {
            final BuyerWithSaleDao dao = factory.getBuyerWithSaleDao();
            return dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public BuyersWithSale findById(final Long id) {
        log.info("<-SERVICE-> Finding by ID node about buyer with sale...");
        if (id < 1) {
            throw new ServiceException("Incorrect ID for finding buyer with sale by ID.");
        }
        try {
            final BuyerWithSaleDao dao = factory.getBuyerWithSaleDao();
            return dao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final BuyersWithSale buyersWithSale) {
        log.info("<-SERVICE-> Updating info about buyer with sale...");
        throw new UnsupportedOperationException("Can't update info about buyer with sale.");
    }

    @Override
    public void delete(final Long id) {
        log.info("<-SERVICE-> Deleting info about buyer with sale...");
        throw new UnsupportedOperationException("Can't delete info about buyer with sale.");
    }
}
