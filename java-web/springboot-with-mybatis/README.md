# Spring Boot with MyBatis

## Build Project

1. Initialize Maven project

```shell
mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=springboot-with-mybatis -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

Add directories

```angular2html
src/main/resources
```

2. Add dependencies

`pom.xml`

```xml
<dependencies>
    <!-- Test -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>${mockito-core.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
        <exclusions>
            <exclusion>
                <groupId>org.junit.vintage</groupId>
                <artifactId>junit-vintage-engine</artifactId>
            </exclusion>
            <exclusion>
                <groupId>org.json</groupId>
                <artifactId>json</artifactId>
            </exclusion>
        </exclusions>
    </dependency>

    <!-- Logging -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
        <exclusions>
            <exclusion>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>

    <!-- web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- data access -->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.2.0</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- utility -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <!-- https://mvnrepository.com/artifact/com.google/gson -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.2</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.json/json -->
    <dependency>
        <groupId>org.json</groupId>
        <artifactId>json</artifactId>
        <version>20200518</version>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.18</version>
    </dependency>
</dependencies>
```

3. Add Spring Boot configuration file `application.yml`

```yaml
# data access
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/my_test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    username: root
    password: root
    driverClassName: com.mysql.cj.jdbc.Driver
server:
  servlet:
    context-path: "/springboot-with-mybatis"
  port: 8081
mybatis:
  mapper-locations: classpath:mybatis/mapper/*.xml
  type-aliases-package: com.taogen.demo.springbootcrud.module.*.entity
```

4. Add module code

controller, service, dao, entity, mapper xml

5. Write Spring Boot run class

```java
@SpringBootApplication
@MapperScan(value = "com.taogen.example.dao")
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class);
    }
}
```

6. Running and visit project

visit `http://localhost:8081/springboot-with-mybatis/user/listUsers`

