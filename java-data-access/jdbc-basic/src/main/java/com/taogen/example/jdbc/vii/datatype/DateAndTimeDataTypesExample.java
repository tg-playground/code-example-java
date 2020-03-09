package com.taogen.example.jdbc.vii.datatype;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;

import java.sql.*;

/**
 * @author Taogen
 */
public class DateAndTimeDataTypesExample {
    /**
     * DATE - A date. The supported range is '1000-01-01' to '9999-12-31'. MySQL displays DATE values in 'YYYY-MM-DD' format, but permits assignment of values to DATE columns using either strings or numbers.
     * DATETIME - A date and time combination. The supported range is '1000-01-01 00:00:00.000000' to '9999-12-31 23:59:59.999999'. MySQL displays DATETIME values in 'YYYY-MM-DD hh:mm:ss[.fraction]' format, but permits assignment of values to DATETIME columns using either strings or numbers.
     * TIMESTAMP - A timestamp. The range is '1970-01-01 00:00:01.000000' UTC to '2038-01-19 03:14:07.999999' UTC.
     * TIME - A time. The range is '-838:59:59.000000' to '838:59:59.000000'. MySQL displays TIME values in 'hh:mm:ss[.fraction]' format, but permits assignment of values to TIME columns using either strings or numbers.
     * YEAR - A year in 4-digit format. MySQL displays YEAR values in YYYY format, but permits assignment of values to YEAR columns using either strings or numbers. Values display as 1901 to 2155, or 0000.
     */
    public int addDateTimeData() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createTable = connection.prepareStatement("create table if not exists test_datetime(" +
                        "my_date date, my_datetime datetime, my_timestamp timestamp, my_time time, my_year year)");
                PreparedStatement insert = connection.prepareStatement("insert into test_datetime values (?, ?, ?, ?, ?)")
        ) {
            createTable.execute();
            // DATE, java.sql.Date object, "2020-03-09", "20200309", or int 20200309
            insert.setDate(1, new Date(System.currentTimeMillis()));
            // DATETIME
            insert.setDate(2, new Date(System.currentTimeMillis()));
            // TIMESTAMP
            insert.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            // TIME
            insert.setTime(4, new Time(System.currentTimeMillis()));
            // YEAR
            insert.setString(5, "2020");
            return insert.executeUpdate();
        }
    }
}
