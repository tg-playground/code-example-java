package com.taogen.example.jdbc.iii.statement;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class StatementExampleTest {

    private StatementExample statementExample = new StatementExample();

    @Before
    public void init() throws SQLException {
        if (!statementExample.checkTableExist()) {
            statementExample.createTable();
        }
        if (statementExample.count() <= 0) {
            statementExample.insert();
        }
    }

    @Test
    public void createTable() {
        assertTrue(statementExample.createTable());
    }

    @Test
    public void insert() throws SQLException {
        assertTrue(statementExample.insert() > 0);
    }

    @Test
    public void update() throws SQLException {
        assertTrue(statementExample.update() > 0);
    }

    @Test
    public void select() throws SQLException {
        assertNotNull(statementExample.select());
    }

    @Test
    public void delete() throws SQLException {
        assertTrue(statementExample.delete() >= 0);
    }

    @Test
    public void dropTable() {
        assertTrue(statementExample.dropTable());
    }

    @Test
    public void checkTableExist() throws SQLException {
        statementExample.createTable();
        assertTrue(statementExample.checkTableExist());
    }

    @Test
    public void count() throws SQLException {
        assertTrue(statementExample.count() > 0);
    }

    @Test
    public void batchInsert() throws SQLException {
        assertEquals(10, statementExample.batchInsert());
    }
}