package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.ShopList;
import by.mironenko.marketplace.exceptions.ServiceException;

import java.sql.Date;
import java.util.List;

public interface PurchaseService extends Service<ShopList> {

    List<ShopList> findBillsByDate(Date date) throws ServiceException;
    List<ShopList> findBillsByPrice(Double price) throws ServiceException;
    void buyGame(Long buyerId, Long gameId) throws ServiceException;
}
