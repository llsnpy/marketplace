package by.mironenko.marketplace.dao.transaction;

import by.mironenko.marketplace.dao.BaseDao;
import by.mironenko.marketplace.dao.KeysForDao;
import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.dao.postgresql.*;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public class TransactionImpl implements Transaction {
    private static Logger log = LogManager.getLogger(TransactionImpl.class);

    private final Connection connection;

    public <T extends BaseDao<?>> T createDao(final KeysForDao keysForDao) throws TransactionException {
        try {
             switch (keysForDao) {
                case BUYER_DAO:
                    return (T) new BuyerDaoImpl(connection);
                 case DEVELOPER_DAO:
                     return (T) new DeveloperDaoImpl(connection);
                 case GAME_DAO:
                     return (T) new GameDaoImpl(connection);
                 case BUYERS_WITH_SALE_DAO:
                     return (T) new BuyerWithSaleDaoImpl(connection);
                 case SHOP_LIST_DAO:
                     return (T) new ShopListDaoImpl(connection);
                 case USER_DAO:
                     return (T) new UserDaoImpl(connection);
                 default:
                     throw new EnumConstantNotPresentException(
                             keysForDao.getDeclaringClass(), keysForDao.name());
            }
        } catch (Exception e) {
            throw new TransactionException(e.getMessage(), e);
        }
    }

    @Override
    public void commit() throws TransactionException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }
}
