package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._4resultset.ResultSetUtil;
import com.taogen.example.jdbc.constant.PreparedStatementSQL;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.taogen.example.jdbc._3statement.PreparedStatementUtil.*;
import static com.taogen.example.jdbc.constant.PreparedStatementSQL.*;

public class PreparedStatementExample {

    private static final Logger logger = LogManager.getLogger();
    public static final String NAME = "preparedStatement";
    public static final String UPDATE_NAME = "hello-preparedStatement";

    public static void executeSqlFile() {
        // TODO
    }

    public static int checkTableExist() {
        ResultSet resultSet = executeDqlSql(CHECK_TABLE_EXIST_SQL);
        int count = -1;
        try {
            resultSet.next();
            count = resultSet.getInt("count");
            logger.debug("count is {}", count);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return count;
    }

    public static void createTable() {
        executeDdlSQL(CREATE_TABLE_SQL);
    }

    public static void dropTable() {
        executeDdlSQL(DROP_TABLE_SQL);
    }

    public static long count() throws SQLException {
        ResultSet resultSet = executeDqlSql("select count(*) as count from test");
        long count = -1;
        count = ResultSetUtil.getCountFromResultSet(resultSet);
        logger.debug("count is {}", count);
        return count;
    }

    public static int insert() {
        int count = executeDmlSql(INSERT_SQL, NAME);
        logger.debug("insert {} rows", count);
        return count;
    }

    public static int delete() {
        int count = executeDmlSql(DELETE_SQL, NAME, UPDATE_NAME);
        logger.debug("delete {} rows", count);
        return count;
    }

    public static int update() {
        int count = executeDmlSql(UPDATE_SQL, UPDATE_NAME, NAME);
        logger.debug("delete {} rows", count);
        return count;
    }

    public static ResultSet select() {
        ResultSet resultSet = executeDqlSql(SELECT_SQL);
        logger.debug("select {} rows", ResultSetUtil.getResultSetSize(resultSet));
        return resultSet;
    }

    public static int batchInsert() throws SQLException {
        // remove all before insert
        PreparedStatementUtil.getPreparedStatement("delete from test").executeUpdate();

        // insert
        PreparedStatement preparedStatement = PreparedStatementUtil.getPreparedStatement(PreparedStatementSQL.INSERT_SQL);
        Connection connection = preparedStatement.getConnection();
        connection.setAutoCommit(false);
        for (int i = 1; i <= 10; i++){
            preparedStatement.setString(1, NAME);
            preparedStatement.addBatch();
        }
        int[] insertCounts = preparedStatement.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        int count = 0;
        for (int c : insertCounts){
            count += c;
        }
        logger.debug("batch insert count is {}", count);
        return count;
    }
}
