package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedStatementUtil {

    private static final Logger logger = LogManager.getLogger();

    private PreparedStatementUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void executeDdlSQL(String sql) {
        try {
            getPreparedStatement(sql).execute();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
    }

    public static int executeDmlSql(String sql, Object... objects) {
        int count = -1;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            setPreparedStatementParams(preparedStatement, objects);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return count;
    }

    public static ResultSet executeDqlSql(String sql, Object... objects) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql);
            setPreparedStatementParams(preparedStatement, objects);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return resultSet;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce().prepareStatement(sql);
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
            throw e;
        }
        return preparedStatement;
    }

    public static void setPreparedStatementParams(PreparedStatement preparedStatement, Object... objects) {
        for (int i = 1; i <= objects.length; i++) {
            try {
                logger.debug("parameter {} is: {}", i, objects[i - 1]);
                preparedStatement.setObject(i, objects[i - 1]);
            } catch (SQLException e) {
                LoggerUtil.loggerError(logger, e);
            }
        }
    }
}
