package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.BuyerWithSaleDao;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.entity.BuyersWithSale;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerWithSaleDaoImpl implements BuyerWithSaleDao {
    private static final Logger log = LogManager.getLogger(BuyerWithSaleDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, buyer_id, game_id FROM buyers_with_sale;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, buyer_id, game_id FROM buyers_with_sale WHERE id = ?;";

    private static final String SQL_CREATE_BUYER_WITH_SALE =
            "INSERT INTO buyers_with_sale (buyer_id, game_id) VALUES (?, ?);";

    public BuyerWithSaleDaoImpl() { }

    @Override
    public List<BuyersWithSale> findAll() {
        log.debug("<-DAO-> Finding all buyers with pre sale...");
        List<BuyersWithSale> preSaleList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BuyersWithSale buyersWithSale = this.convertToBuyersWithSale(resultSet);
                preSaleList.add(buyersWithSale);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding all buyers with pre-sale: ", e);
        }
        return preSaleList;
    }

    @Override
    public BuyersWithSale findById(final Long id) {
        log.debug("<-DAO-> Finding buyers with pre sale by ID...");
        BuyersWithSale buyersWithSale = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                buyersWithSale = this.convertToBuyersWithSale(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding buyers with sale by id: ", e);
        }
        return buyersWithSale;
    }

    @Override
    public void create(final BuyersWithSale buyersWithSale) {
        log.debug("<-DAO-> Creating buyer with sale...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_BUYER_WITH_SALE)) {
            this.converFromBuyersWithSale(preparedStatement, buyersWithSale);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating new note of buyer with sale: ", e);
        }
    }

    @Override
    public void update(final BuyersWithSale buyersWithSale) {
        log.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(final Long id) {
        log.error("Unsupported operation exception");
        throw new UnsupportedOperationException();
    }

    private BuyersWithSale convertToBuyersWithSale(final ResultSet resultSet) throws SQLException {
        return BuyersWithSale.builder()
                .id(resultSet.getLong("id"))
                .buyerId(resultSet.getLong("buyer_id"))
                .gameId(resultSet.getLong("game_id"))
                .build();
    }

    private void converFromBuyersWithSale(final PreparedStatement preparedStatement, final BuyersWithSale buyersWithSale) throws SQLException {
        preparedStatement.setLong(1, buyersWithSale.getId());
        preparedStatement.setLong(2, buyersWithSale.getBuyerId());
        preparedStatement.setLong(3, buyersWithSale.getGameId());
    }
}
