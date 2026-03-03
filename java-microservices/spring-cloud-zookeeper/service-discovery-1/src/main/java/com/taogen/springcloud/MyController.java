package com.taogen.springcloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 *
 * @author taogen
 */
@Controller
@RestController
@RequestMapping("/")
public class MyController {
    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello from " + applicationName + "!" + new Date();
    }

    @RequestMapping("/callService2")
    public String callService2() {
        RestTemplate restTemplate = new RestTemplate();
        String serviceUrl = null;
        String serviceId = "spring-cloud-zookeeper-service-2";
        List<ServiceInstance> list = discoveryClient.getInstances(serviceId);
        if (list != null && list.size() > 0) {
            serviceUrl = list.get(0).getUri().toString();
        }
        if (serviceUrl == null) {
            return "No service found";
        }
        serviceUrl += "/greeting";
        System.out.println("Requesting URL: " + serviceUrl);
        return restTemplate.getForObject(serviceUrl, String.class);
    }
}
