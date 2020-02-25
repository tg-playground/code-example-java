# servlet3-basic-03-asynchronous Project 

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
- javax.servlet-api v3.0.1



## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet3-basic-03-asynchronous -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Add project directories structures  

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example.servlet3.async`

```shell
cd servlet3-basic-03-asynchronous
# Linux
mkdir -p src/main/java/com/taogen/example/servlet3/async src/test/java/com/taogen/example/servlet3/async
# Windows
mkdir src\main\java\com\taogen\example\servlet3\async src\test\java\com\taogen\example\servlet3\async
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
    <!-- changeit -->
    <tomcat.plugin.config.port>80</tomcat.plugin.config.port>
    <tomcat.plugin.config.path>/servlet3-async</tomcat.plugin.config.path>
    <servlet.version>3.0.1</servlet.version>
    <servlet.artifactId>javax.servlet-api</servlet.artifactId>
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

    <!-- servlet api -->
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>${servlet.artifactId}</artifactId>
        <version>${servlet.version}</version>
        <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
        <scope>provided</scope>
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

It's not necessary, but if you want you can add it.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	      version="3.0">
```



## Requirements

Using synchronous Servlet. 

- Decoupling HTTP thread and worker thread. Long time operations run on worker thread, back HTTP thread to web container. Async Servlet have higher HTTP throughput than Sync Servlet.
- Different web container implement work thread are different. Tomcat using HTTP thread as worker thread. So HTTP throughput of Async Servlet and Sync Servlet not have difference.

## Implementation

```java
AsyncContext asyncContext = request.startAsync();
asyncContext.start(Runner runner);
```



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

[1] [Asynchronous Processing - Oracle Java EE Documentation](https://docs.oracle.com/javaee/7/tutorial/servlets012.htm)

[2] [Asynchronous Servlets in Java](https://www.hackerearth.com/zh/practice/notes/asynchronous-servlets-in-java/)

[3] [Servlet 3.0 异步处理详解](https://segmentfault.com/a/1190000012318350)