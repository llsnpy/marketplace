package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.Buyer;

import java.util.List;

public interface BuyerDao extends BaseDao<Buyer> {

     Buyer findBySurname(String surname);
     List<Buyer> sortByMoney();
}
