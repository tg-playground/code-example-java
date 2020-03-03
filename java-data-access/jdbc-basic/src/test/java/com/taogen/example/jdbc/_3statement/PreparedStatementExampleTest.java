package com.taogen.example.jdbc._3statement;

import com.taogen.example.jdbc._4resultset.ResultSetUtil;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PreparedStatementExampleTest {

    @Before
    public void init() throws SQLException {
        if (PreparedStatementExample.checkTableExist() <= 0) {
            PreparedStatementExample.createTable();
        }
        if (PreparedStatementExample.count() <= 0) {
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
    public void count() throws SQLException {
        assertTrue(PreparedStatementExample.count() >= 0);
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

    @Test
    public void batchInsert() throws SQLException {
        assertTrue(PreparedStatementExample.batchInsert() == 10);
    }
}