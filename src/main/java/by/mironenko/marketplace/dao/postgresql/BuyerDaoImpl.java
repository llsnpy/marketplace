package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.BuyerDao;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Mironenko
 * @see BuyerDao
 * Describes how to access the database and work with the
 * buyer table
 */
public class BuyerDaoImpl implements BuyerDao {
    private static final Logger log = LogManager.getLogger(BuyerDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, surname, money, age FROM buyer;";

    private static final String SQL_SORT_BUYER_BY_MONEY =
            "SELECT id, name, surname, money, age FROM buyer ORDER BY money;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, surname, money, age FROM buyer WHERE id = ?;";

    private static final String SQL_CREATE_BUYER =
            "INSERT INTO buyer(id, name, surname, money, age) VALUES(?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_BUYER =
            "UPDATE buyer SET name = ?, surname = ?, money = ?, age = ? WHERE id = ?;";

    private static final String SQL_DELETE_BUYER =
            "DELETE FROM buyer WHERE id = ?;";

    private static final String SQL_FIND_BY_SURNAME =
            "SELECT id, name, surname, money, age FROM buyer WHERE surname = ?;";

    public BuyerDaoImpl() { }

    /**
     * @param surname input value from webApp
     * @return finding buyer
     * @throws DaoException if have some problems with DB
     * Method for finding buyers by surname
     */
    @Override
    public Buyer findBySurname(final String surname) {
        log.debug("<-DAO-> Finding buyer by surname...");
        Buyer buyer = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_SURNAME)
        ) {
            preparedStatement.setString(1, surname);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                buyer = this.convertToBuyer(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding buyer by surname: ", e);
        }
        return buyer;
    }

    /**
     * @return list of all buyers
     * @throws DaoException if have some problems with DB
     * Method for finding all buyers
     */
    @Override
    public List<Buyer> findAll() {
        log.debug("<-DAO-> Finding all buyers...");
        List<Buyer> buyers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Buyer buyer = this.convertToBuyer(resultSet);
                buyers.add(buyer);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding buyers: ", e);
        }
        return buyers;
    }

    /**
     * @return sorted by money list of buyers
     */
    @Override
    public List<Buyer> sortByMoney() {
        log.debug("<-DAO-> Sort buyers by name...");
        List<Buyer> buyers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SORT_BUYER_BY_MONEY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Buyer buyer = this.convertToBuyer(resultSet);
                buyers.add(buyer);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding buyers: ", e);
        }
        return buyers;
    }

    /**
     * @param id input value from webApp
     * @return finding buyer
     * @throws DaoException if have some problems with DB
     * Method for finding buyers by given ID
     */
    @Override
    public Buyer findById(final Long id) {
        log.debug("<-DAO-> Finding buyer by ID...");
        Buyer buyer = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                buyer = this.convertToBuyer(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding buyer by id: ", e);
        }
        return buyer;
    }

    /**
     * @param id inputValue from webApp
     * @throws DaoException if have some problems with DB
     * Method for deleting buyer by ID
     */
    @Override
    public void delete(final Long id) {
        log.debug("<-DAO-> Deleting buyer by surname...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BUYER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting buyer: ", e);
        }
    }

    /**
     * @param buyer when we want to create ne buyer
     * @throws DaoException if have some problems with DB
     * Method for creating new buyer (after registration like buyer)
     */
    @Override
    public void create(final Buyer buyer) {
        log.debug("<-DAO-> Creating buyer...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_BUYER)) {
            convertFromBuyer(preparedStatement, buyer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating buyer: ", e);
        }
    }

    /**
     * @param buyer selected buyer in webApp
     * @throws DaoException if have some problems with DB
     * Method for updating buyer
     */
    @Override
    public void update(final Buyer buyer) {
        log.debug("<-DAO-> Updating buyer...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BUYER)) {
            preparedStatement.setString(1, buyer.getName());
            preparedStatement.setString(2, buyer.getSurname());
            preparedStatement.setDouble(3, buyer.getMoney());
            preparedStatement.setInt(4, buyer.getAge());
            preparedStatement.setLong(5, buyer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during updating buyer: ", e);
        }
    }

    private Buyer convertToBuyer(final ResultSet resultSet) throws SQLException {
        return Buyer.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .money(resultSet.getDouble("money"))
                .age(resultSet.getInt("age"))
                .build();
    }

    private void convertFromBuyer(final PreparedStatement preparedStatement, final Buyer buyer) throws SQLException {
        preparedStatement.setLong(1, buyer.getId());
        preparedStatement.setString(2, buyer.getName());
        preparedStatement.setString(3, buyer.getSurname());
        preparedStatement.setDouble(4, buyer.getMoney());
        preparedStatement.setInt(5, buyer.getAge());
    }
}
