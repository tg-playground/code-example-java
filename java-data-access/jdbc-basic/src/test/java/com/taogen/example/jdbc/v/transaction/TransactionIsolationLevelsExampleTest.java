package com.taogen.example.jdbc.v.transaction;

import com.mysql.cj.jdbc.exceptions.MySQLTimeoutException;
import com.taogen.example.jdbc.constant.StatementSql;
import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class TransactionIsolationLevelsExampleTest {

    public static final Logger logger = LogManager.getLogger();

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
    public void readUncommitted() throws SQLException {
        Connection otherConnection = ConnectionUtil.getConnection();
        otherConnection.setAutoCommit(false);
        Statement otherStatement = otherConnection.createStatement();
        otherStatement.executeUpdate("insert into test (id, name) values (1, 'Tom') on duplicate key update name='Tom'");

        try (
                Connection connection = ConnectionUtil.getReadUncommittedConnection()
        ) {
            // before otherConnection commit, equals
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test where id = 1");
            resultSet.next();
            assertEquals("Tom", resultSet.getString("name"));

            otherConnection.commit();

            // after otherConnection commit, equals
            ResultSet resultSet2 = statement.executeQuery("select * from test where id = 1");
            resultSet2.next();
            assertEquals("Tom", resultSet2.getString("name"));
        }

        releaseResource(otherConnection, otherStatement);
    }

    private void releaseResource(Connection connection, Statement statement) throws SQLException {
        statement.close();
        connection.setAutoCommit(true);
        connection.close();
    }

    @Test
    public void readCommitted() throws SQLException {
        Connection otherConnection = ConnectionUtil.getConnection();
        otherConnection.setAutoCommit(false);
        Statement otherStatement = otherConnection.createStatement();
        otherStatement.executeUpdate("insert into test (id, name) values (1, 'Tom') on duplicate key update name='Tom'");

        try (
                Connection connection = ConnectionUtil.getReadCommittedConnection()
        ) {
            // before otherConnection commit, resultSet is null
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test where id = 1");
            assertFalse(resultSet.next());

            // otherConnection commit insert
            otherConnection.commit();

            // After otherConnection committed. Result is equals
            ResultSet resultSet2 = statement.executeQuery("select * from test where id = 1");
            assertTrue(resultSet2.next());
            assertEquals("Tom", resultSet2.getString("name"));

            // OtherConnection commit update: actually write in database, but not effect the repeatable read transaction data.
            otherStatement.executeUpdate("update test set name='Jack' where id=1");
            otherConnection.commit();

            // After otherConnection update. Result is not equals
            ResultSet resultSet3 = statement.executeQuery("select * from test where id = 1");
            assertTrue(resultSet3.next());
            assertNotEquals("Tom", resultSet3.getString("name"));

            connection.commit();
            connection.setAutoCommit(true);
        }

        releaseResource(otherConnection, otherStatement);
    }

    @Test
    public void repeatableRead() throws SQLException {
        Connection otherConnection = ConnectionUtil.getConnection();
        otherConnection.setAutoCommit(false);
        Statement otherStatement = otherConnection.createStatement();
        otherStatement.executeUpdate("insert into test (id, name) values (1, 'Tom') on duplicate key update name='Tom'");
        otherConnection.commit();

        try (
                Connection connection = ConnectionUtil.getRepeatableReadConnection()
        ) {

            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            // before otherConnection update
            ResultSet resultSet = statement.executeQuery("select * from test where id=1");
            assertTrue(resultSet.next());
            assertEquals("Tom", resultSet.getString("name"));

            // OtherConnection commit update: actually write in database, but not effect the repeatable read transaction data.
            otherStatement.executeUpdate("update test set name='Jack' where id=1");
            otherStatement.executeUpdate("insert into test (id, name) values (2, 'Tom2') on duplicate key update name='Tom2'");
            otherConnection.commit();

            // after otherConnection update
            ResultSet resultSet2 = statement.executeQuery("select * from test where id=1");
            assertTrue(resultSet2.next());
            assertEquals("Tom", resultSet2.getString("name"));
//            long count = ResultSetUtil.getCountFromResultSet(statement.executeQuery("select count(*) as count from test"));
//            assertNotEquals(1, count);

            connection.commit();
            connection.setAutoCommit(true);
        }

        releaseResource(otherConnection, otherStatement);
    }

    @Test
    public void serializable() throws SQLException {
        Connection otherConnection = ConnectionUtil.getConnection();
        otherConnection.setAutoCommit(false);
        Statement otherStatement = otherConnection.createStatement();
        otherStatement.executeUpdate("insert into test (id, name) values (1, 'Tom') on duplicate key update name='Tom'");
        otherConnection.commit();
        otherStatement.setQueryTimeout(3);

        try (
                Connection connection = ConnectionUtil.getSerializableConnection()
        ) {

            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();

            // before otherConnection update
            logger.debug("connection transaction begin...");
            ResultSet resultSet = statement.executeQuery("select * from test where id=1");
            assertTrue(resultSet.next());
            assertEquals("Tom", resultSet.getString("name"));

            // OtherConnection commit update: actually write in database, but not effect the repeatable read transaction data.
            logger.debug("other connection insert and update begin...");
            try {
                otherStatement.executeUpdate("update test set name='Jack' where id=1");
                otherStatement.executeUpdate("insert into test (id, name) values (2, 'Tom2') on duplicate key update name='Tom2'");
                otherConnection.commit();
            } catch (MySQLTimeoutException e) {
                LoggerUtil.loggerError(logger, e);
            }

            // after otherConnection update
            // other connection should not execute sql, during serializable transaction.
            logger.debug("check change of data in one transaction begin...");
            ResultSet resultSet2 = statement.executeQuery("select * from test where id=1");
            assertTrue(resultSet2.next());
            assertEquals("Tom", resultSet2.getString("name"));
            long count = ResultSetUtil.getCountFiledFromResultSet(statement.executeQuery("select count(*) as count from test"));
            assertEquals(1, count);

            connection.commit();
            connection.setAutoCommit(true);
        }

        releaseResource(otherConnection, otherStatement);
    }
}