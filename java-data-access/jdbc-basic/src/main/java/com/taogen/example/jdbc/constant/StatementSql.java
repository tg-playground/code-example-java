package com.taogen.example.jdbc.constant;

public class StatementSql {
    public static final String TEST_TABLE = "test";
    public static final String CREATE_TABLE_SQL = new StringBuilder()
            .append("create table if not exists ")
            .append(TEST_TABLE)
            .append(" (id int not null primary key auto_increment, name varchar(64) not null)")
            .toString();
    public static final String COUNT_TABLE_SQL = new StringBuilder()
            .append("select count(*) as count from ")
            .append(TEST_TABLE)
            .toString();
    public static final String INSERT_SQL = "insert into test (name) value ('hello')";
    public static final String UPDATE_SQL = "update test set name ='helloworld' where name ='hello'";
    public static final String SELECT_SQL = "select * from test";
    public static final String DELETE_SQL = "delete from test";
    public static final String DROP_TABLE_SQL = "drop table if exists test";
    public static final String CHECK_TABLE_EXIST_SQL = "select count(*) as count from information_schema.tables where table_schema = 'test' and table_name = 'test'";

    private StatementSql() {
        throw new IllegalStateException("Constant class");
    }
}
