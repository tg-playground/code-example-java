package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
@RestController
public class EurekaWithOpenFeignApplication {
    @Autowired
    private MyFeignClient myFeignClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaWithOpenFeignApplication.class, args);
    }

    @RequestMapping("/")
    public String home() {
        String message = "Hello Eureka Client - OpenFeign " + new Date();
        System.out.println(message);
        return message;
    }

    @GetMapping("/client1")
    public String requestEurekaClient1WithOpenFeign() {
        return "Client 1 response: " + myFeignClient.client1();
    }
}
