package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.GameDao;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.GameService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private static final Logger log = Logger.getLogger(GameServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

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
}
