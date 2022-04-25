package com.taogen.demo.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(value = "com.taogen.demo.mybatisplus.dao")
public class SpringBootWithMyBatisPlusApp
{
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWithMyBatisPlusApp.class, args);
    }
}
