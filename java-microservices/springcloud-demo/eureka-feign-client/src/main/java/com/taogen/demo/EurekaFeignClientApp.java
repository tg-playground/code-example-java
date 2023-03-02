package com.taogen.demo;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taogen
 */
@SpringBootApplication
@EnableFeignClients
@RestController
public class EurekaFeignClientApp {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;
    @Autowired
    private GreetingClient greetingClient;
    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping("/greeting")
    public String greeting() {
        return String.format(
                "Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

    @RequestMapping("/get-greeting")
    public String greeting(Model model) {
        return new StringBuilder()
                .append(greetingClient.greeting())
                .append("<br>")
                .append(greetingClient.getGreetingNoFeign())
                .toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaFeignClientApp.class, args);
//        new SpringApplicationBuilder(EurekaClientApp.class).web(true).run(args);
    }
}
