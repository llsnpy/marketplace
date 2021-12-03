package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.DeveloperDao;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDaoImpl implements DeveloperDao {
    private static final Logger log = LogManager.getLogger(DeveloperDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, money, rating FROM developer;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, money, rating FROM developer WHERE id = ?;";

    private static final String SQL_CREATE_DEVELOPER =
            "INSERT INTO developer(id, name, money, rating) VALUES(?, ?, ?, ?);";

    private static final String SQL_UPDATE_DEVELOPER =
            "UPDATE developer SET name = ?, money = ?, rating = ? WHERE id = ?;";

    private static final String SQL_DELETE_DEVELOPER =
            "DELETE FROM developer WHERE id = ?;";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, name, money, rating FROM developer WHERE name = ?;";

    private static final String SQL_SORT_DEVELOPER_BY_RATING =
            "SELECT id, name, money, rating FROM developer ORDER BY rating;";

    public DeveloperDaoImpl() { }

    @Override
    public Developer findByName(final String name) {
        log.debug("<-DAO-> Finding developer by Name...");
        Developer developer = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developer = this.mapToDeveloper(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding developer by name: ", e);
        }
        return developer;
    }

    @Override
    public List<Developer> findAll() {
        log.debug("<-DAO-> Finding all developers...");
        List<Developer> developers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Developer developer = this.mapToDeveloper(resultSet);
                developers.add(developer);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding developers: ", e);
        }
        return developers;
    }

    @Override
    public List<Developer> sortByRating() {
        log.debug("<-DAO-> Sort developer by rating...");
        List<Developer> developers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SORT_DEVELOPER_BY_RATING)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Developer developer = this.mapToDeveloper(resultSet);
                developers.add(developer);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during sort developers by rating: ", e);
        }
        return developers;
    }

    @Override
    public Developer findById(final Long id) {
        log.debug("<-DAO-> Finding developer by ID...");
        Developer developer = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developer = this.mapToDeveloper(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding developer by id: ", e);
        }
        return developer;
    }

    @Override
    public void delete(final Long id) {
        log.debug("<-DAO-> Deleting developer by ID...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_DEVELOPER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting developer: ", e);
        }
    }

    @Override
    public void create(final Developer developer) {
        log.debug("<-DAO-> Creating developer...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_DEVELOPER)) {
            this.mapFromDeveloper(preparedStatement, developer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating developer: ", e);
        }
    }

    @Override
    public void update(final Developer developer) {
        log.debug("<-DAO-> Updating developer...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_DEVELOPER)) {
            this.mapFromDeveloper(preparedStatement, developer);
            preparedStatement.setLong(4, developer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during updating developer: ", e);
        }
    }

    private Developer mapToDeveloper(final ResultSet resultSet) throws SQLException {
        return Developer.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .money(resultSet.getDouble("money"))
                .rating(resultSet.getInt("rating"))
                .build();
    }

    private void mapFromDeveloper(final PreparedStatement preparedStatement, final Developer developer) throws SQLException {
        preparedStatement.setLong(1, developer.getId());
        preparedStatement.setString(2, developer.getName());
        preparedStatement.setDouble(3, developer.getMoney());
        preparedStatement.setInt(4, developer.getRating());
    }
}
