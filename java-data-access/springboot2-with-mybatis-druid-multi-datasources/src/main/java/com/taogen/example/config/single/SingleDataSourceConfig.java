package com.taogen.example.config.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Taogen
 */
@Configuration
@Profile("single-datasource")
@MapperScan("com.taogen.example.mapper")
public class SingleDataSourceConfig {
}
