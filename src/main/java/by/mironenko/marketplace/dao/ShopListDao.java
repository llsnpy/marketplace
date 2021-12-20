package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.entity.ShopList;

import java.util.Date;
import java.util.List;

/**
 * @author Pavel Mironenko
 * @see by.mironenko.marketplace.dao.postgresql.ShopListDaoImpl
 * @see DaoFactory
 * The interface describes additional methods for working with shop list.
 */
public interface ShopListDao extends BaseDao<ShopList> {

    List<ShopList> findByDate(Date date);
    List<ShopList> findByPrice(Double price);
    List<ShopList> selectByBuyerId(Long id);
    Long findLastId();
}
