package com.taogen.example.jdbc.iv.resultset;

import com.taogen.example.jdbc.iii.statement.StatementUtil;
import com.taogen.example.jdbc.constant.StatementSQL;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetExample {

    private static final Logger logger = LogManager.getLogger();

    public boolean printResultSet() {
        try {
            ResultSet resultSet = StatementUtil.executeDqlSql(StatementSQL.SELECT_SQL);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                logger.info("{} \t {}", id, name);
            }
            return true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return false;
    }


    public void updateAllRowsInResultSet(String newname) throws SQLException {
        ResultSet resultSet = ResultSetUtil.getUpdatableResultSet();
        while (resultSet.next()) {
            resultSet.updateString("name", newname);
            resultSet.updateRow();
        }
    }

    public void insertNewRowInResultSet(String name) throws SQLException {
        ResultSet resultSet = ResultSetUtil.getUpdatableResultSet();
        resultSet.moveToInsertRow();
        resultSet.updateString("name", name);
        resultSet.insertRow();
    }
}
