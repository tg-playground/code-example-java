package com.taogen.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Taogen
 */
@Configuration
@MapperScan("com.taogen.example.mapper")
public class MyBatisPlusConfig {

}
