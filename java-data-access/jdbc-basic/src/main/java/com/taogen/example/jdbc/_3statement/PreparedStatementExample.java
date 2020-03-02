package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc._4resultset.ResultSetExample;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementExample {

    private static final Logger logger = LogManager.getLogger();
    public static final String CREATE_TABLE_SQL = "create table if not exists test (id int not null primary key auto_increment, name varchar(64) not null)";
    public static final String INSERT_SQL = "insert into test (name) value (?)";
    public static final String UPDATE_SQL = "update test set name =? where name =?";
    public static final String SELECT_SQL = "select * from test";
    public static final String DELETE_SQL = "delete from test where name=? or name=?";
    public static final String DROP_TABLE_SQL = "drop table if exists test";
    public static final String CHECK_TABLE_EXIST_SQL = "select count(*) as count from information_schema.tables where table_schema = 'test' and table_name = 'test'";
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

    private static void executeDdlSQL(String sql) {
        try {
            getPreparedStatement(sql).execute();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    private static int executeDmlSql(String sql, Object... objects) {
        int count = -1;
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        setPreparedStatementParams(preparedStatement, objects);
        try {
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return count;
    }

    private static ResultSet executeDqlSql(String sql, Object... objects) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = getPreparedStatement(sql);
        setPreparedStatementParams(preparedStatement, objects);
        try {
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return resultSet;
    }

    private static PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce().prepareStatement(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return preparedStatement;
    }

    private static void setPreparedStatementParams(PreparedStatement preparedStatement, Object... objects){
        for (int i=1; i <= objects.length; i++) {
            try {
                logger.debug("parameter {} is: {}", i, objects[i-1]);
                preparedStatement.setObject(i, objects[i-1]);
            } catch (SQLException e) {
                LoggerUtil.loggerError(logger, e);
            }
        }
    }
}
