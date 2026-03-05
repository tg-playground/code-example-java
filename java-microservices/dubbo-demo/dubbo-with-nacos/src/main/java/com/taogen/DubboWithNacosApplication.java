package com.taogen;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author taogen
 */
@SpringBootApplication
@EnableDubbo
public class DubboWithNacosApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboWithNacosApplication.class, args);
    }
}
