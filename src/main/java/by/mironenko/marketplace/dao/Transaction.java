package by.mironenko.marketplace.dao;

import by.mironenko.marketplace.exceptions.TransactionException;

public interface Transaction {

    /*<T extends BaseDao<?>> T createDao(final KeysForDao keysForDao) throws TransactionException;*/

    void commit() throws TransactionException;

    void rollback() throws TransactionException;
}
