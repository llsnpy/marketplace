package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.entity.Game;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;

public interface BuyerService extends Service {

    Buyer findBySurname(String surname) throws ServiceException, DaoException;
    boolean buyGame(Game game) throws ServiceException;
    boolean givePreSale(boolean preSale) throws ServiceException;

}
