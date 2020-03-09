package com.taogen.example.jdbc.vii.datatype;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class JsonDataTypeExampleTest {
    private JsonDataTypeExample jsonDataTypeExample = new JsonDataTypeExample();

    @Test
    public void addJsonData() throws SQLException {
        assertEquals(1, jsonDataTypeExample.addJsonData());
    }
}