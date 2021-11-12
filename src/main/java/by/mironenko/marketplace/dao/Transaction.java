package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.dao.transaction.TransactionException;

public interface Transaction {

    <T extends BaseDao<?>> T createDao(final KeysForDao keysForDao) throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
