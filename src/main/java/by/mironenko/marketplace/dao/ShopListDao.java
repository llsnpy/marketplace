package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.ShopList;
import by.mironenko.marketplace.exceptions.DaoException;

import java.util.Date;
import java.util.List;

public interface ShopListDao extends BaseDao<ShopList> {

    List<ShopList> findByDate(Date date) throws DaoException;
    List<ShopList> findByPrice(Double price) throws DaoException;
}
