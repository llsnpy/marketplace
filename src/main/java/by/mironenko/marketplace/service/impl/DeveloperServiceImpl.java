package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.DeveloperDao;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;

import java.util.List;

@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private static final Logger log = Logger.getLogger(DeveloperServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    @Override
    public void create(final Developer developer) throws ServiceException {
        log.info("<-SERVICE-> Creating new developer...");
        if (developer == null || developer.getMoney() < 0) {
            throw new ServiceException("Incorrect input parameters for creating developer.");
        }
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            developerDao.create(developer);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Developer> findAll() throws ServiceException {
        log.info("<-SERVICE-> Finding all developers...");
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            return developerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Developer findById(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Finding developer by ID...");
        if (id < 1) {
            throw new ServiceException("Incorrect ID for finding developer by ID.");
        }
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            return developerDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void update(final Developer developer) throws ServiceException {
        log.info("<-SERVICE-> Updating developer...");
        if (developer == null) {
            throw new ServiceException("Incorrect parameters for updating developer.");
        }
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            developerDao.update(developer);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(final Long id) throws ServiceException {
        log.info("<-SERVICE-> Deleting developer by ID...");
        if (id < 1) {
            throw new ServiceException("Incorrect ID for deleting developer.");
        }
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            developerDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Developer findByName(final String developerName) throws ServiceException {
        log.info("<-SERVICE-> Finding developer by name...");
        if (developerName == null) {
            throw new ServiceException("Incorrect name for finding developer by name.");
        }
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            return developerDao.findByName(developerName);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void createNewGame(final Game game) throws ServiceException {
        //todo create this method
    }
}
