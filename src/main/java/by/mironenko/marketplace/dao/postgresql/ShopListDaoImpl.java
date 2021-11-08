package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.ShopListDao;
import by.mironenko.marketplace.entity.Buyer;
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

    private final Connection connection;

    public ShopListDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<ShopList> findByDate(final Date date) throws DaoException {
        List<ShopList> bills = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_DATE)) {
            preparedStatement.setDate(4, (java.sql.Date) date);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = this.mapToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            log.error("Exception during finding bills by date");
            throw new DaoException("Exception during finding bills by date", e);
        }
        return bills;
    }

    @Override
    public List<ShopList> findByPrice(final Double price) throws DaoException {
        List<ShopList> bills = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PRICE)) {
            preparedStatement.setDouble(5, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = this.mapToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            log.error("Exception during finding bills by price");
            throw new DaoException("Exception during finding bills by price", e);
        }
        return bills;
    }

    @Override
    public List<ShopList> findAll() throws DaoException {
        List<ShopList> bills = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ShopList shopList = this.mapToShopList(resultSet);
                bills.add(shopList);
            }
        } catch (SQLException e) {
            log.error("Exception during finding all bills");
            throw new DaoException("Exception during finding all bills: ", e);
        }
        return bills;
    }

    @Override
    public ShopList findById(final Long id) throws DaoException {
        ShopList shopList = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                shopList = this.mapToShopList(resultSet);
            }
        } catch (SQLException e) {
            log.error("Exception during finding bills by id");
            throw new DaoException("Exception during finding bills by id", e);
        }
        return shopList;
    }

    @Override
    public void delete(final Long id) throws DaoException {
        log.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(final ShopList shopList) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_SHOP_LIST)) {
            this.mapFromShopList(preparedStatement, shopList);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during creating bill");
            throw new DaoException("Exception during creating bill: ", e);
        }
    }

    @Override
    public void update(ShopList shopList) throws DaoException {
        log.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    private ShopList mapToShopList(final ResultSet resultSet) throws SQLException {
        return ShopList.builder()
                .id(resultSet.getLong("shop_list.id"))
                .buyerId(resultSet.getLong("shop_list.buyer_id"))
                .gameId(resultSet.getLong("shop_list.game_id"))
                .date(resultSet.getDate("shop_list.date"))
                .price(resultSet.getDouble("shop_list.price"))
                .build();
    }

    //todo nujno li zdes' vkluchat' ID????? + date sql or util???
    private void mapFromShopList(final PreparedStatement preparedStatement, final ShopList shopList) throws SQLException {
        preparedStatement.setLong(1, shopList.getId());
        preparedStatement.setLong(2, shopList.getBuyerId());
        preparedStatement.setLong(3, shopList.getGameId());
        preparedStatement.setDate(4, (java.sql.Date) shopList.getDate());
        preparedStatement.setDouble(5, shopList.getPrice());
    }
}