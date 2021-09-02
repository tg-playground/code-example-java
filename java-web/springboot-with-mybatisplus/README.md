# Spring Boot with MyBatis Plus

## Build Project

1. Initialize project

```shell
mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=springboot-with-mybatisplus -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

Add directories

```
src/main/resources
```

2. Add Maven dependencies

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
        <groupId>com.baomidou</groupId>
        <artifactId>mybatis-plus-boot-starter</artifactId>
        <version>3.4.3</version>
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

3. Create Spring Boot configuration file `application.yml`

- Configuring data sources

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
    context-path: "/springboot-with-mybatisplus"
  port: 8082
```

4. Add module code

Write controller, service, dao, entity

```java
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
}
```

```java
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
}
```

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserDao extends BaseMapper<User> {
}
```

```java
@Data
@TableName("user")
public class User {
    @TableId(value = "user_id")
    private Integer userId;
    ...
}
```

5. Write Spring Boot Runner

```java
@SpringBootApplication
@MapperScan(value = "com.taogen.example.dao")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class)
    }
}
```

5. Running project

Visiting http://localhost:8082/springboot-with-mybatisplus/user/listUsers