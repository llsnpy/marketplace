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
    private ReentrantLock lock;

    private BlockingQueue<InPoolConnection> freeConnections = new LinkedBlockingQueue<>();
    private Set<InPoolConnection> usedConnections = new ConcurrentSkipListSet<>();

    private static ConnectionPool instance = new ConnectionPool();

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() { }

    public Connection getConnection() {
        /*lock.lock();*/
        InPoolConnection connection = null;
        try {
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
            log.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
        } finally {
            /*lock.unlock();*/
        }
        return connection;
    }

     void freeConnection(InPoolConnection connection) throws DaoException {
        lock.lock();
        try {
            if(connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.put(connection);
                log.debug(String.format("Connection was returned into pool. " +
                        "Current pool size: %d used connections; %d free connection",
                        usedConnections.size(), freeConnections.size()));
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
        lock.lock();
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
        } finally {
            lock.unlock();
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
            lock.unlock();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroy();
    }
}
