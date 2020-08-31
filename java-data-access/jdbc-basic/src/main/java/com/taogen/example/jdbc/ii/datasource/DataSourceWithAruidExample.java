package com.taogen.example.jdbc.ii.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.LoggerUtil;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Taogen
 */
public class DataSourceWithAruidExample {

    private static final Logger logger = LogManager.getLogger();
    private static DruidDataSource dataSource = new DruidDataSource();

    static {
        Properties properties = null;
        try {
            properties = PropertyUtils.getPropertiesByFilePath("db.properties");
            dataSource.setUrl(properties.getProperty(JdbcConfig.MYSQL_URL.toString()));
            dataSource.setUsername(properties.getProperty(JdbcConfig.MYSQL_USER.toString()));
            dataSource.setPassword(properties.getProperty(JdbcConfig.MYSQL_PASSWD.toString()));
        } catch (IOException e) {
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
