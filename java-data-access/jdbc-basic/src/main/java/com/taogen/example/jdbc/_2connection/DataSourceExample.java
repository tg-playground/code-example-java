package com.taogen.example.jdbc._2connection;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceExample {

    private static final Logger logger = LogManager.getLogger();
    private static MysqlDataSource dataSource = new MysqlDataSource();

    static {
        Properties properties = null;
        try {
            properties = PropertyUtils.getProperitesByFilePath("db.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataSource.setUrl(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL)));
        dataSource.setUser(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER)));
        dataSource.setPassword(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD)));
    }

    public static Connection getConnectionFromDataSoruce() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.debug("connection is {}", connection);
        return connection;
    }

}
