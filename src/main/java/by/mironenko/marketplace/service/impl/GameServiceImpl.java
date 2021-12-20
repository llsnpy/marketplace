package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.GameDao;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.GameService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see GameService
 * Invoke method for working with GameDao
 */
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private static final Logger log = LogManager.getLogger(GameServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    /**
     * @param game input parameter from controller (entity Game)
     * Invoke method from DAO to creating new node in database
     */
    @Override
    public void create(final Game game) {
        log.debug("<-SERVICE-> Creating new game...");
        if (game == null || game.getPrice() <= 0.0) {
            throw new ServiceException("Incorrect input parameters for creating game.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            gameDao.create(game);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @return list of games from database
     * Invoke method from DAO to finding all games
     */
    @Override
    public List<Game> findAll() {
        log.debug("<-SERVICE-> Finding all games...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter gameId
     * @return sought game (by ID)
     * Invoke method from DAO to finding game by ID
     */
    @Override
    public Game findById(final Long id) {
        log.debug("<-SERVICE-> Finding game by ID...");
        if (id <= 0) {
            throw new ServiceException("Incorrect ID for buying game by ID.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param game input parameter from controller (entity Game)
     * Invoke method from DAO to updating the node in database
     */
    @Override
    public void update(final Game game) {
        log.info("<-SERVICE-> Updating the game...");
        if (game == null) {
            throw new ServiceException("Cant define game for updating.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            gameDao.update(game);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter gameId
     * Invoke method from DAO to deleting the node from database by ID
     */
    @Override
    public void delete(final Long id) {
        log.info("<-SERVICE-> Deleting the game...");
        if (id <= 0) {
            throw new ServiceException("Incorrect ID for buying game by ID.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            gameDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param gameName input parameter gameName
     * @return sought game by gameName
     * Invoke method from DAO to finding game by name
     */
    @Override
    public Game findByGameName(final String gameName) {
        log.info("<-SERVICE-> Finding game by name...");
        if (gameName == null) {
            throw new ServiceException("Incorrect game name.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByName(gameName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param developerName input parameter developerName
     * @return list of games by specific developer
     * Invoke method from DAO to finding game by DeveloperName
     */
    @Override
    public List<Game> findByDeveloperName(final String developerName) {
        log.info("<-SERVICE-> Finding games by developer name...");
        if (developerName == null) {
            throw new ServiceException("Incorrect developer name.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByDeveloper(developerName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param status input parameter saleStatus
     * @return list of games by specific status
     * Invoke method from DAO to finding game by sale status
     */
    @Override
    public List<Game> findByPreSaleStatus(final Boolean status) {
        log.info("<-SERVICE-> Finding games by genre...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findGameByPreSaleStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter buyerID
     * @return list of games by specific buyer
     * Invoke method from DAO to finding game by buyer ID
     */
    @Override
    public List<Game> findByBuyerID(final Long id) {
        log.info("<-SERVICE-> Finding games by buyer ID...");
        if (id <= 0) {
            throw new ServiceException("Incorrect ID for finding games.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByBuyerId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter developerID
     * @return list of games by specific developer
     * Invoke method from DAO to finding game by developer ID
     */
    @Override
    public List<Game> findByDeveloperId(final Long id) {
        log.info("<-SERVICE-> Finding games by buyer ID...");
        if (id <= 0) {
            throw new ServiceException("Incorrect ID for finding games.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByDeveloperId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param price input parameter price
     * @return list of games by specific price
     * Invoke method from DAO to finding games by price
     */
    @Override
    public List<Game> findByPrice(final Double price) {
        log.info("<-SERVICE-> Finding games by price...");
        if (price <= 0.0) {
            throw new ServiceException("Incorrect price for finding game by price.");
        }
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByPrice(price);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @return sorted by price list of games
     * Invoke method from DAO to sorting game by price
     */
    @Override
    public List<Game> sortByPrice() {
        log.info("<-SERVICE-> Sort games by price...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.sortByPrice();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter gameID
     * @return sought developer ID by game ID
     */
    @Override
    public Long getDeveloperId(final Long id) {
        log.info("<-SERVICE-> Get developer_id by game_id...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.getDeveloperId(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
