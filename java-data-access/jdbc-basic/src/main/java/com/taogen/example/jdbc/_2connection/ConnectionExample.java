package com.taogen.example.jdbc._2connection;

import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionExample {

    private static Connection connection;
    private static final Logger logger = LogManager.getLogger();

    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null){
            synchronized (ConnectionExample.class) {
                if (connection == null) {
                    connection = getConnectionByProperties(PropertyUtils.getProperitesByFilePath("db.properties"));
                }
            }
        }
        return connection;
    }

    private static Connection getConnectionByProperties(Properties properties) throws SQLException {
        Connection connection = null;
        String url = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL));
        String user = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER));
        String password = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD));
        logger.debug("url is {}, user is {}, password is {}", url, user, password.substring(0, 2));
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.debug("connection is {}", connection);
        } catch (SQLException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
        return connection;
    }
}
