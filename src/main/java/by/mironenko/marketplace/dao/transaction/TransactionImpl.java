package by.mironenko.marketplace.dao.transaction;

import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.exceptions.TransactionException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public class TransactionImpl implements Transaction {
    private static Logger log = LogManager.getLogger(TransactionImpl.class);

    private final Connection connection;

    @Override
    public void commit() throws TransactionException {
        log.debug("<-TRANSACTION-> Committing...");
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        log.debug("<-TRANSACTION-> Rollback...");
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }
}
