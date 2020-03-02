package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._4resultset.ResultSetExample;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PreparedStatementExampleTest {

    @Before
    public void init() throws SQLException {
        if (PreparedStatementExample.checkTableExist() <= 0){
            PreparedStatementExample.createTable();
        }
        if (ResultSetExample.getResultSetCount(PreparedStatementExample.select()) <= 0){
            PreparedStatementExample.insert();
        }
    }

    @Test
    public void executeSqlFile() {
    }

    @Test
    public void checkTableExist() {
        PreparedStatementExample.checkTableExist();
    }

    @Test
    public void createTable() {
        PreparedStatementExample.createTable();
    }

    @Test
    public void dropTable() {
        PreparedStatementExample.dropTable();
    }

    @Test
    public void insert() {
        assertTrue(PreparedStatementExample.insert() > 0);
    }

    @Test
    public void delete() {
        assertTrue(PreparedStatementExample.delete() >= 0);
    }

    @Test
    public void update() {
        assertTrue(PreparedStatementExample.update() > 0);
    }

    @Test
    public void select() throws SQLException {
        assertNotNull(PreparedStatementExample.select());
    }
}