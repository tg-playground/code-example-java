package com.taogen.example.jdbc._2connection_datasource;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.LoggerUtil;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceWithSpecificDriverExample {

    private static final Logger logger = LogManager.getLogger();
    private static MysqlDataSource dataSource = new MysqlDataSource();

    static {
        Properties properties = null;
        try {
            properties = PropertyUtils.getProperitesByFilePath("db.properties");
            dataSource.setUrl(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL)));
            dataSource.setUser(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER)));
            dataSource.setPassword(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD)));
        } catch (IOException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    public Connection getConnectionFromDataSoruce() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        logger.debug("connection is {}", connection);
        return connection;
    }

}
