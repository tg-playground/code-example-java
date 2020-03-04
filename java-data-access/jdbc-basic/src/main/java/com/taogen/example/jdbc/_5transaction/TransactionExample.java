package com.taogen.example.jdbc._5transaction;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {

    private static final Logger logger = LogManager.getLogger();

    public static void transactionCommit() {
        Connection connection = getTransactionConnection();
        try {
            Statement statement = connection.createStatement();
            initialData(statement);
            // transaction begin...
            connection.setAutoCommit(false);
            executeMultipleStatements(statement);
            connection.commit();
        } catch (Exception e) {
            LoggerUtil.loggerError(logger, e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void transactionRollback() {
        Connection connection = getTransactionConnection();
        try {
            Statement statement = connection.createStatement();
            initialData(statement);
            // transaction begin...
            connection.setAutoCommit(false);
            executeMultipleStatementsWithError(statement);
            connection.commit();
        } catch (Exception e) {
            LoggerUtil.loggerError(logger, e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getTransactionConnection() {
        Connection connection = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static void initialData(Statement statement) {
        try {
            statement.executeUpdate("insert into test (id, name) values (1, 'hello') on duplicate key update name='hello'");
            statement.executeUpdate("insert into test (id, name) values (2, 'hello') on duplicate key update name='hello'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeMultipleStatements(Statement statement) throws SQLException {
        statement.executeUpdate("update test set name='hello1' where id=1");
        statement.executeUpdate("update test set name='hello2' where id=2");
    }

    private static void executeMultipleStatementsWithError(Statement statement) throws SQLException {
        statement.executeUpdate("update test set name='hello1' where id=1");
        int i = 1 / 0;
        statement.executeUpdate("update test set name='hello2' where id=2");
    }

}
