package by.mironenko.marketplace.controller;

import by.mironenko.marketplace.dao.BuyerDao;
import by.mironenko.marketplace.dao.DaoFactory;
import by.mironenko.marketplace.entity.Buyer;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Buyer> buyer = null;
        BuyerDao dao = DaoFactory.getInstance().getBuyerDao();
        buyer = dao.findAll();

        for (Buyer value : buyer) {
            System.out.println(value.toString());
        }
    }
}
