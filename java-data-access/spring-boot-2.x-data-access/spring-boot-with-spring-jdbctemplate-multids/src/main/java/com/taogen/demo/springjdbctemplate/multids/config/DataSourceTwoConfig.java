package com.taogen.demo.springjdbctemplate.multids.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Taogen
 */
@Configuration
@EnableTransactionManagement
public class DataSourceTwoConfig implements DataSourceConfig {
    @Bean("db2DataSourceProperties")
    @ConfigurationProperties("spring.datasource.db2")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean("db2DataSource")
    public DataSource dataSource() {
        return dataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("db2JdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("db2DataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean("db2Tx")
    @Override
    public PlatformTransactionManager platformTransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
