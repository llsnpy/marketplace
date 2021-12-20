package by.mironenko.marketplace.service;

import by.mironenko.marketplace.entity.ShopList;

import java.sql.Date;
import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.service.impl.PurchaseServiceImpl
 * The interface describes methods for forking with data from table "shop_list"
 */
public interface PurchaseService extends Service<ShopList> {

    List<ShopList> findBillsByDate(Date date);
    List<ShopList> findBillsByPrice(Double price);
    List<ShopList> findByBuyerId(Long buyerId);
    Long findMaxId();
}
