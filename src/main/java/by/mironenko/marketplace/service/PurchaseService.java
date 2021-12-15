package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.ShopList;

import java.sql.Date;
import java.util.List;

public interface PurchaseService extends Service<ShopList> {

    List<ShopList> findBillsByDate(Date date);
    List<ShopList> findBillsByPrice(Double price);
    List<ShopList> findByBuyerId(Long buyerId);
    Long findMaxId();
}
