package com.taogen.example.jdbc._4resultset;

import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
