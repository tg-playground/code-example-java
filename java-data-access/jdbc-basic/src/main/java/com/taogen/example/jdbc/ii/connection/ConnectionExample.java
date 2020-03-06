package com.taogen.example.jdbc.ii.connection;

import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Taogen
 */
public class ConnectionExample {

    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            synchronized (ConnectionExample.class) {
                if (connection == null) {
                    connection = getConnectionByDriverManager(PropertyUtils.getProperitesByFilePath("db.properties"));
                }
            }
        }
        return connection;
    }

    private Connection getConnectionByDriverManager(Properties properties) throws SQLException {
        String url = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL));
        String user = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER));
        String passwd = properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD));
        logger.debug("url is {}, user is {}", url, user);
        try {
            connection = DriverManager.getConnection(url, user, passwd);
            logger.debug("connection is {}", connection);
        } catch (SQLException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
        return connection;
    }
}
