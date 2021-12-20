package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Buyer;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.dao.BaseDao
 * @see DaoFactory
 * The interface describes additional methods for working with buyer.
 */
public interface BuyerDao extends BaseDao<Buyer> {

     Buyer findBySurname(String surname);
     List<Buyer> sortByMoney();
}
