package by.mironenko.marketplace.dao.connection;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final Logger log = LogManager.getLogger(ConnectionCreator.class);
    private static final Properties properties = new Properties();
    private static String DATABASE_URL;

    static {
        DATABASE_URL = initConnectionProp();
    }

    private ConnectionCreator() { }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, properties);
    }

    private static String initConnectionProp() {
        try {
            properties.load(new FileReader("src/main/resources/datares/database.properties"));  /*datares\database.properties*/
            String driverName = (String) properties.get("db.driver");
            Class.forName(driverName);
        } catch (ClassNotFoundException | IOException e) {
            log.fatal(e.getMessage());
        }
        return (String) properties.get("db.url");
    }
}
