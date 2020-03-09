package com.taogen.example.jdbc.vii.datatype;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringDataTypesExampleTest {

    private StringDataTypesExample stringDataTypesExample = new StringDataTypesExample();

    @Test
    public void addStringData() throws IOException, SQLException {
        assertEquals(1, stringDataTypesExample.addStringData());
    }

    @Test
    public void getStringData() throws IOException, SQLException {
        assertTrue(stringDataTypesExample.getStringData());
    }
}