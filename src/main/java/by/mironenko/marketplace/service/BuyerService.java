package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Buyer;

import java.util.List;

public interface BuyerService extends Service<Buyer> {

    Buyer findBySurname(String surname);
    List<Buyer> sortByMoney();

}
