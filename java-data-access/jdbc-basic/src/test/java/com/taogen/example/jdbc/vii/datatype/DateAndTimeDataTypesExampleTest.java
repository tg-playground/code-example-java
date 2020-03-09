package com.taogen.example.jdbc.vii.datatype;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DateAndTimeDataTypesExampleTest {

    private DateAndTimeDataTypesExample dateAndTimeDataTypesExample = new DateAndTimeDataTypesExample();

    @Test
    public void addDateTimeData() throws SQLException {
        assertEquals(1, dateAndTimeDataTypesExample.addDateTimeData());
    }
}