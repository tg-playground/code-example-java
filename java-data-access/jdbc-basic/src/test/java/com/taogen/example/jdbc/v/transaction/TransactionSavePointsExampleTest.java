package com.taogen.example.jdbc.v.transaction;

import com.taogen.example.jdbc.constant.StatementSql;
import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionSavePointsExampleTest {
    private static final Logger logger = LogManager.getLogger();

    @Before
    public void init() throws SQLException {
        createEmptyTable();
    }

    private void createEmptyTable() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            connection.setAutoCommit(true);
            statement.execute("drop table if exists test");
            statement.execute(StatementSql.CREATE_TABLE_SQL);
        }
    }

    @Test
    public void rollbackAtSpecifiedSavePoint() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        logger.debug("Transaction started...");
        // save point 1
        Savepoint savepoint1 = connection.setSavepoint();
        statement.executeUpdate("insert into test (id, name) values (1, 'Tom')");
        // save point 2
        Savepoint savepoint2 = connection.setSavepoint();
        statement.executeUpdate("insert into test (id, name) values (2, 'Jack')");
        logger.debug("end to insert data.");

        // check the result
        logger.debug("checking the insert data result.");
        ResultSet resultSet = statement.executeQuery("select * from test");
        assertEquals(2, ResultSetUtil.getResultSetSize(resultSet));
        resultSet.next();
        assertEquals("Tom", resultSet.getString("name"));
        resultSet.next();
        assertEquals("Jack", resultSet.getString("name"));

        // rollback -- save point 2
        logger.debug("rollback to save point 2 started...");
        connection.rollback(savepoint2);
        ResultSet resultSetRollbackToPoint2 = statement.executeQuery("select * from test");
        assertEquals(1, ResultSetUtil.getResultSetSize(resultSetRollbackToPoint2));
        assertTrue(resultSetRollbackToPoint2.next());
        assertEquals("Tom", resultSetRollbackToPoint2.getString("name"));

        // release save point 2, or release by commit the transaction
        connection.releaseSavepoint(savepoint2);

        // after transaction commit, all savepoint (savepoint1 and savepoint2) in the transaction will be released
        connection.commit();

        // rollback to save point 1
        logger.debug("rollback to save point 1 started...");
        try {
            connection.rollback(savepoint1);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
            assertEquals("SQLSyntaxErrorException", e.getClass().getSimpleName());
        }

        connection.setAutoCommit(true);
    }
}