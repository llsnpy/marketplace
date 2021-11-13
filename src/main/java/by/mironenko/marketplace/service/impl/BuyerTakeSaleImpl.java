package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.BuyerWithSaleDao;
import by.mironenko.marketplace.dao.KeysForDao;
import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.entity.BuyersWithSale;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.exceptions.TransactionException;
import by.mironenko.marketplace.service.BuyerTakeSale;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class BuyerTakeSaleImpl implements BuyerTakeSale {
    private static final Logger log = Logger.getLogger(BuyerTakeSaleImpl.class);

    private final Transaction transaction;

    @Override
    public void create(final BuyersWithSale buyersWithSale) throws ServiceException {
        log.info("<-SERVICE-> Creating info about buyer with sale...");
        try {
            BuyerWithSaleDao dao = transaction.createDao(KeysForDao.BUYERS_WITH_SALE_DAO);
            dao.create(buyersWithSale);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<BuyersWithSale> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all buyers with sale...");
        try {
            BuyerWithSaleDao dao = transaction.createDao(KeysForDao.BUYERS_WITH_SALE_DAO);
            return dao.findAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //todo наверное нужно сделать отдельный метод для возврата buyer
    @Override
    public BuyersWithSale findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding by ID node about buyer with sale...");
        try {
            BuyerWithSaleDao dao = transaction.createDao(KeysForDao.BUYERS_WITH_SALE_DAO);
            return dao.findById(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //todo делать ли throw или просто new
    @Override
    public void update(final BuyersWithSale buyersWithSale) throws ServiceException {
        log.info("<-SERVICE-> Updating info about buyer with sale...");
        throw new UnsupportedOperationException("Can't update info about buyer with sale.");
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting info about buyer with sale...");
        throw new UnsupportedOperationException("Can't delete info about buyer with sale.");
    }
}
