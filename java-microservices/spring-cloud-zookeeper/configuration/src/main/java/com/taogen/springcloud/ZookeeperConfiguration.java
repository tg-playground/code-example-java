package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author taogen
 */
@Component
public class ZookeeperConfiguration implements CommandLineRunner {

    @Value("${greeting}")
    private String greeting;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(greeting);
    }
}
