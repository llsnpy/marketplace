package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.ShopList;

import java.util.Date;
import java.util.List;

public interface ShopListDao extends BaseDao<ShopList> {

    List<ShopList> findByDate(Date date);
    List<ShopList> findByPrice(Double price);
}
