package com.taogen.example.jdbc._2connection;

import com.taogen.example.jdbc.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceWithTomcatJndiExample {

    private static final Logger logger = LogManager.getLogger();

    public static DataSource getDataSource() {
        Context context = null;
        DataSource dataSource = null;
        Properties properties = null;
        try {
            properties = PropertyUtils.getProperitesByFilePath("jndi.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("jndi url is {}", properties.getProperty("jndi.java.naming.provider.url"));
        try {
            context = new InitialContext(properties);
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/MyLocalDB");
            logger.debug("dataSource is {}", dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static Connection getConnection() {
        Connection connection = null;
        DataSource dataSource = getDataSource();
        try {
            connection = dataSource.getConnection();
            logger.debug("connection is {}", connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
