package by.mironenko.marketplace.dao.postgresql;

import by.mironenko.marketplace.dao.GameDao;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.entity.Genre;
import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Mironenko
 * @see GameDao
 * Describes how to access the database and work with the
 * game table
 */
public class GameDaoImpl implements GameDao {
    private static final Logger log = LogManager.getLogger(GameDaoImpl.class);

    private static final String SQL_SELECT_ALL =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game;";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game WHERE id = ?;";

    private static final String SQL_CREATE_GAME =
            "INSERT INTO game(developer_id, name, genre, date, price, pre_sale, sale_price) VALUES (?, ?, ?, ?, ?, ?, ?);";

    private static final String SQL_UPDATE_GAME =
            "UPDATE game SET name = ?, genre = ?, date = ?, price = ?, pre_sale = ?, sale_price = ? WHERE id = ?;";

    private static final String SQL_DELETE_GAME =
            "DELETE FROM game WHERE id = ?;";

    private static final String SQL_FIND_BY_NAME =
            "SELECT id, developer_id, name, genre, date, price, pre_sale, sale_price FROM game WHERE name = ?;";

    private static final String SQL_FIND_BY_DEVELOPER =
            "SELECT game.id, game.name, genre, date, price, pre_sale, sale_price, developer.name FROM game RIGHT JOIN developer ON game.developer_id = developer.id WHERE developer.name = ?";

    private static final String SQL_FIND_BY_PRE_SALE_STATUS =
            "SELECT id, name, genre, date, pre_sale FROM game WHERE pre_sale = ?;";

    private static final String SQL_FIND_BY_PRICE =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game WHERE price = ?;";

    private static final String SQL_FIND_DEV_ID =
            "SELECT developer_id FROM game WHERE id = ?;";

    private static final String SQL_SORT_GAME_BY_PRICE =
            "SELECT id, name, genre, date, price, pre_sale, sale_price FROM game ORDER BY price;";

    private static final String SQL_FIND_BY_BUYER_ID =
            "SELECT game.id, game.name, game.genre, game.date, game.price, game.pre_sale, game.sale_price FROM game RIGHT JOIN shop_list ON game.id = shop_list.game_id JOIN buyer ON shop_list.buyer_id = buyer.id WHERE buyer_id = ?;";

    private static final String SQL_FIND_BY_DEVELOPER_ID =
            "SELECT game.id, game.name, game.genre, game.date, game.price, game.pre_sale, game.sale_price FROM game WHERE developer_id = ?;";

    public GameDaoImpl() { }

    /**
     * @param name input game name
     * @return finding game
     * Method for finding game by game name
     */
    @Override
    public Game findByName(final String name) {
        log.debug("<-DAO-> Finding game by Name...");
        Game game = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                game = convertToGame(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding game by name: ", e);
        }
        return game;
    }

    /**
     * @param developerName input developer name
     * @return list of selected developer's games
     * Method for finding games by developer name
     */
    @Override
    public List<Game> findByDeveloper(final String developerName) {
        log.debug("<-DAO-> Finding game by Developer...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_DEVELOPER)) {
            preparedStatement.setString(1, developerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding games by developer: ", e);
        }
        return games;
    }

    /**
     * @param preSaleStatus input sale status
     * @return list of games with sale (true / false)
     * Method for finding games by sale status
     */
    @Override
    public List<Game> findGameByPreSaleStatus(final boolean preSaleStatus) {
        log.debug("<-DAO-> Finding game by pre sale status...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PRE_SALE_STATUS)) {
            preparedStatement.setBoolean(1, preSaleStatus);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding games by preSale status: ", e);
        }
        return games;
    }

    /**
     * @param price input parameter
     * @return list of games with input price
     * Method for finding game by price
     */
    @Override
    public List<Game> findByPrice(final double price) {
        log.debug("<-DAO-> Finding game by Price...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_PRICE)) {
            preparedStatement.setDouble(1, price);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding games by price: ", e);
        }
        return games;
    }

    /**
     * @param id input game ID
     * @return developer ID
     * Method for finding developer ID by game ID
     */
    @Override
    public Long getDeveloperId(final Long id) {
        log.debug("<-DAO-> Finding developer ID by game ID...");
        long devId = 0L;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_DEV_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                devId = resultSet.getLong("developer_id");
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding developer ID: ", e);
        }
        return devId;
    }

    /**
     * @return list of all games
     * Method for finding all games
     */
    @Override
    public List<Game> findAll() {
        log.debug("<-DAO-> Finding all games...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding games: ", e);
        }
        return games;
    }

    /**
     * @param id input parameter
     * @return finding game
     * Method for finding game by ID
     */
    @Override
    public Game findById(final Long id) {
        log.debug("<-DAO-> Finding game by ID...");
        Game game = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                game = this.convertToGame(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during finding game by id: ", e);
        }
        return game;
    }

    /**
     * @param id input buyer ID
     * @return list of games for a specific buyer
     * Method for finding games by buyer ID
     */
    @Override
    public List<Game> findByBuyerId(final Long id) {
        log.debug("<-DAO-> Select all games by buyer ID...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_BUYER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during selecting games from shop list by buyer ID: ", e);
        }
        return games;
    }

    /**
     * @param id input parameter
     * @return list of games for specific developer
     * Method for finding games by developer ID
     */
    @Override
    public List<Game> findByDeveloperId(final Long id) {
        log.debug("<-DAO-> Select all games by developer ID...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_DEVELOPER_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during selecting games by developer ID: ", e);
        }
        return games;
    }

    /**
     * @param id input parameter game ID
     * Method for deleting game by game ID
     */
    @Override
    public void delete(final Long id) {
        log.debug("<-DAO-> Deleting game by ID...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_GAME)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting by id: ", e);
        }
    }

    /**
     * @param game input parameter entity
     * Method for creating new node in table "game"
     */
    @Override
    public void create(final Game game) {
        log.debug("<-DAO-> Creating game...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_GAME)) {
            this.convertFromGame(preparedStatement, game);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during creating game: ", e);
        }
    }

    /**
     * @param game input entity game
     * Method for updating node in database
     */
    @Override
    public void update(final Game game) {
        log.debug("<-DAO-> Updating game...");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_GAME)) {
            preparedStatement.setString(1, game.getName());
            preparedStatement.setString(2, game.getGenre().toString());
            preparedStatement.setDate(3, game.getDate());
            preparedStatement.setDouble(4, game.getPrice());
            preparedStatement.setBoolean(5, game.isPreSale());
            preparedStatement.setDouble(6, game.getSalePrice());
            preparedStatement.setLong(7, game.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during updating game: ", e);
        }
    }

    /**
     * @return sorted by price list of games
     * Method for sorting games by price
     */
    @Override
    public List<Game> sortByPrice() {
        log.debug("<-DAO-> Sort game by price...");
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SORT_GAME_BY_PRICE)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Game game = this.convertToGame(resultSet);
                games.add(game);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during sorting games by price: ", e);
        }
        return games;
    }

    private Game convertToGame(final ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .genre(Genre.valueOf((resultSet.getString( "genre")).toUpperCase()))
                .date(resultSet.getDate("date"))
                .price(resultSet.getDouble("price"))
                .preSale(resultSet.getBoolean("pre_sale"))
                .salePrice(resultSet.getDouble("sale_price"))
                .build();
    }

    private void convertFromGame(final PreparedStatement preparedStatement, final Game game) throws SQLException {
        preparedStatement.setLong(1, game.getDeveloperId());
        preparedStatement.setString(2, game.getName());
        preparedStatement.setString(3, game.getGenre().toString());
        preparedStatement.setDate(4, game.getDate());
        preparedStatement.setDouble(5, game.getPrice());
        preparedStatement.setBoolean(6, game.isPreSale());
        preparedStatement.setDouble(7, game.getSalePrice());
    }
}
