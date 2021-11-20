package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Buyer;

public interface BuyerDao extends BaseDao<Buyer> {

     Buyer findBySurname(String surname);
}
