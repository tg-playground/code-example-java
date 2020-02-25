package com.taogen.example.jdbc._2connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionExample {

    private static final Logger logger = LogManager.getLogger();

    public static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream("db.properties");
        try {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.error("{}: {}",e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
        String url = (String) properties.get("jdbc.mysql.url");
        String user = (String) properties.get("jdbc.mysql.user");
        String password = (String) properties.get("jdbc.mysql.password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.debug(connection);
        } catch (SQLException e) {
            logger.error("{}: {}",e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
        return connection;
    }
}
