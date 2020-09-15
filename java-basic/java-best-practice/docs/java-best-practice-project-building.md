# java-best-practice Project Building Document

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
- JDK 1.8
- Apache Maven
- Intellj IDEA

Servers

### Library Dependencies

Test

- junit v4.12

Logging

- log4j-web v2.8.2

Utility

Data Access

Web

## Building Process

### Step1: Creating a Git repository

### Step2: Generating a Maven project

Using Maven Template to Generate Project Structure and Artifacts

- maven-archetype-quickstart

```
$ mvn archetype:generate -DgroupId=com.taogen.example.bestpractice -DartifactId=java-best-practice -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```



### Step3: Add project directory structure



### Step4: Configuring Maven pom.xml

Add Maven project properties, add Maven dependencies, and add Maven plugins

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

<dependencies>
    <!-- Test -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>${junit.version}</version>
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



## References