# `mybatis-with-mybatis-plus` Project Building Document

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

- MySQL (or Oracle)

### Library Dependencies

Test

- junit v4.12

Logging

- log4j-web v2.8.2

Utility

- lombok v1.18.10
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



## Building Process

### Step1: Creating a Git repository

- Git Repository initialization.

  ```
  git init
  ```

- Add .gitignore, .gitmessage, README

- Create a repository on GitHub

- Push new local repository to remote

  ```
  git remote add origin https://github.com/<username>/<repository_name>.git
  git push -u origin master
  ```

### Step2: Generating a Maven project

Using Maven Template to Generate Project Structure and Artifacts

- maven-archetype-quickstart

**<<<<<<!!{update me}!!>>>>>>**

```
$ mvn archetype:generate -DgroupId=com.taogen.example.mybatisplus -DartifactId=mybatis-with-mybatis-plus -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```



### Step3: Add project directory structure

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example`

**<<<<<<!!{update me}!!>>>>>>**

```
cd {your_project_root_dir}
# Linux
mkdir -p src/main/java/com/taogen/example src/test/java/com/taogen/example
# Windows
mkdir src\main\java\com\taogen\example src\test\java\com\taogen\example
```



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