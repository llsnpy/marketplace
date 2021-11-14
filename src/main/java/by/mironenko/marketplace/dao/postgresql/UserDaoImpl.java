package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.UserDao;
import by.mironenko.marketplace.entity.User;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
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

    @Override
    public User findById(final Long id) throws DaoException {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
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
    public void delete(final Long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting user: ", e);
        }
    }

    @Override
    public void create(final User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            this.convertFromUser(preparedStatement, user);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating user: ", e);
        }
    }

    @Override
    public void update(final User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            this.convertFromUser(preparedStatement, user);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during updating user: ", e);
        }
    }

    private User convertToUser(final ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("buyer.id"))
                .login(resultSet.getString("users.login"))
                .password(resultSet.getString("users.password"))
                .build();
    }

    private void convertFromUser(final PreparedStatement preparedStatement, final User user) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
    }
}