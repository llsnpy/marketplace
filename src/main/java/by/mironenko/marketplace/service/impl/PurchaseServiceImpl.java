package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.*;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.entity.ShopList;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.exceptions.TransactionException;
import by.mironenko.marketplace.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private static final Logger log = Logger.getLogger(PurchaseServiceImpl.class);

    private final Transaction transaction;

    @Override
    public void create(final ShopList shopList) throws ServiceException {
        log.info("<-SERVICE-> Creating new bill...");
        try {
            final ShopListDao shopListDao = transaction.createDao(KeysForDao.SHOP_LIST_DAO);
            shopListDao.create(shopList);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ShopList> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all bills...");
        try {
            final ShopListDao shopListDao = transaction.createDao(KeysForDao.SHOP_LIST_DAO);
            return shopListDao.findAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ShopList findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding bill by ID...");
        try {
            final ShopListDao shopListDao = transaction.createDao(KeysForDao.SHOP_LIST_DAO);
            return shopListDao.findById(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final ShopList shopList) throws ServiceException {
        log.info("<-SERVICE-> Finding bill by ID...");
        throw new UnsupportedOperationException("Cant update the bill.");
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting bill by ID...");
        throw new UnsupportedOperationException("Can't delete the bill.");
    }

    @Override
    public List<ShopList> findBillsByDate(final Date date) throws ServiceException {
        log.info("<-SERVICE-> Finding bills by date...");
        try {
            final ShopListDao shopListDao = transaction.createDao(KeysForDao.SHOP_LIST_DAO);
            return shopListDao.findByDate(date);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ShopList> findBillsByPrice(final Double price) throws ServiceException {
        log.info("<-SERVICE-> Finding bills by price...");
        try {
            final ShopListDao shopListDao = transaction.createDao(KeysForDao.SHOP_LIST_DAO);
            return shopListDao.findByPrice(price);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void buyGame(final Long buyerId, final Long gameId) throws ServiceException {
        log.info("<-SERVICE-> The buyer buy the game...");
        try {
            BuyerDao buyerDao = transaction.createDao(KeysForDao.BUYER_DAO);
            GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            ShopListDao shopListDao = transaction.createDao(KeysForDao.SHOP_LIST_DAO);
            DeveloperDao developerDao = transaction.createDao(KeysForDao.DEVELOPER_DAO);
            ShopList shopList = new ShopList();
            Buyer buyer = buyerDao.findById(buyerId);
            Game game = gameDao.findById(gameId);
            if (buyer.getMoney() > game.getPrice()) {
                shopList.setBuyerId(buyer.getId());
                shopList.setGameId(game.getId());
                java.util.Date date = new java.util.Date();
                shopList.setDate(date);
                shopList.setPrice(game.getPrice());
                shopListDao.create(shopList);
                //todo здесь же проверку на ту цену, которая должна быть
                //todo списание и зачисление денег на счет (update методы)
                //todo увеличение рейтинга
                //todo проверка на возраст (создать утилку?)
                //todo цену брать из списка предзаказов



            } else {
                throw new ServiceException("Buyer with ID " + buyer.getId() + " haven't money for buying game " + game.getId());
            }
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
