package by.mironenko.marketplace.dao.transaction;

import by.mironenko.marketplace.dao.Transaction;
import by.mironenko.marketplace.dao.connection.ConnectionPool;
import by.mironenko.marketplace.exceptions.TransactionException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl {
    private static final Logger log = Logger.getLogger(TransactionFactoryImpl.class);
    private Connection connection;

    public TransactionFactoryImpl() throws TransactionException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionException("Impossible to turn off autocommiting far DB connection", e);
        }
    }

    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Exception during closing connection");
        }
    }
}
