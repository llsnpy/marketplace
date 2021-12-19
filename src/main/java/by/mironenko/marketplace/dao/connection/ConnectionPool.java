package by.mironenko.marketplace.dao.connection;

import by.mironenko.marketplace.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

final public class ConnectionPool {
    private static Logger log = LogManager.getLogger(ConnectionPool.class);

    private String url;
    private String user;
    private String password;
    private int maxSize;
    private int checkConnectionTimeout;
    private static final ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<InPoolConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<InPoolConnection> usedConnections = new ConcurrentSkipListSet<>();

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() { }

    public Connection getConnection() {
        InPoolConnection connection = null;
        try {
            lock.lock();
            while (connection == null) {
                try {
                    if (!freeConnections.isEmpty()) {
                        connection = freeConnections.take();
                        if (!connection.isValid(checkConnectionTimeout)) {
                            try {
                                connection.getConnection().close();
                            } catch (SQLException e) {
                            }
                            connection = null;
                        }
                    } else if (usedConnections.size() < maxSize) {
                        connection = createConnection();
                    } else {
                        throw new RuntimeException("The limit of number of database connections is exceeded");
                    }
                } catch (InterruptedException | SQLException e) {
                    throw new RuntimeException("It is impossible to connect to a database", e);
                }
            }
            usedConnections.add(connection);
            log.debug("Connection was received from pool.");
        } finally {
            lock.unlock();
        }
        return connection;
    }

     void freeConnection(InPoolConnection connection) throws DaoException {
        try {
             lock.lock();
            if(connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                log.debug("Connection was returned into pool.");
            }
        } catch(SQLException | InterruptedException e1) {
            try {
                connection.getConnection().close();
            } catch(SQLException e2) {
                throw new DaoException(e2.getCause());
            }
        } finally {
            lock.unlock();
        }
    }

    public void init(String driverClass, String url, String user, String password,
                                  int startSize, int maxSize, int checkConnectionTimeout) {

        try {
            destroy();
            Class.forName(driverClass);
            this.url = url;
            this.user = user;
            this.password = password;
            this.maxSize = maxSize;
            this.checkConnectionTimeout = checkConnectionTimeout;
            for(int counter = 0; counter < startSize; counter++) {
                freeConnections.put(createConnection());
            }
        } catch(ClassNotFoundException | SQLException | InterruptedException e) {
            log.fatal("It is impossible to initialize connection pool", e);
        }
    }

    private InPoolConnection createConnection() throws SQLException {
        return new InPoolConnection(DriverManager.getConnection(url, user, password));
    }

    public void destroy() {
        try {
            usedConnections.addAll(freeConnections);
            freeConnections.clear();
            for (InPoolConnection connection : usedConnections) {
                try {
                    connection.getConnection().close();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            usedConnections.clear();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
