package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementUtil {

    private static final Logger logger = LogManager.getLogger();

    public static void executeDdlSQL(String sql) {
        try {
            getStatement().execute(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    public static int executeDmlSql(String sql) {
        int count = 0;
        try {
            count = getStatement().executeUpdate(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return count;
    }

    public static ResultSet executeDqlSql(String sql) {
        ResultSet resultSet = null;
        try {
            resultSet = getStatement().executeQuery(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return resultSet;
    }

    public static Statement getStatement() {
        Statement statement = null;
        try {
            statement = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce().createStatement();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return statement;
    }
}
