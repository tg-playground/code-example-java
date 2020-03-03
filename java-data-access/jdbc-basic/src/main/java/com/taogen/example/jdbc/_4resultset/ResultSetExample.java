package com.taogen.example.jdbc._4resultset;

import com.taogen.example.jdbc._2connection_datasource.DataSourceWithSpecificDriverExample;
import com.taogen.example.jdbc.constant.StatementSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetExample {

    private static final Logger logger = LogManager.getLogger();

    public static void printResultSet() throws SQLException {
        ResultSet resultSet = getResultSet();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            logger.info("{} \t {}", id, name);
        }
    }

    private static ResultSet getResultSet() throws SQLException {
        Statement statement = DataSourceWithSpecificDriverExample.getConnectionFromDataSoruce().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        statement.execute(StatementSQL.CREATE_TABLE_SQL);
        statement.executeUpdate(StatementSQL.INSERT_SQL);
        return statement.executeQuery(StatementSQL.SELECT_SQL);
    }

    public static void updateRowInResultSet(String newname) throws SQLException {
        ResultSet resultSet = getResultSet();
        while (resultSet.next()) {
            resultSet.updateString("name", newname);
            resultSet.updateRow();
        }
    }

    public static void insertRowInResultSet(String name) throws SQLException {
        ResultSet resultSet = getResultSet();
        resultSet.moveToInsertRow();
        resultSet.updateString("name", name);
        resultSet.insertRow();
    }
}
