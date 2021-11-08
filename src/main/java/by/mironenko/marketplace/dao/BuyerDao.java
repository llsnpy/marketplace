package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Buyer;
import by.mironenko.marketplace.exceptions.DaoException;

public interface BuyerDao extends BaseDao<Buyer, Long> {

     Buyer findBySurname(String surname) throws DaoException;
}