package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@RestController
@RefreshScope
public class EurekaWithSpringCloudLoadBalancerApplication {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EurekaWithSpringCloudLoadBalancerApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        String message = "Hello Eureka Client - Spring Cloud LoadBalancer " + new Date();
        System.out.println(message);
        return message;
    }

    @GetMapping("/client1")
    public String client1() {
        String serviceUrl = "http://eureka-client/";
        return restTemplate.getForObject(serviceUrl, String.class);
    }
}
