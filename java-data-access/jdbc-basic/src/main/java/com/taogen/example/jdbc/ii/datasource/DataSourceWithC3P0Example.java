package com.taogen.example.jdbc.ii.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.LoggerUtil;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Taogen
 */
public class DataSourceWithC3P0Example {

    private static final Logger logger = LogManager.getLogger();
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    static {
        Properties properties = null;
        try {
            properties = PropertyUtils.getPropertiesByFilePath("db.properties");
            dataSource.setDriverClass(properties.getProperty(JdbcConfig.MYSQL_DRIVER_CLASS.toString()));
            dataSource.setJdbcUrl(properties.getProperty(JdbcConfig.MYSQL_URL.toString()));
            dataSource.setUser(properties.getProperty(JdbcConfig.MYSQL_USER.toString()));
            dataSource.setPassword(properties.getProperty(JdbcConfig.MYSQL_PASSWD.toString()));
        } catch (IOException | PropertyVetoException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return connection;
    }
}
