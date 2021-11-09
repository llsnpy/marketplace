package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.dao.GameDao;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDaoImpl implements GameDao {
    private static final Logger log = LogManager.getLogger(GameDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game WHERE id = ?;";

    private static final String SQL_CREATE_GAME =
            "INSERT INTO game(name, genre, date, price, pre_sale, sale_price) VALUES (?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_GAME =
            "UPDATE game SET name = ?, genre = ?, date = ?, price = ?, pre_sale = ?, sale_price = ?;";

    private static final String SQL_DELETE_GAME =
            "DELETE FROM game WHERE id = ?;";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game WHERE name = ?;";

    private static final String SQL_FIND_BY_DEVELOPER =
            "SELECT game.id, game.name, genre, date, price, pre_sale, sale_price, developer.name FROM game RIGHT JOIN developer ON game.developer_id = developer.id = developer_id WHERE developer.name = ?";

    private static final String SQL_FIND_BY_PRE_SALE_STATUS =
            "SELECT id, name, genre, date, pre_sale FROM game WHERE pre_sale = ?;";

    private static final String SQL_FIND_BY_PRICE =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game WHERE price = ?;";

    Connection connection;

    public GameDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Game findByName(final String name) throws DaoException {
        Game game = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                game = this.mapToGame(resultSet);
            }
        } catch (SQLException e) {
            log.error("Exception during finding game by name");
            throw new DaoException("Exception during finding game by name: ", e);
        }
        return game;
    }

    @Override
    public List<Game> findByDeveloper(final String developerName) throws DaoException {
        List<Game> games = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_DEVELOPER)) {
            preparedStatement.setString(1, developerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.mapToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            log.error("Exception during finding games by developer");
            throw new DaoException("Exception during finding games by developer: ", e);
        }
        return games;
    }

    @Override
    public List<Game> findGameByPreSaleStatus(final boolean preSaleStatus) throws DaoException {
        List<Game> games = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PRE_SALE_STATUS)) {
            preparedStatement.setBoolean(1, preSaleStatus);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.mapToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            log.error("Exception during finding games by preSale status");
            throw new DaoException("Exception during finding games by preSale status: ", e);
        }
        return games;
    }

    @Override
    public List<Game> findByPrice(final double price) throws DaoException {
        List<Game> games = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PRICE)) {
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.mapToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            log.error("Exception during finding games by price");
            throw new DaoException("Exception during finding games by price: ", e);
        }
        return games;
    }

    @Override
    public List<Game> findAll() throws DaoException {
        List<Game> games = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.mapToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            log.error("Exception during finding games");
            throw new DaoException("Exception during finding games: ", e);
        }
        return games;
    }

    @Override
    public Game findById(final Long id) throws DaoException {
        Game game = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                game = this.mapToGame(resultSet);
            }
        } catch (SQLException e) {
            log.error("Exception during finding game by id");
            throw new DaoException("Exception during finding game by id: ", e);
        }
        return game;
    }

    @Override
    public void delete(final Long id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_GAME)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during deleting by id");
            throw new DaoException("Exception during deleting by id: ", e);
        }
    }

    @Override
    public void create(final Game game) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_GAME)) {
            this.mapFromGame(preparedStatement, game);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during creating game");
            throw new DaoException("Exception during creating game: ", e);
        }
    }

    @Override
    public void update(final Game game) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_GAME)) {
            this.mapFromGame(preparedStatement, game);
            preparedStatement.setLong(1, game.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Exception during updating game");
            throw new DaoException("Exception during updating game: ", e);
        }
    }

    private Game mapToGame(final ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getLong("game.id"))
                .name(resultSet.getString("game.name"))
                .genre(Genre.valueOf(resultSet.getString( "game.genre")))
                .date(resultSet.getDate("game.date"))
                .price(resultSet.getDouble("game.price"))
                .preSale(resultSet.getBoolean("game.pre_sale"))
                .salePrice(resultSet.getDouble("game.sale_price"))
                .build();
    }

    private void mapFromGame(final PreparedStatement preparedStatement, final Game game) throws SQLException {
        preparedStatement.setString(1, game.getName());
        preparedStatement.setString(2, game.getGenre().toString());
        preparedStatement.setDate(3, (Date) game.getDate());
        preparedStatement.setDouble(4, game.getPrice());
        preparedStatement.setBoolean(5, game.isPreSale());
        preparedStatement.setDouble(6, game.getSalePrice());
    }
}
