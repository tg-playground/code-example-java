package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._4resultset.ResultSetUtil;
import com.taogen.example.jdbc.constant.StatementSQL;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.taogen.example.jdbc._3statement.StatementUtil.*;
import static com.taogen.example.jdbc.constant.StatementSQL.*;

public class StatementExample {

    private static final Logger logger = LogManager.getLogger();

    public static void executeSqlFile() {
        // TODO
    }

    public static boolean checkTableExist() {
        ResultSet resultSet = executeDqlSql(CHECK_TABLE_EXIST_SQL);
        int count = -1;
        try {
            resultSet.next();
            count = resultSet.getInt("count");
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        logger.debug("Is table exist: {}", count == 1);
        return count == 1;
    }

    public static void createTable() {
        executeDdlSQL(CREATE_TABLE_SQL);
    }

    public static void dropTable() {
        executeDdlSQL(DROP_TABLE_SQL);
    }

    public static long count() throws SQLException {
        long count = -1;
        ResultSet resultSet = StatementUtil.executeDqlSql(StatementSQL.COUNT_TABLE_SQL);
        count = ResultSetUtil.getCountFromResultSet(resultSet);
        logger.debug("count is {}", count);
        return count;
    }

    public static int insert() {
        int count = executeDmlSql(INSERT_SQL);
        logger.debug("insert {} rows", count);
        return count;
    }

    public static int delete() {
        int count = executeDmlSql(DELETE_SQL);
        logger.debug("delete {} rows", count);
        return count;
    }

    public static int update() {
        int count = executeDmlSql(UPDATE_SQL);
        logger.debug("update {} rows", count);
        return count;
    }

    public static ResultSet select() {
        ResultSet resultSet = executeDqlSql(SELECT_SQL);
        logger.debug("select {} rows", ResultSetUtil.getResultSetSize(resultSet));
        return resultSet;
    }

    public static int batchInsert() throws SQLException {

        Statement statement = StatementUtil.getStatement();
        statement.executeUpdate("delete from test");

        Connection connection = statement.getConnection();
        connection.setAutoCommit(false);
        for (int i = 1; i <= 10; i++) {
            statement.addBatch(StatementSQL.INSERT_SQL);
        }
        int[] insertCounts = statement.executeBatch();
        connection.commit();
        connection.setAutoCommit(true);
        int count = 0;
        for (int c : insertCounts){
            count += c;
        }
        logger.debug("batchInsert count is {}", count);
        return count;
    }
}
