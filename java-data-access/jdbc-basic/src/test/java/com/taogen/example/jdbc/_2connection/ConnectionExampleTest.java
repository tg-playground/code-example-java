package com.taogen.example.jdbc._2connection;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class ConnectionExampleTest {


    @Test
    public void getConnection() throws IOException, SQLException {
        assertNotNull(ConnectionExample.getConnection());
    }
}