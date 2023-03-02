package com.taogen.demo.springjdbctemplate.multids.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author Taogen
 */
public interface DataSourceConfiguration {
    DataSourceProperties dataSourceProperties();

    DataSource dataSource();

    JdbcTemplate jdbcTemplate(DataSource ccbsDataSource);

    PlatformTransactionManager platformTransactionManager(DataSource dataSource);

}
