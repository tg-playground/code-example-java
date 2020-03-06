package com.taogen.example.jdbc.iii.statement;

import com.taogen.example.jdbc.constant.StatementSql;
import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.taogen.example.jdbc.constant.StatementSql.*;
import static com.taogen.example.jdbc.iii.statement.StatementUtil.*;

/**
 * @author Taogen
 */
public class StatementExample {

    private static final Logger logger = LogManager.getLogger();

    public static void executeSqlFile() {
        // TODO
    }

    public boolean checkTableExist() throws SQLException {
        int count = -1;
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = executeDqlSql(statement, CHECK_TABLE_EXIST_SQL);
        ) {
            resultSet.next();
            count = resultSet.getInt("count");
            logger.debug("Is table exist: {}", count == 1);
        }
        return count == 1;
    }

    public boolean createTable() {
        boolean result = false;
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            executeDdlSql(statement, CREATE_TABLE_SQL);
            result = true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return result;
    }

    public boolean dropTable() {
        boolean result = false;
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            executeDdlSql(statement, DROP_TABLE_SQL);
            result = true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return result;
    }

    public long count() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = StatementUtil.executeDqlSql(statement, StatementSql.COUNT_TABLE_SQL);
        ) {
            long count = ResultSetUtil.getCountFiledFromResultSet(resultSet);
            logger.debug("count is {}", count);
            return count;
        }
    }

    public int insert() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            int count = executeDmlSql(statement, INSERT_SQL);
            logger.debug("insert {} rows", count);
            return count;
        }
    }

    public int delete() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            int count = executeDmlSql(statement, DELETE_SQL);
            logger.debug("delete {} rows", count);
            return count;
        }
    }

    public int update() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            int count = executeDmlSql(statement, UPDATE_SQL);
            logger.debug("update {} rows", count);
            return count;
        }
    }

    public ResultSet select() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = executeDqlSql(statement, SELECT_SQL);
            logger.debug("select {} rows", ResultSetUtil.getResultSetSize(resultSet));
            return resultSet;
        }
    }

    public int batchInsert() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
        ) {
            StatementUtil.executeDdlSql(statement, "delete from test");
            connection.setAutoCommit(false);
            int insertCount = 10;
            for (int i = 1; i <= insertCount; i++) {
                statement.addBatch(StatementSql.INSERT_SQL);
            }
            int[] insertCounts = statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            int count = 0;
            for (int c : insertCounts) {
                count += c;
            }
            logger.debug("batchInsert count is {}", count);
            return count;
        }
    }
}
