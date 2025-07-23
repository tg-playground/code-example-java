# Eureka with Spring Cloud LoadBalancer

1\. Add Dependencies

```kotlin
// Eureka client basic dependencies
//...
// spring cloud loadbalancer
implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
```

2\. Config Load Balancer RestTemplate

```java

@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

3\. Using restTemplate to request a Eureka client API

Eureka instance ID as hostname

```java

@Autowired
private RestTemplate restTemplate;

@GetMapping("/client1")
public String client1() {
    String serviceUrl = "http://eureka-client-1/hello";
    return restTemplate.getForObject(serviceUrl, String.class);
}
```
