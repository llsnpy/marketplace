package by.mironenko.marketplace.controller;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
    /*    Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres", "postgres", "LlsnpyMiro123");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(
                "SELECT id, login, password FROM users");
        System.out.println(resultSet.toString());

        while (true) {
            MessageManager language;
            printer.print(MessageManager.DEFAULT.getString(Constants.CHOOSE_LANG));
            String answer = reader.scanString();
            if (Integer.parseInt(answer) == 1) {
                language = MessageManager.EN;
            } else if (Integer.parseInt(answer) == 2) {
                language = MessageManager.DE;
            } else {
                language = MessageManager.DEFAULT;
            }
            try {
                DriverManager.registerDriver(new org.postgresql.Driver());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String url = "jdbc:postgresql://localhost:5432/marketplace";
            Properties properties = new Properties();
            properties.put("user", "marketHolder");
            properties.put("password", "LlsnpyMiro123");
            properties.put("autoReconnect", "true");
            properties.put("characterEncoding", "UTF-8");
            properties.put("useUnicode", "true");
            properties.put("useSSL", "true");
            properties.put("useJDBCCompliantTimezoneShift", "true");
            properties.put("useLegacyDatetimeCode", "false");
            properties.put("serverTimezone", "UNC");
            properties.put("serverSslCert", "classpath:server.crt");

        }*/
    }
}
