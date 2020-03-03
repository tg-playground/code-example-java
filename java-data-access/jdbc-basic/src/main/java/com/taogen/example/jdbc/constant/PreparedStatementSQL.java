package com.taogen.example.jdbc.constant;

public class PreparedStatementSQL {
    public static final String CREATE_TABLE_SQL = "create table if not exists test (id int not null primary key auto_increment, name varchar(64) not null)";
    public static final String INSERT_SQL = "insert into test (name) value (?)";
    public static final String UPDATE_SQL = "update test set name =? where name =?";
    public static final String SELECT_SQL = "select * from test";
    public static final String DELETE_SQL = "delete from test where name=? or name=?";
    public static final String DROP_TABLE_SQL = "drop table if exists test";
    public static final String CHECK_TABLE_EXIST_SQL = "select count(*) as count from information_schema.tables where table_schema = 'test' and table_name = 'test'";

    private PreparedStatementSQL(){
        throw new IllegalStateException("Constant class");
    }
}
