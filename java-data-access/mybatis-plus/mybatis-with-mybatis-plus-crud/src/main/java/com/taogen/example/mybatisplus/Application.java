package com.taogen.example.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 * @author Taogen
 */
@SpringBootApplication
@MapperScan("com.taogen.example.mybatisplus.crud.mapper")
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class);
    }
}
