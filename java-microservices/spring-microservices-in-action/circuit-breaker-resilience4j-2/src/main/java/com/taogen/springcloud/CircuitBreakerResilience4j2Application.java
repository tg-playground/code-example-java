package com.taogen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class CircuitBreakerResilience4j2Application {


    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerResilience4j2Application.class, args);
    }

    @GetMapping("/")
    public String home() {
        String message = "Welcome circuit-breaker-resilience4j-2 " + new Date();
        System.out.println(message);
        return message;
    }

    @GetMapping("/testBreakerResilience")
    public String testBreakerResilience() {
        int i = 1 / 0;
        return "testBreakerResilience" + new Date();
    }
}
