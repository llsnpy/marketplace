package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.UserDao;
import by.mironenko.marketplace.dao.connection.ConnectionCreator;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Mironenko
 * @see UserDao
 * Describes how to access the database and work
 * with the user table
 */
public class UserDaoImpl implements UserDao {
    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, login, password FROM users;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT login, password FROM users WHERE id = ?;";

    private static final String SQL_DELETE_USER =
            "DELETE FROM users WHERE id = ?;";

    private static final String SQL_CREATE_USER =
            "INSERT INTO users(login, password) VALUES(?, ?);";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET login = ?, password = ?;";

    private static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT login, password FROM users WHERE login = ?;";

    public UserDaoImpl() { }

    /**
     * @return list of all users
     * @throws DaoException if have some problems with DB
     * Method for finding all users
     */
    @Override
    public List<User> findAll() {
        log.debug("<-DAO-> Finding all users...");
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = convertToUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding users: ", e);
        }
        return users;
    }

    /**
     * @param id input param from webApp
     * @return current user (selected in webApp)
     * @throws DaoException if have some problems with DB
     * Method for finding user by ID
     */
    @Override
    public User findById(final Long id) {
        log.debug("<-DAO-> Finding user by ID...");
        User user = null;
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = convertToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding user by id: ", e);
        }
        return user;
    }

    @Override
    public void delete(final Long id) {
        log.debug("<-DAO-> Deleting user by ID...");
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting user: ", e);
        }
    }

    @Override
    public void create(final User user) {
        log.debug("<-DAO-> Creating user...");
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            this.convertFromUser(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating user: ", e);
        }
    }

    @Override
    public void update(final User user) {
        log.debug("<-DAO-> Updating user...");
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            this.convertFromUser(preparedStatement, user);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during updating user: ", e);
        }
    }

    @Override
    public User findUserByLogin(final String login) {
        log.debug("<-DAO-> Finding user by login...");
        User user = null;
        try (Connection connection = ConnectionCreator.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = convertToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding user by login: ", e);
        }
        return user;
    }

    private User convertToUser(final ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getLong("id"))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void convertFromUser(final PreparedStatement preparedStatement, final User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
    }
}
