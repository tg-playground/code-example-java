package com.taogen.example.jdbc.ii.datasource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author Taogen
 */
public class ConnectionUtil {

    private static final Logger logger = LogManager.getLogger();
    private static DataSourceWithSpecificDriverExample dataSourceWithSpecificDriverExample = new DataSourceWithSpecificDriverExample();

    private ConnectionUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static Connection getConnection() {
        return dataSourceWithSpecificDriverExample.getConnectionFromDataSoruce();
    }

    public static Connection getReadUncommittedConnection() throws SQLException {
        Connection connection = dataSourceWithSpecificDriverExample.getConnectionFromDataSoruce();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        return connection;
    }

    public static Connection getReadCommittedConnection() throws SQLException {
        Connection connection = dataSourceWithSpecificDriverExample.getConnectionFromDataSoruce();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }

    public static Connection getRepeatableReadConnection() throws SQLException {
        Connection connection = dataSourceWithSpecificDriverExample.getConnectionFromDataSoruce();
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        logger.debug("Is support repeatable read level: {}", databaseMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_REPEATABLE_READ));
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        return connection;
    }

    public static Connection getSerializableConnection() throws SQLException {
        Connection connection = dataSourceWithSpecificDriverExample.getConnectionFromDataSoruce();
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return connection;
    }
}
