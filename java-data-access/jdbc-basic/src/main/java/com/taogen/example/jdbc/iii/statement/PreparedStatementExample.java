package com.taogen.example.jdbc.iii.statement;

import com.taogen.example.jdbc.constant.PreparedStatementSql;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.taogen.example.jdbc.constant.PreparedStatementSql.*;

public class PreparedStatementExample {

    public static final String NAME = "preparedStatement";
    public static final String UPDATE_NAME = "hello-preparedStatement";
    private static final Logger logger = LogManager.getLogger();

    public static void executeSqlFile() {
        // TODO
    }

    public boolean isTableExist() throws SQLException {
        ResultSet resultSet = PreparedStatementUtil.executeDqlSql(CHECK_TABLE_EXIST_SQL);
        resultSet.next();
        int count = resultSet.getInt("count");
        return count > 0;
    }

    public boolean createTable() {
        try {
            PreparedStatementUtil.executeDdlSql(CREATE_TABLE_SQL);
            return true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return false;
    }

    public boolean dropTable() {
        try {
            PreparedStatementUtil.executeDdlSql(DROP_TABLE_SQL);
            return true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return false;
    }

    public long count() throws SQLException {
        ResultSet resultSet = PreparedStatementUtil.executeDqlSql("select count(*) as count from test");
        long count = ResultSetUtil.getCountFiledFromResultSet(resultSet);
        logger.debug("count is {}", count);
        return count;
    }

    public int insert() throws SQLException {
        int count = PreparedStatementUtil.executeDmlSql(INSERT_SQL, NAME);
        logger.debug("insert {} rows", count);
        return count;
    }

    public int delete() throws SQLException {
        int count = PreparedStatementUtil.executeDmlSql(DELETE_SQL, NAME, UPDATE_NAME);
        logger.debug("delete {} rows", count);
        return count;
    }

    public int update() throws SQLException {
        int count = PreparedStatementUtil.executeDmlSql(UPDATE_SQL, UPDATE_NAME, NAME);
        logger.debug("delete {} rows", count);
        return count;
    }

    public ResultSet select() throws SQLException {
        ResultSet resultSet = PreparedStatementUtil.executeDqlSql(SELECT_SQL);
        logger.debug("select {} rows", ResultSetUtil.getResultSetSize(resultSet));
        return resultSet;
    }

    public int batchInsert() throws SQLException {
        // remove all before insert
        PreparedStatementUtil.getPreparedStatement("delete from test").executeUpdate();

        // insert
        PreparedStatement preparedStatement = PreparedStatementUtil.getPreparedStatement(PreparedStatementSql.INSERT_SQL);
        Connection connection = preparedStatement.getConnection();
        connection.setAutoCommit(false);
        int insertCount = 10;
        for (int i = 1; i <= insertCount; i++) {
            preparedStatement.setString(1, NAME);
            preparedStatement.addBatch();
        }
        int[] insertCounts = preparedStatement.executeBatch();
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
