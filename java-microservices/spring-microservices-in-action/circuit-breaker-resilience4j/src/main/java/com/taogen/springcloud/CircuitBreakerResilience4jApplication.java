package com.taogen.springcloud;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class CircuitBreakerResilience4jApplication {
    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerResilience4jApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        String message = "Welcome circuit-breaker-resilience4j-1 " + new Date();
        System.out.println(message);
        return message;
    }

    @GetMapping("/testBreakerResilience")
    public String testBreakerResilience() {
        RestTemplate restTemplate = new RestTemplate();
        String serviceId = "circuit-breaker-resilience4j-2";
        String serviceUrl = discoveryClient.getInstances(serviceId).get(0).getUri().toString();
        Supplier<String> supplier = () -> restTemplate.getForObject(serviceUrl + "/testBreakerResilience", String.class);
        Function<Throwable, String> fallbackFunction = throwable -> {
            if (throwable instanceof CallNotPermittedException) {
                return "Fallback: Circuit breaker is open, request cannot be processed.";
            } else {
                return "This is a fallback message. Causeed by " + throwable.getClass().getSimpleName() + ": " + throwable.getMessage();
            }
        };
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuit-breaker-resilience4j-2");
        String response = circuitBreaker.run(supplier, fallbackFunction);
        System.out.println(response);
        return response;
    }
}
