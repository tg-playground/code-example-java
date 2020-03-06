package com.taogen.example.jdbc.iv.resultset;

import com.taogen.example.jdbc.constant.StatementSql;
import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iii.statement.StatementUtil;
import com.taogen.example.jdbc.utils.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Taogen
 */
public class ResultSetExample {

    private static final Logger logger = LogManager.getLogger();

    public boolean printResultSet() {
        boolean result = false;
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = StatementUtil.executeDqlSql(statement, StatementSql.SELECT_SQL);
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                logger.info("{} \t {}", id, name);
            }
            result = true;
        } catch (SQLException e) {
            LoggerUtil.loggerError(logger, e);
        }
        return result;
    }


    public void updateAllRowsInResultSet(String newName) throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = StatementUtil.getStatementWithUpdatableResult(connection);
                ResultSet resultSet = statement.executeQuery(StatementSql.SELECT_SQL);
        ) {
            while (resultSet.next()) {
                resultSet.updateString("name", newName);
                resultSet.updateRow();
            }
        }
    }

    public void insertNewRowInResultSet(String name) throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = StatementUtil.getStatementWithUpdatableResult(connection);
                ResultSet resultSet = statement.executeQuery(StatementSql.SELECT_SQL);
        ) {
            resultSet.moveToInsertRow();
            resultSet.updateString("name", name);
            resultSet.insertRow();
        }
    }
}
