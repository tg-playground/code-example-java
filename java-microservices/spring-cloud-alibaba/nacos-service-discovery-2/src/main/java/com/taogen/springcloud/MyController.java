package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class MyController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/greeting")
    public String greeting() {
        return "Hello from " + applicationName + " at " + new Date();
    }

    @GetMapping("/callService1")
    public String callService1() {
        String serviceId = "nacos-service-discovery-1";
        return restTemplate.getForObject("http://" + serviceId + "/greeting", String.class);
    }
}
