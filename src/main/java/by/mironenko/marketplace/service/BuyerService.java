package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Buyer;

import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.service.impl.BuyerServiceImpl
 * The interface describes methods for forking with data from table "buyer"
 */
public interface BuyerService extends Service<Buyer> {

    Buyer findBySurname(String surname);
    List<Buyer> sortByMoney();

}
