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
    public void create(final Game game) throws ServiceException {
        log.info("<-SERVICE-> Creating new game...");
        try {
            final GameDao gameDao = factory.getGameDao();
            gameDao.create(game);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all games...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Game findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding game by ID...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Game game) throws ServiceException {
        log.info("<-SERVICE-> Updating the game...");
        try {
            final GameDao gameDao = factory.getGameDao();
            gameDao.update(game);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting the game...");
        try {
            final GameDao gameDao = factory.getGameDao();
            gameDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Game findByGameName(final String gameName) throws ServiceException {
        log.info("<-SERVICE-> Finding game by name...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByName(gameName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByDeveloperName(final String developerName) throws ServiceException {
        log.info("<-SERVICE-> Finding games by developer name...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByDeveloper(developerName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByPreSaleStatus(final Boolean status) throws ServiceException {
        log.info("<-SERVICE-> Finding games by genre...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findGameByPreSaleStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByPrice(final Double price) throws ServiceException {
        log.info("<-SERVICE-> Finding games by price...");
        try {
            final GameDao gameDao = factory.getGameDao();
            return gameDao.findByPrice(price);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
