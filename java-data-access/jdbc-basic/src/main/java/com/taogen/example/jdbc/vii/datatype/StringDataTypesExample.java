package com.taogen.example.jdbc.vii.datatype;

import com.taogen.example.jdbc.ii.datasource.ConnectionUtil;
import com.taogen.example.jdbc.iv.resultset.ResultSetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.*;

/**
 * @author Taogen
 */
public class StringDataTypesExample {

    private static final Logger logger = LogManager.getLogger();

    public static String readInputStream(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            while (inputStream.read(buffer) != -1) {
                stringBuilder.append(new String(buffer));
            }
            return stringBuilder.toString();
        }
        return null;
    }

    /**
     * # character string
     * CHAR - A fixed-length string that is always right-padded with spaces to the specified length when stored. M represents the column length in characters. The range of M is 0 to 255(2^8-1). If M is omitted, the length is 1.
     * VARCHAR - A variable-length string. M represents the maximum column length in characters. The range of M is 0 to 65,535(2^16-1).
     * TINYTEXT - A TEXT column with a maximum length of 255 (28 − 1) characters.
     * TEXT - A TEXT column with a maximum length of 65,535 (2^16 − 1) characters. The effective maximum length is less if the value contains multibyte characters. Each TINYTEXT value is stored using a 1-byte length prefix that indicates the number of bytes in the value.
     * MEDIUMTEXT - A TEXT column with a maximum length of 16,777,215 (2^24 − 1) characters.
     * LONGTEXT - A TEXT column with a maximum length of 4,294,967,295 or 4GB (2^32 − 1) characters.
     * <p>
     * # binary string
     * BINARY - Max bytes 255, The BINARY type is similar to the CHAR type, but stores binary byte strings rather than nonbinary character strings. An optional length M represents the column length in bytes. If omitted, M defaults to 1.
     * VARBINARY - The VARBINARY type is similar to the VARCHAR type, but stores binary byte strings rather than nonbinary character strings. M represents the maximum column length in bytes.
     * TINYBLOB
     * BLOB(M) - A BLOB column with a maximum length of 65,535 (2^16 − 1) bytes. Each BLOB value is stored using a 2-byte length prefix that indicates the number of bytes in the value. An optional length M can be given for this type.
     * MEDIUMBLOB
     * LONGBLOB
     * <p>
     * # others
     * ENUM - An enumeration. A string object that can have only one value, chosen from the list of values 'value1', 'value2', ..., NULL or the special '' error value. ENUM values are represented internally as integers.
     * SET - A set. A string object that can have zero or more values, each of which must be chosen from the list of values 'value1', 'value2', ... SET values are represented internally as integers.
     * # character set and collation
     * c1 VARCHAR(20) character set utf8mb4 collate utf8mb4_bin
     * create table (...) CHARACTER SET utf8mb4;
     */
    public int addStringData() throws SQLException, IOException {

        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement createTable = connection.prepareStatement("create table if not exists test_string(" +
                        "my_char char(64), my_varchar varchar(64), my_text text, my_binary binary(255), my_blob blob, " +
                        "my_enum enum('value1','value2'), my_set set('value1', 'value2'))");
                PreparedStatement insert = connection.prepareStatement("insert into test_string values (?, ?, ?, ?, ?, ?, ?)")
        ) {
            createTable.execute();
            // char
            insert.setString(1, "Hello");
            // varchar
            insert.setString(2, "World");
            // text
            Clob clob = connection.createClob();
            Writer writer = clob.setCharacterStream(1);
            writer.write("Hello World");
            writer.close();
            insert.setClob(3, clob);
            // binary
            insert.setInt(4, 1);
            // blob
            Blob blob = connection.createBlob();
            OutputStream outputStream = blob.setBinaryStream(1);
            outputStream.write("Hello World".getBytes());
            outputStream.close();
            insert.setBlob(5, blob);
            // enum
            insert.setString(6, "value1");
            // set, note between set values can't have space.
            insert.setString(7, "value1,value2");
            return insert.executeUpdate();
        }
    }

    public boolean getStringData() throws IOException, SQLException {
        addStringData();
        try (
                Connection connection = ConnectionUtil.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from test_string");
        ) {
            int i = 1;
            while (resultSet.next()) {
                logger.debug("------row {}--------", i++);
                logger.debug("my_char: {}", resultSet.getString("my_char"));
                logger.debug("my_varchar: {}", resultSet.getString("my_varchar"));
                logger.debug("my_text: {}", ResultSetUtil.readClob(resultSet.getClob("my_text")));
                logger.debug("my_binary: {}", readInputStream(resultSet.getBinaryStream("my_binary")));
                logger.debug("my_blob: {}", ResultSetUtil.readBlob(resultSet.getBlob("my_blob")));
                logger.debug("my_enum: {}", resultSet.getString("my_enum"));
                logger.debug("my_set: {}", resultSet.getString("my_set"));
            }
            return true;
        }
    }

}
