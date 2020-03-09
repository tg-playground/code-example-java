package com.taogen.example.jdbc.vii.datatype;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class NumericDataTypesExampleTest {

    private NumericDataTypesExample numericDataTypesExample = new NumericDataTypesExample();

    @Test
    public void addNumericData() throws SQLException {
        assertEquals(1, numericDataTypesExample.addNumericData());
    }
}