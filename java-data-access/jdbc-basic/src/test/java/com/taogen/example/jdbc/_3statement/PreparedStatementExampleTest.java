package com.taogen.example.jdbc._3statement;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class PreparedStatementExampleTest {

    private PreparedStatementExample preparedStatementExample = new PreparedStatementExample();

    @Before
    public void init() throws SQLException {
        if (!preparedStatementExample.isTableExist()) {
            preparedStatementExample.createTable();
        }
        if (preparedStatementExample.count() <= 0) {
            preparedStatementExample.insert();
        }
    }

    @Test
    public void executeSqlFile() {
        // TODO
    }

    @Test
    public void checkTableExist() throws SQLException {
        assertTrue(preparedStatementExample.isTableExist());
    }

    @Test
    public void createTable() {
        assertTrue(preparedStatementExample.createTable());
    }

    @Test
    public void dropTable() {
        assertTrue(preparedStatementExample.dropTable());
    }

    @Test
    public void count() throws SQLException {
        assertTrue(preparedStatementExample.count() >= 0);
    }

    @Test
    public void insert() throws SQLException {
        assertTrue(preparedStatementExample.insert() > 0);
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(preparedStatementExample.delete() >= 0);
    }

    @Test
    public void update() throws SQLException {
        assertTrue(preparedStatementExample.update() > 0);
    }

    @Test
    public void select() throws SQLException {
        assertNotNull(preparedStatementExample.select());
    }

    @Test
    public void batchInsert() throws SQLException {
        assertEquals(10, preparedStatementExample.batchInsert());
    }
}