package com.taogen.example.jdbc.v.transaction;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {

    private static final Logger logger = LogManager.getLogger();

    public boolean transactionCommit() {
        Connection connection = getTransactionConnection();
        try {
            Statement statement = connection.createStatement();
            initialData(statement);
            // transaction begin...
            connection.setAutoCommit(false);
            executeMultipleStatements(statement);
            connection.commit();
            return true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LoggerUtil.loggerError(logger, e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LoggerUtil.loggerError(logger, e);
            }
        }
        return false;
    }

    public boolean transactionRollback() {
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
            try {
                connection.rollback();
                return true;
            } catch (SQLException e1) {
                LoggerUtil.loggerError(logger, e);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                LoggerUtil.loggerError(logger, e);
            }
        }
        return false;
    }

    private Connection getTransactionConnection() {
        Connection connection = ConnectionUtil.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return connection;
    }

    private void initialData(Statement statement) {
        try {
            statement.executeUpdate("insert into test (id, name) values (1, 'hello') on duplicate key update name='hello'");
            statement.executeUpdate("insert into test (id, name) values (2, 'hello') on duplicate key update name='hello'");
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    private void executeMultipleStatements(Statement statement) throws SQLException {
        statement.executeUpdate("update test set name='hello1' where id=1");
        statement.executeUpdate("update test set name='hello2' where id=2");
    }

    private void executeMultipleStatementsWithError(Statement statement) throws SQLException {
        statement.executeUpdate("update test set name='hello1' where id=1");
        int i = 1 / 0;
        statement.executeUpdate("update test set name='hello2' where id=2");
    }

}
