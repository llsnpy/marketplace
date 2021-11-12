package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.BuyerDao;
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
 */
public class BuyerDaoImpl implements BuyerDao {
    private static final Logger log = LogManager.getLogger(BuyerDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, surname, money, age FROM buyer;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, surname, money, age FROM buyer WHERE id = ?;";

    private static final String SQL_CREATE_BUYER =
            "INSERT INTO buyer(name, surname, money, age) VALUES(?, ?, ?, ?);";

    private static final String SQL_UPDATE_BUYER =
            "UPDATE buyer SET name = ?, surname = ?, money = ?, age = ?;";

    private static final String SQL_DELETE_BUYER =
            "DELETE FROM buyer WHERE id = ?;";

    private static final String SQL_FIND_BY_SURNAME =
            "SELECT id, name, surname, money, age FROM buyer WHERE surname = ?;";

    private final Connection connection; //connectionPool

    public BuyerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    /**
     * @param surname input value from webApp
     * @return finding buyer
     * @throws DaoException castom exception for dao
     * Method for finding buyers by surname
     */
    @Override
    public Buyer findBySurname(final String surname) throws DaoException {
        Buyer buyer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_SURNAME)) {
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
     * @throws DaoException custom exception for dao
     * Finding all buyers
     */
    @Override
    public List<Buyer> findAll() throws DaoException {
        List<Buyer> buyers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
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
     * @throws DaoException custom exception for dao
     * Finding buyers by given id
     */
    @Override
    public Buyer findById(final Long id) throws DaoException {
        Buyer buyer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
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

    @Override
    public void delete(final Long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BUYER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting buyer: ", e);
        }
    }

    @Override
    public void create(final Buyer buyer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_BUYER)) {
            convertFromBuyer(preparedStatement, buyer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating buyer: ", e);
        }
    }

    @Override
    public void update(final Buyer buyer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_BUYER)) {
            this.convertFromBuyer(preparedStatement, buyer);
            preparedStatement.setLong(1, buyer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during updating buyer: ", e);
        }
    }

    private Buyer convertToBuyer(final ResultSet resultSet) throws SQLException {
        return Buyer.builder()
                .id(resultSet.getLong("buyer.id"))
                .name(resultSet.getString("buyer.name"))
                .surname(resultSet.getString("buyer.surname"))
                .money(resultSet.getDouble("buyer.money"))
                .age(resultSet.getInt("buyer.age"))
                .build();
    }

    private void convertFromBuyer(final PreparedStatement preparedStatement, final Buyer buyer) throws SQLException {
        preparedStatement.setString(1, buyer.getName());
        preparedStatement.setString(2, buyer.getSurname());
        preparedStatement.setDouble(3, buyer.getMoney());
        preparedStatement.setInt(4, buyer.getAge());
    }
}
