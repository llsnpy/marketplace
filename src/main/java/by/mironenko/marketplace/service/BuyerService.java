package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.Buyer;

public interface BuyerService extends Service<Buyer> {

    Buyer findBySurname(String surname);

}
