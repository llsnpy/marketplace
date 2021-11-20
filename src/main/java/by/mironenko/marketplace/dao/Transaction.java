package by.mironenko.marketplace.dao;

public interface Transaction {

    void commit();

    void rollback();
}
