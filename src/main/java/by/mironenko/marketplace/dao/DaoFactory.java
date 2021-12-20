package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.dao.postgresql.*;

/**
 * @author Pavel Mironenko
 * Factory class for working with DAO.
 * Singleton.
 */
public class DaoFactory {
    private static final DaoFactory instance = new DaoFactory();

    private final BuyerDao buyerDao = new BuyerDaoImpl();
    private final BuyerWithSaleDao buyerWithSaleDao = new BuyerWithSaleDaoImpl();
    private final DeveloperDao developerDao = new DeveloperDaoImpl();
    private final GameDao gameDao = new GameDaoImpl();
    private final ShopListDao shopListDao = new ShopListDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

    private DaoFactory() { }

    public static DaoFactory getInstance() {
        return instance;
    }

    public BuyerDao getBuyerDao() {
        return buyerDao;
    }

    public BuyerWithSaleDao getBuyerWithSaleDao() {
        return buyerWithSaleDao;
    }

    public DeveloperDao getDeveloperDao() {
        return developerDao;
    }

    public GameDao getGameDao() {
        return gameDao;
    }

    public ShopListDao getShopListDao() {
        return shopListDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }
}
