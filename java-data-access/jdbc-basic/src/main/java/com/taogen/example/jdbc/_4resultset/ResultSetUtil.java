package com.taogen.example.jdbc._4resultset;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc.constant.StatementSQL;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetUtil {

    private static final Logger logger = LogManager.getLogger();

    private ResultSetUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int getResultSetSize(ResultSet resultSet) {
        int count = 0;
        if (resultSet != null) {
            try {
                resultSet.last();
                count = resultSet.getRow();
                resultSet.beforeFirst();
            } catch (SQLException e) {
                LoggerUtil.loggerError(logger, e);
            }
        }
        return count;
    }

    public static long getCountFromResultSet(ResultSet resultSet) throws SQLException {
        long count = -1;
        while (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }

    public static ResultSet getUpdatableResultSet() throws SQLException {
        Statement statement = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        return statement.executeQuery(StatementSQL.SELECT_SQL);
    }
}
