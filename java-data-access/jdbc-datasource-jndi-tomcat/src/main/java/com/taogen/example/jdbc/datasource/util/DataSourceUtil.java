package com.taogen.example.jdbc.datasource.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtil {

    private static final Logger logger = LogManager.getLogger();
    private static DataSource dataSource;

    public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (DataSourceUtil.class){
                if (dataSource == null){
                    try {
                        Context context = new InitialContext();
                        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
                    } catch (NamingException e) {
                        logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
                    }
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
        return connection;
    }
}
