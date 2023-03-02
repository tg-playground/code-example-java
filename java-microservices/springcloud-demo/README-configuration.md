# Configuration Server

1. Dependencies

- spring-cloud-config-server
- spring-boot-starter-web
- spring-boot-starter-security (optional)

2. configuration file for server

src/main/resources/application.properties

```
server.port=7777
spring.profiles.active=native
spring.cloud.config.server.native.searchLocations=file:///D:/app-config
```

3. Create configuration files

```java
test-dev.properties
test-prod.properties
test2-dev.properties
test2-prod.properties
```

test-dev.properties:
`test.greeting=Hi developer!`
        
test2-dev.properties:
`test.msg=How is your coding going?`

test-prod.properties
`test.greeting=Hi there!`
        
test2-prod.properties
`test.msg=How are you doing?`

4. Main Class

```java
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
  public static void main(String[] args) {
      SpringApplication.run(ConfigServerApplication.class, args);
  }
}
```

Now we can access the backend via http://localhost:7777/{application}/{profile} or http://localhost:7777/{application}/{profile}/default

We can also access properties using following URLs:

```
/{application}-{profile}.yml
/{application}-{profile}.properties
```
