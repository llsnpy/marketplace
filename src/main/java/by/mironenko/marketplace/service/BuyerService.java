package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.exceptions.DaoException;
import by.mironenko.marketplace.exceptions.ServiceException;

public interface BuyerService extends Service<Buyer> {

    Buyer findBySurname(String surname) throws ServiceException, DaoException;

}
