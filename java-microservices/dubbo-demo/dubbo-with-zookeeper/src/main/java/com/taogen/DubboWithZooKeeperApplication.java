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
public class DubboWithZooKeeperApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboWithZooKeeperApplication.class, args);
    }
}
