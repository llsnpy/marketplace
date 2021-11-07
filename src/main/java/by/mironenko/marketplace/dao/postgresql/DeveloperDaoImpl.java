package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.dao.DeveloperDao;
import by.mironenko.marketplace.entity.Developer;
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
            "INSERT INTO developer(name, money, rating) VALUES(?, ?, ?);";

    //todo where id = ? ???
    private static final String SQL_UPDATE =
            "UPDATE developer SET name = ?, money = ?, rating = ?;";

    private static final String SQL_DELETE =
            "DELETE FROM developer WHERE id = ?;";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, name, money, rating FROM developer WHERE name = ?;";

    private final Connection connection;

    public DeveloperDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Developer findByName(final String name) throws DaoException {
        Developer developer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developer = this.mapToDeveloper(resultSet);
            }
        } catch (SQLException e) {
            log.error("Exception during finding developer by name");
            throw new DaoException("Exception during finding developer by name: ", e);
        }
        return developer;
    }

    @Override
    public List<Developer> findAll() throws DaoException {
        List<Developer> developers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Developer developer = this.mapToDeveloper(resultSet);
                developers.add(developer);
            }
        } catch (SQLException e) {
            log.error("Exception during finding developers");
            throw new DaoException("Exception during finding developers: ", e);
        }
        return developers;
    }

    @Override
    public Developer findById(final Long id) throws DaoException {
        Developer developer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                developer = this.mapToDeveloper(resultSet);
            }
        } catch (SQLException e) {
            log.error("Exception during finding developer by id");
            throw new DaoException("Exception during finding developer by id: ", e);
        }
        return developer;
    }

    @Override
    public void delete(final Long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during deleting developer");
            throw new DaoException("Exception during deleting developer: ", e);
        }
    }

    @Override
    public void create(final Developer developer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_DEVELOPER)) {
            this.mapFromDeveloper(preparedStatement, developer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during creating developer");
            throw new DaoException("Exception during creating developer: ", e);
        }
    }

    @Override
    public void update(final Developer developer) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            this.mapFromDeveloper(preparedStatement, developer);
            preparedStatement.setLong(1, developer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during updating developer");
            throw new DaoException("Exception during updating developer: ", e);
        }
    }

    private Developer mapToDeveloper(final ResultSet resultSet) throws SQLException {
        return Developer.builder()
                .id(resultSet.getLong("developer.id"))
                .name(resultSet.getString("developer.name"))
                .money(resultSet.getDouble("developer.money"))
                .rating(resultSet.getInt("developer.rating"))
                .build();
    }

    private void mapFromDeveloper(final PreparedStatement preparedStatement, final Developer developer) throws SQLException {
        preparedStatement.setString(1, developer.getName());
        preparedStatement.setDouble(2, developer.getMoney());
        preparedStatement.setInt(3, developer.getRating());
    }
}
