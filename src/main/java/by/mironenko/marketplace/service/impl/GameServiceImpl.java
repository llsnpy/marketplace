package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.GameDao;
import by.mironenko.marketplace.dao.KeysForDao;
import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.exceptions.TransactionException;
import by.mironenko.marketplace.service.GameService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private static final Logger log = Logger.getLogger(GameServiceImpl.class);

    private final Transaction transaction;

    @Override
    public void create(final Game game) throws ServiceException {
        log.info("<-SERVICE-> Creating new game...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            gameDao.create(game);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all games...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            return gameDao.findAll();
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Game findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding game by ID...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            return gameDao.findById(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Game game) throws ServiceException {
        log.info("<-SERVICE-> Updating the game...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            gameDao.update(game);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting the game...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            gameDao.delete(id);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Game findByGameName(final String gameName) throws ServiceException {
        log.info("<-SERVICE-> Finding game by name...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            return gameDao.findByName(gameName);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByDeveloperName(final String developerName) throws ServiceException {
        log.info("<-SERVICE-> Finding games by developer name...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            return gameDao.findByDeveloper(developerName);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByPreSaleStatus(final Boolean status) throws ServiceException {
        log.info("<-SERVICE-> Finding games by genre...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            return gameDao.findGameByPreSaleStatus(status);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Game> findByPrice(final Double price) throws ServiceException {
        log.info("<-SERVICE-> Finding games by price...");
        try {
            final GameDao gameDao = transaction.createDao(KeysForDao.GAME_DAO);
            return gameDao.findByPrice(price);
        } catch (TransactionException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
