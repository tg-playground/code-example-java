package com.taogen.example.jdbc.ii.datasource;

import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.LoggerUtil;
import com.taogen.example.jdbc.utils.PropertyUtils;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Taogen
 */
public class DataSourceWithHikaricpExample {
    private static final Logger logger = LogManager.getLogger();
    private static HikariConfig hikariConfig = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        Properties properties = null;
        try {
            properties = PropertyUtils.getPropertiesByFilePath("db.properties");
            hikariConfig.setJdbcUrl(properties.getProperty(JdbcConfig.MYSQL_URL.toString()));
            hikariConfig.setDriverClassName(properties.getProperty(JdbcConfig.MYSQL_DRIVER_CLASS.toString()));
            hikariConfig.setUsername(properties.getProperty(JdbcConfig.MYSQL_USER.toString()));
            hikariConfig.setPassword(properties.getProperty(JdbcConfig.MYSQL_PASSWD.toString()));
            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        } catch (IOException e) {
            LoggerUtil.loggerError(logger, e);
        }
        dataSource = new HikariDataSource(hikariConfig);
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
