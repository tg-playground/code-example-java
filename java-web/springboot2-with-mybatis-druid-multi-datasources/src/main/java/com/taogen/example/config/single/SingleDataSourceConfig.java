package com.taogen.example.config.single;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Configuration
@Profile("singledatasource")
@MapperScan("com.taogen.example.mapper")
public class SingleDataSourceConfig {
}
