package com.taogen.example.jdbc.iii.statement;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.taogen.example.jdbc.constant.PreparedStatementSql.*;

/**
 * @author Taogen
 */
public class PreparedStatementExample {

    public static final String NAME = "preparedStatement";
    public static final String UPDATE_NAME = "hello-preparedStatement";
    private static final Logger logger = LogManager.getLogger();

    public static void executeSqlFile() {
        // TODO
    }

    public boolean isTableExist() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CHECK_TABLE_EXIST_SQL);
                ResultSet resultSet = PreparedStatementUtil.executeDqlSql(preparedStatement)
        ) {
            long count = ResultSetUtil.getCountFiledFromResultSet(resultSet);
            return count > 0;
        }
    }

    public boolean createTable() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_SQL)
        ) {
            PreparedStatementUtil.executeDdlSql(preparedStatement);
            return true;
        }
    }

    public boolean dropTable() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DROP_TABLE_SQL)
        ) {
            PreparedStatementUtil.executeDdlSql(preparedStatement);
            return true;
        }
    }

    public long count() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("select count(*) as count from test");
                ResultSet resultSet = PreparedStatementUtil.executeDqlSql(preparedStatement)
        ) {
            long count = ResultSetUtil.getCountFiledFromResultSet(resultSet);
            logger.debug("count is {}", count);
            return count;
        }
    }

    public int insert() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)
        ) {
            int count = PreparedStatementUtil.executeDmlSql(preparedStatement, NAME);
            logger.debug("insert {} rows", count);
            return count;
        }
    }

    public int delete() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)
        ) {
            int count = PreparedStatementUtil.executeDmlSql(preparedStatement, NAME, UPDATE_NAME);
            logger.debug("delete {} rows", count);
            return count;
        }
    }

    public int update() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)
        ) {
            int count = PreparedStatementUtil.executeDmlSql(preparedStatement, UPDATE_NAME, NAME);
            logger.debug("update {} rows", count);
            return count;
        }
    }

    public ResultSet select() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                ResultSet resultSet = PreparedStatementUtil.executeDqlSql(preparedStatement)
        ) {
            logger.debug("select {} rows", ResultSetUtil.getResultSetSize(resultSet));
            return resultSet;
        }
    }

    public int batchInsert() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement deletePreparedStatement = connection.prepareStatement("delete from test");
                PreparedStatement insertPreparedStatement = connection.prepareStatement(INSERT_SQL)
        ) {
            // remove all before insert
            PreparedStatementUtil.executeDmlSql(deletePreparedStatement);

            // insert
            connection.setAutoCommit(false);
            int insertCount = 10;
            for (int i = 1; i <= insertCount; i++) {
                insertPreparedStatement.setString(1, NAME);
                insertPreparedStatement.addBatch();
            }
            int[] insertCounts = insertPreparedStatement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
            int count = 0;
            for (int c : insertCounts) {
                count += c;
            }
            logger.debug("batch insert count is {}", count);
            return count;
        }
    }
}
