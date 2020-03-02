package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc._4resultset.ResultSetExample;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementExample {

    private static final Logger logger = LogManager.getLogger();
    public static final String CREATE_TABLE_SQL = "create table if not exists test (id int not null primary key auto_increment, name varchar(64) not null)";
    public static final String INSERT_SQL = "insert into test (name) value ('hello')";
    public static final String UPDATE_SQL = "update test set name ='helloworld' where name ='hello'";
    public static final String SELECT_SQL = "select * from test";
    public static final String DELETE_SQL = "delete from test where name='hello' or name='helloworld'";
    public static final String DROP_TABLE_SQL = "drop table if exists test";
    public static final String CHECK_TABLE_EXIST_SQL = "select count(*) as count from information_schema.tables where table_schema = 'test' and table_name = 'test'";

    public static void executeSqlFile(){
    }

    public static int checkTableExist(){
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

    public static void createTable(){
        executeDdlSQL(CREATE_TABLE_SQL);
    }

    public static void dropTable(){
        executeDdlSQL(DROP_TABLE_SQL);
    }

    public static int insert(){
        int count = executeDmlSql(INSERT_SQL);
        logger.debug("insert {} rows", count);
        return count;
    }

    public static int delete(){
        int count = executeDmlSql(DELETE_SQL);
        logger.debug("delete {} rows", count);
        return count;
    }

    public static int update(){
        int count = executeDmlSql(UPDATE_SQL);
        logger.debug("update {} rows", count);
        return count;
    }

    public static ResultSet select(){
        ResultSet resultSet = executeDqlSql(SELECT_SQL);
        logger.debug("select {} rows", ResultSetExample.getResultSetCount(resultSet));
        return resultSet;
    }

    private static void executeDdlSQL(String sql){
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    private static int executeDmlSql(String sql){
        int count = 0;
        try {
            count = getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return count;
    }

    private static ResultSet executeDqlSql(String sql){
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return resultSet;
    }

    private static Statement getStatement(){
        Statement statement = null;
        try {
            statement = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce().createStatement();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return statement;
    }
}
