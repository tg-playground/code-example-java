package com.taogen.example.jdbc._2connection;

import com.taogen.example.jdbc.constant.DatabaseType;
import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceWithDBCPExample {

    private static final Logger logger = LogManager.getLogger();
    private static BasicDataSource dataSource = new BasicDataSource();

    public static DataSource getDataSource(DatabaseType databaseType){

        Properties properties = null;
        try {
            properties = PropertyUtils.getProperitesByFilePath("db.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (DatabaseType.MySQL.equals(databaseType)) {
            dataSource.setDriverClassName(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_DRIVER_CLASS)));
            dataSource.setUrl(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL)));
            dataSource.setUsername(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER)));
            dataSource.setPassword(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD)));
        }else if (DatabaseType.Oracle.equals(databaseType)){
            dataSource.setDriverClassName(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_DRIVER_CLASS)));
            dataSource.setUrl(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_URL)));
            dataSource.setUsername(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_USER)));
            dataSource.setPassword(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_PASSWD)));
        }
        return dataSource;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = getDataSource(DatabaseType.MySQL).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.debug("connection is {}", connection);
        return connection;
    }
}
