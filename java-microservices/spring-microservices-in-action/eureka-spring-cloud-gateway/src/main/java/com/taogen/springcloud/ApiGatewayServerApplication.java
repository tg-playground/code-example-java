package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ApiGatewayServerApplication {
    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayServerApplication.class, args);
    }

    @GetMapping("/")
    public String index() {
        return "Hello World" + new Date();
    }

    @GetMapping("/client1")
    public String requestEurekaClient1ByGateway() {
        RestTemplate restTemplate = new RestTemplate();
        String serviceId = "gateway-server";
        String serviceUrl = discoveryClient.getInstances(serviceId).get(0).getUri().toString();
        String response = restTemplate.getForObject(serviceUrl + "/eureka-client-1", String.class);
        System.out.println("Response from " + serviceId + ": " + response);
        return "Response from Eureka Client 1: " + response;
    }
}
