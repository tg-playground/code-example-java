package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@RestController
@RefreshScope
@EnableDiscoveryClient
public class EurekaClientApplication {
    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
//        new SpringApplicationBuilder(EurekaClientApplication.class).web(true).run(args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello Eureka Client" + new Date();
    }

    @RequestMapping("/client2")
    public String client2() {
        RestTemplate restTemplate = new RestTemplate();
        String serviceId = "EUREKA-CLIENT-2";
        String serviceUrl = discoveryClient.getInstances(serviceId).get(0).getUri().toString();
        String response = restTemplate.getForObject(serviceUrl + "/", String.class);
        System.out.println("Response from " + serviceId + ": " + response);
        return "Response from Eureka Client 2: " + response;
    }
}
