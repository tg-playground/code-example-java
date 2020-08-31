package com.taogen.example.jdbc.ii.datasource;

import com.taogen.example.jdbc.constant.DatabaseType;
import com.taogen.example.jdbc.constant.JdbcConfig;
import com.taogen.example.jdbc.utils.LoggerUtil;
import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Taogen
 */
public class DataSourceWithDbcpExample {

    private static final Logger logger = LogManager.getLogger();
    private static BasicDataSource dataSource = new BasicDataSource();

    private static DataSource getDataSource(DatabaseType databaseType) throws IOException {

        Properties properties;
        try {
            properties = PropertyUtils.getPropertiesByFilePath("db.properties");
        } catch (IOException e) {
            LoggerUtil.loggerError(logger, e);
            throw e;
        }
        if (DatabaseType.MYSQL.equals(databaseType)) {
            dataSource.setDriverClassName(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_DRIVER_CLASS)));
            dataSource.setUrl(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_URL)));
            dataSource.setUsername(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_USER)));
            dataSource.setPassword(properties.getProperty(String.valueOf(JdbcConfig.MYSQL_PASSWD)));
        } else if (DatabaseType.ORACLE.equals(databaseType)) {
            dataSource.setDriverClassName(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_DRIVER_CLASS)));
            dataSource.setUrl(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_URL)));
            dataSource.setUsername(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_USER)));
            dataSource.setPassword(properties.getProperty(String.valueOf(JdbcConfig.ORACLE_PASSWD)));
        }
        return dataSource;
    }

    public Connection getConnection() throws IOException, SQLException {
        Connection connection;
        try {
            connection = getDataSource(DatabaseType.MYSQL).getConnection();
        } catch (SQLException | IOException e) {
            LoggerUtil.loggerError(logger, e);
            throw e;
        }
        logger.debug("connection is {}", connection);
        return connection;
    }
}
