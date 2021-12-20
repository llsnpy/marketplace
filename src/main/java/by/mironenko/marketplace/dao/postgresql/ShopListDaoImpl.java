package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.ShopListDao;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.entity.ShopList;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Pavel Mironenko
 * @see ShopListDao
 * Describes how to access the database and work with the
 * shop list table
 */
public class ShopListDaoImpl implements ShopListDao {
    private static final Logger log = LogManager.getLogger(ShopListDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, buyer_id, game_id, date, price FROM shop_list;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, buyer_id, game_id, date, price FROM shop_list WHERE id = ?;";

    private static final String SQL_CREATE_SHOP_LIST =
            "INSERT INTO shop_list(buyer_id, game_id, date, price) VALUES(?, ?, ?, ?);";

    private static final String SQL_FIND_BY_DATE =
            "SELECT id, buyer_id, game_id, date, price FROM shop_list WHERE date = ?;";

    private static final String SQL_FIND_BY_PRICE =
            "SELECT id, buyer_id, game_id, date, price FROM shop_list WHERE price = ?;";

    private static final String SQL_SELECT_BY_BUYER_ID =
            "SELECT id, buyer_id, game_id, date, price FROM shop_list WHERE buyer_id = ?;";

    private static final String SQL_LAST_ID =
            "SELECT id FROM shop_list ORDER BY id DESC LIMIT 1;";

    private static final String SQL_DELETE_SHOP_LIST_BY_GAME_ID =
            "DELETE FROM shop_list WHERE game_id = ?;";

    public ShopListDaoImpl() { }

    /**
     * @param date input parameter date
     * @return sorted by date shopping list
     */
    @Override
    public List<ShopList> findByDate(final Date date) {
        log.debug("<-DAO-> Finding shop list by Date...");
        List<ShopList> bills = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_DATE)) {
            preparedStatement.setDate(1, (java.sql.Date) date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = this.convertToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding bills by date", e);
        }
        return bills;
    }

    /**
     * @param id input parameter buyer ID
     * @return a specific buyers's shopping list
     * Method for finding purchases of a specific buyer
     */
    @Override
    public List<ShopList> selectByBuyerId(final Long id) {
        log.debug("<-DAO-> Select shop list by buyer ID...");
        List<ShopList> bills = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_BUYER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = convertToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding bills by buyer ID", e);
        }
        return bills;
    }

    /**
     * @param price input parameter price
     * @return list of purchases made on the specific date
     * Method for finding purchases by price
     */
    @Override
    public List<ShopList> findByPrice(final Double price) {
        log.debug("<-DAO-> Finding shop list by Price...");
        List<ShopList> bills = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PRICE)) {
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = this.convertToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding bills by price", e);
        }
        return bills;
    }

    /**
     * @return all list of all purchases
     * Method for finding all purchases
     */
    @Override
    public List<ShopList> findAll() {
        log.debug("<-DAO-> Finding all shop lists...");
        List<ShopList> bills = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = this.convertToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding all bills: ", e);
        }
        return bills;
    }

    /**
     * @return last id in the table "shop list"
     * Method for finding last ID in the table "shop list"
     */
    @Override
    public Long findLastId() {
        log.debug("<-DAO-> Find last ID...");
        Long maxId = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_LAST_ID)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maxId = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding Max ID: ", e);
        }
        return maxId;
    }

    /**
     * @param id input parameter shopList ID
     * @return sought shop list
     * Method for finding shop list by ID
     */
    @Override
    public ShopList findById(final Long id) {
        log.debug("<-DAO-> Finding shop list by ID...");
        ShopList shopList = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shopList = this.convertToShopList(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding bills by id", e);
        }
        return shopList;
    }

    /**
     * @param id input shop list ID
     * Method for deleting shop list by ID
     */
    @Override
    public void delete(final Long id) {
        log.error("<-DAO-> Deleting bill by game ID...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SHOP_LIST_BY_GAME_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting bill: ", e);
        }
    }

    /**
     * @param shopList input entity
     * Method for creating new node in table "shop list"
     */
    @Override
    public void create(final ShopList shopList) {
        log.debug("<-DAO-> Creating shop list...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_SHOP_LIST)) {
            this.convertFromShopList(preparedStatement, shopList);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating bill: ", e);
        }
    }

    @Override
    public void update(ShopList shopList) {
        log.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    private ShopList convertToShopList(final ResultSet resultSet) throws SQLException {
        return ShopList.builder()
                .id(resultSet.getLong("id"))
                .buyerId(resultSet.getLong("buyer_id"))
                .gameId(resultSet.getLong("game_id"))
                .date(resultSet.getDate("date"))
                .price(resultSet.getDouble("price"))
                .build();
    }

    private void convertFromShopList(final PreparedStatement preparedStatement, final ShopList shopList) throws SQLException {
        preparedStatement.setLong(1, shopList.getId());
        preparedStatement.setLong(1, shopList.getBuyerId());
        preparedStatement.setLong(2, shopList.getGameId());
        preparedStatement.setDate(3, shopList.getDate());
        preparedStatement.setDouble(4, shopList.getPrice());
    }
}
