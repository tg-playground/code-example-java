package com.taogen.example.jdbc.vii.datatype;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Taogen
 */
public class JsonDataTypeExample {

    public int addJsonData() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createTable = connection.prepareStatement("create table if not exists test_json(my_json JSON)");
                PreparedStatement insert = connection.prepareStatement("insert into test_json values (?) ")
        ) {
            createTable.execute();
            insert.setString(1, "{\"name\":\"Tom\", \"age\":12}");
            return insert.executeUpdate();
        }
    }
}
