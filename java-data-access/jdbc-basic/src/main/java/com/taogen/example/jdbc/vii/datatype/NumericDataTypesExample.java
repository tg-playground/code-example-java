package com.taogen.example.jdbc.vii.datatype;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Taogen
 */
public class NumericDataTypesExample {
    /**
     * bit - A bit-value type. M indicates the number of bits per value, from 1 to 64. The default is 1 if M is omitted.
     * bool, boolean - These types are synonyms for TINYINT(1). A value of zero is considered false. Nonzero values are considered true.
     * tinyint - 1 byte, A very small integer. The signed range is -128 to 127. The unsigned range is 0 to 255.
     * smallint - 2 bytes, A small integer. The signed range is -32768 to 32767. The unsigned range is 0 to 65535.
     * mediumint - 3 bytes, A medium-sized integer. The signed range is -8388608 to 8388607. The unsigned range is 0 to 16777215.
     * integer (int) - 4 bytes, A normal-size integer. The signed range is -2147483648 to 2147483647. The unsigned range is 0 to 4294967295.
     * bigint - 8 bytes, A large integer. The signed range is -9223372036854775808 to 9223372036854775807. The unsigned range is 0 to 18446744073709551615.
     * decimal(M, D) (dec, fixed, numeric) - A packed “exact” fixed-point number. M is the total number of digits (the precision) and D is the number of digits after the decimal point (the scale). The decimal point and (for negative numbers) the - sign are not counted in M.
     * float - A small (single-precision) floating-point number. Permissible values are -3.402823466E+38 to -1.175494351E-38, 0, and 1.175494351E-38 to 3.402823466E+38. These are the theoretical limits, based on the IEEE standard. The actual range might be slightly smaller depending on your hardware or operating system.
     * double precision (double) - A normal-size (double-precision) floating-point number. Permissible values are -1.7976931348623157E+308 to -2.2250738585072014E-308, 0, and 2.2250738585072014E-308 to 1.7976931348623157E+308.
     * real - default a synonym for DOUBLE. If the REAL_AS_FLOAT SQL mode is enabled, REAL is a synonym for FLOAT rather than DOUBLE.
     *
     * @return
     */
    public int addNumericData() throws SQLException {
        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createTable = connection.prepareStatement("create table if not exists test_numeric(" +
                        "my_bit bit, my_tinyint tinyint, my_bool bool, my_smallint smallint, my_mediumint mediumint, " +
                        "my_int int, my_bigint bigint, my_decimal decimal(6, 2), my_float float, my_double double )");
                PreparedStatement insert = connection.prepareStatement("insert into test_numeric values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
        ) {
            createTable.execute();
            // bit
            insert.setByte(1, (byte) 1);
            // tinyint
            insert.setByte(2, (byte) 3);
            // bool
            insert.setBoolean(3, true);
            // smallint
            insert.setShort(4, (short) 4);
            // mediumint
            insert.setShort(5, (short) 4);
            // int
            insert.setInt(6, 4);
            // bigint
            insert.setLong(7, 4L);
            // decimal
            insert.setDouble(8, 9.123);
            // float
            insert.setFloat(9, 10.123F);
            // double
            insert.setDouble(10, 11.123456);
            return insert.executeUpdate();
        }
    }
}
