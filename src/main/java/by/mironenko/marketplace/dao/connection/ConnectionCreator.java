package by.mironenko.marketplace.dao.connection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final Logger log = LogManager.getLogger(ConnectionCreator.class);
    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            properties.load(new FileReader("database.properties"));
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            log.fatal("Exception during connection to db: ", e.getCause());
        }
        DATABASE_URL = (String) properties.get("db.url");
    }

    private ConnectionCreator() {
    }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }
}
