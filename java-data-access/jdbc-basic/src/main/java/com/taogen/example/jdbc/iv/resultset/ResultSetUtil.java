package com.taogen.example.jdbc.iv.resultset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Taogen
 */
public class ResultSetUtil {

    private ResultSetUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static int getResultSetSize(ResultSet resultSet) throws SQLException {
        int count = 0;
        if (resultSet != null) {
            resultSet.last();
            count = resultSet.getRow();
            resultSet.beforeFirst();
        }
        return count;
    }

    public static long getCountFiledFromResultSet(ResultSet resultSet) throws SQLException {
        long count = -1;
        while (resultSet.next()) {
            count = resultSet.getInt("count");
        }
        return count;
    }

    public static String readClob(Clob clob) throws SQLException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(clob.getCharacterStream());
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    public static String readBlob(Blob blob) throws SQLException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream binaryStream = blob.getBinaryStream();
        byte[] bytes = new byte[1024];
        while (binaryStream.read(bytes) != -1) {
            stringBuilder.append(new String(bytes));
        }
        return stringBuilder.toString();
    }
}
