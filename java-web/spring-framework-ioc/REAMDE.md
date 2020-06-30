# spring-framework-ioc Project 

Content

- Environments
- Building Project
- Requirements
- Implementation
- Test
- References

## Environment

Software

- JDK 1.8
- Maven
- Tomcat7 Maven Plugin

Dependencies

- Test
  - junit v4.12
  - mockito-core v2.23.4
- Logging
  - log4j-web v2.8.2
- Tools
  - lombok v1.18.10
  - gson v2.8.5
  - commons-lang3 v3.9
- Spring framework
  - spring-core
  - spring-beans
  - spring-context

## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=spring-framework-ioc -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Add project directories structures  

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example`

```shell
cd spring-framework-ioc
# Linux
mkdir -p src/main/java/com/taogen/example/springframework/ioc src/test/java/com/taogen/example/springframework/ioc
# Windows
mkdir src\main\java\com\taogen\example\springframework\ioc src\test\java\com\taogen\example\springframework\ioc
```

### Step 3: Configuring Maven Project `pom.xml`

Set Maven project properties, add Maven dependencies, and add Maven plugins

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
    <!-- custom properties -->
    <project.java.version>1.8</project.java.version>
    <tomcat.plugin.version>2.2</tomcat.plugin.version>
    <junit.version>4.12</junit.version>
    <log4j.version>2.8.2</log4j.version>
    <spring.version>5.2.6.RELEASE</spring.version>
    <!-- changeit -->
    <tomcat.plugin.config.port>80</tomcat.plugin.config.port>
    <tomcat.plugin.config.path>/servlet-hello</tomcat.plugin.config.path>
    <servlet.version>2.5</servlet.version>
    <servlet.artifactId>servlet-api</servlet.artifactId>
</properties>

<dependencies>
    <!-- ** Unit Test ** -->
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

    <!-- ** Logging ** -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-web</artifactId>
        <version>${log4j.version}</version>
    </dependency>

    <!-- ** Common Tools ** -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.10</version>
        <scope>provided</scope>
    </dependency>
    <!-- small json file (KB) -->
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.8.5</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.9</version>
    </dependency>
    
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-bean</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>
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
            </configuration>
        </plugin>

        <!-- maven package war -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>3.2.3</version>
            <configuration>
                <failOnMissingWebXml>false</failOnMissingWebXml>
                <webResources>
                    <resource>
                        <!-- this is relative to the pom.xml directory -->
                        <directory>src/main/resources</directory>
                    </resource>
                </webResources>
            </configuration>
        </plugin>

        <!-- Tomcat plugin -->
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>${tomcat.plugin.version}</version>
            <configuration>
                <port>${tomcat.plugin.config.port}</port>
                <path>${tomcat.plugin.config.path}</path>
                <contextReloadable>true</contextReloadable>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Step 4: Add Log4j2.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Logger name="com.taogen.example" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```

### Step 5: Update Servlet web.xml dtd

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	      http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	      version="2.5">
```



## Requirements

Functions

Business Processes

## Implementation

### Step 1: Write Servlets

### Step 2: Configure Servlets

### Step 3: Write Unit Test





## Test

### Running and Visiting Project

Running Maven Project by Maven Tomcat 7 Plugin

```shell
$ mvn tomcat7:run
# or
$ mvn tomcat7:run-war
```

Visiting Index Page

```shell
$ curl http://localhost:{your_port}/{your_context}
```

Visiting `HelloWorldServlet`  

**<<<<<<!!{update me}!!>>>>>>**

```shell
$ curl http://localhost:{your_port}/{your_context}/hello
```



## References

**<<<<<<!!{update me}!!>>>>>>**

Books, Docs, URLs