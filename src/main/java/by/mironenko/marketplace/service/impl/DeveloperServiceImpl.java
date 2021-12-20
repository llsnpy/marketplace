package by.mironenko.marketplace.service.impl;

import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.dao.DeveloperDao;
import by.mironenko.marketplace.entity.Developer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;
import by.mironenko.marketplace.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see DeveloperService
 * Invoke method for working with DeveloperDao
 */
@RequiredArgsConstructor
public class DeveloperServiceImpl implements DeveloperService {
    private static final Logger log = LogManager.getLogger(DeveloperServiceImpl.class);

    private final DaoFactory factory = DaoFactory.getInstance();

    /**
     * @param developer input parameter from controller (entity Developer)
     * Invoke method from DAO to creating new node in database
     */
    @Override
    public void create(final Developer developer) {
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

    /**
     * @return list of developers from database
     * Invoke method from DAO to finding all developers
     */
    @Override
    public List<Developer> findAll() {
        log.info("<-SERVICE-> Finding all developers...");
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            return developerDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @param id input parameter developerId
     * @return sought developer (by ID)
     * Invoke method from DAO to finding developer by ID
     */
    @Override
    public Developer findById(final Long id) {
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

    /**
     * @param developer input parameter from controller (entity Developer)
     * Invoke method from DAO to updating the node in database
     */
    @Override
    public void update(final Developer developer) {
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

    /**
     * @param id input parameter developerId
     * Invoke method from DAO to deleting the node from database by ID
     */
    @Override
    public void delete(final Long id) {
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

    /**
     * @param developerName input parameter developerName
     * @return sought developer by name
     * Invoke method from DAO to finding developer by name
     */
    @Override
    public Developer findByName(final String developerName) {
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

    /**
     * @return sorted by rating list of developers
     * Invoke method from DAO to sorting developers by rating
     */
    @Override
    public List<Developer> sortByRating() {
        log.info("<-SERVICE-> Finding developer by name...");
        try {
            final DeveloperDao developerDao = factory.getDeveloperDao();
            return developerDao.sortByRating();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
