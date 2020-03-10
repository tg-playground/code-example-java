package com.taogen.example.jdbc.viii.storedprocedure;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * @author Taogen
 */
public class StoredProcedureExample {

    private static final Logger logger = LogManager.getLogger();

    /**
     * @return stored procedure name
     * @throws SQLException
     */
    public String createProcedure() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement()
        ) {
            String procedureName = "COUNT_TABLE";
            String queryDrop = "drop procedure if exists " + procedureName;
            statement.execute(queryDrop);
            //  As a general rule (and therefore probably subject to exceptions),
            //  DBMS do not allow you to use placeholders (parameters) for structural elements of a query
            //  such as table names or column names; they only allow you to specify values such as column values.
            String createProcedure = new StringBuilder().append("create procedure ")
                    .append(procedureName)
                    .append(" (in username varchar(64), out table_count int) begin select count(*) into table_count from test where name=username;  end")
                    .toString();
            statement.execute(createProcedure);
            return procedureName;
        }
    }

    public Object callProcedure() throws SQLException {
        createProcedure();
        String procedureName = "COUNT_TABLE";
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                CallableStatement callableStatement = connection.prepareCall("{call " + procedureName + " (?, ?)}")
        ) {
            String name = "Tom";
            callableStatement.setString(1, name);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.executeQuery();
            String count = callableStatement.getString(2);
            logger.debug("count is {}", count);
            return count;
        }
    }

}
