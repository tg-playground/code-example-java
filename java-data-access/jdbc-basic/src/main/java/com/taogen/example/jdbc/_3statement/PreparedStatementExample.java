package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._4resultset.ResultSetExample;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.taogen.example.jdbc._3statement.PreparedStatementUtil.*;
import static com.taogen.example.jdbc.constant.PreparedStatementSQL.*;

public class PreparedStatementExample {

    private static final Logger logger = LogManager.getLogger();
    public static final String NAME = "preparedStatement";
    public static final String UPDATE_NAME = "hello-preparedStatement";

    public static void executeSqlFile() {
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
        logger.debug("select {} rows", ResultSetExample.getResultSetCount(resultSet));
        return resultSet;
    }

}
