# Spring Boot CRUD Best Practice Project Building Document

**Content**

- Environments
  - Software Environment
  - Library Dependencies
- Building Process
- References

## Environments

### Software Environment

Tools

- Git
- JDK 11
- Apache Maven
- Intellj IDEA

Servers

- MySQL
- Apache Tomcat

### Library Dependencies

Test

- junit v4.12
- mockito-core v2.23.4

Logging

- log4j-web v2.8.2

Utility

- commons-lang3 v3.9
- Google Guava
- JSON
  - org.json
  - gson v2.8.5
  - jackson-databind v2.9.10.3
- XML
  - JDK JAXB
- HTTP Client
  - OkHttp

Data Access

- mysql-connector-java
- mybatis
- jedis

Web

- javax.servlet-api v3.0.1
- JSTL
- spring
- spring web

## Building Process

### Step1: Creating a Git repository

### Step2: Generating a Maven project

Using Maven Template to Generate Project Structure and Artifacts

- maven-archetype-quickstart

```
$ mvn archetype:generate -DgroupId=com.taogen.demo.springbootcurd -DartifactId=springboot-crud -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```



### Step3: Add project directory structure



### Step4: Configuring Maven pom.xml

Add Maven project properties, add Maven dependencies, and add Maven plugins

**<<<<<<!!{update me}!!>>>>>>**

```xml
<properties>
    <!-- maven properties -->
    <project.build.sourceEncoding>${project.charset}</project.build.sourceEncoding>
    <project.reporting.outputEncoding>${project.charset}</project.reporting.outputEncoding>
    <maven.compiler.target>${project.java.version}</maven.compiler.target>
    <maven.compiler.source>${project.java.version}</maven.compiler.source>
    <!-- end -->
    
    <!-- project properties -->
    <project.java.version>1.8</project.java.version>
    <project.charset>UTF-8</project.charset>
    <!-- end -->
    
    <!-- dependencies version -->
    <junit.version>4.12</junit.version>
    <log4j.version>2.8.2</log4j.version>
    <!-- end -->
</properties>

<repositories>
    <repository>
        <id>alimaven</id>
        <name>aliyun maven</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    </repository>
    <repository>
        <id>central</id>
        <name>maven central</name>
        <url>https://repo1.maven.org/maven2/</url>
    </repository>
</repositories>

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
        <version>2.23.4</version>
        <scope>test</scope>
    </dependency>
    <!-- end -->

    <!-- Logging -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-web</artifactId>
        <version>${log4j.version}</version>
    </dependency>
    <!-- end -->
    
</dependencies>

<build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <testSourceDirectory>src/test/java</testSourceDirectory>
    <plugins>
        <!-- maven compile -->
        <plugin>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.5.1</version>
            <configuration>
                <source>${project.java.version}</source>
                <target>${project.java.version}</target>
                <encoding>${project.charset}</encoding>
            </configuration>
        </plugin>
    </plugins>
</build>
```



### Step5: Add logging configuration file

`src/main/resources/log4j2.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Properties>
        <Property name="my_pattern">%d [%t] %-5level %logger{36} - %msg%n</Property>
        <Property name="filename">app.log</Property>
    </Properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${my_pattern}"/>
        </Console>
        <File name="File" fileName="${filename}">
            <PatternLayout pattern="${my_pattern}" />
        </File>
    </Appenders>
    <Loggers>
        <Logger name="com.taogen.example" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

## Configurations

### Test Configurations 

#### Junit 4

`pom.xml`

```xml
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
    </exclusions>
</dependency>
```

`XxxTest.java`

```java
@SpringBootTest
@RunWith(SpringRunner.class)
class SpringBootCrudBestPracticeApplicationTests {

	@Test
	void test() {
        assertTrue(true);
	}

}
```



### Logging Configurations

`pom.xml`

```xml
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
```

`application.yml`

```yaml
logging:
  config: classpath:log4j2.xml
```

`log4j2.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Properties>
        <Property name="my_pattern">%d [%t] %-5level %logger{36} - %msg%n</Property>
        <Property name="filename">app.log</Property>
    </Properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${my_pattern}"/>
        </Console>
        <File name="File" fileName="${filename}">
            <PatternLayout pattern="${my_pattern}"/>
        </File>
    </Appenders>
    <Loggers>
        <Logger name="com.taogen.demo.springbootcrud" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Logger>
        <logger name="org.springframework" level="INFO" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </logger>
        <logger name="org.mybatis" level="INFO" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </logger>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```



### Data Access Configurations

#### RDBMS

`pom.xml`

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.3</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

`application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springboot-curd?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    username: root
    password: root
    driverClassName: com.mysql.cj.jdbc.Driver
    # connection pool configurations
	...
mybatis:
  mapper-locations: classpath:mybatis/mapper/*.xml
  type-aliases-package: com.taogen.demo.springbootcrud.module.*.entity
```

#### Cache

TODO



### Web  Configurations

#### Web MVC

`pom.xml`

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.5.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

`application.yml`

```yaml
server:
  servlet:
    context-path: /springbootcrud
  port: 80
```

#### API

Swagger + Open API 3.0

`pom.xml`

```xml
<!-- API doc -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.2.32</version>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.2.32</version>
</dependency>
<!-- API doc end -->
```

`application.yml`

```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
    operationsSorter: method
```



### Data Validation

#### JPA Data Validation

`pom.xml`

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

`{Entity}.java`

```java
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Employee extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String nickname;

    @Min(value = 1, message = "Age can't less than 1")
    @Max(value = 150, message = "Age can't greater than 150")
    private Integer age;
}
```

`{GlobalExcpetionHandler}.java`

```java
@ControllerAdvice
@RestController
public class GlobalControllerExceptionHandler {

    /**
     * 400: BAD_REQUEST
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
```



### Error Handling

### AOP

### Filter

RequestLogFilter

### File Upload

### Web Security (Authentication, Authorization)

### Internationalization

### Prevent Repeat Submit

### Data Privilege and Operation Privilege

## References