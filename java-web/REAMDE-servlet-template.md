# servlet-xxx Project {{update me}}

Content

- Environments
- Building Project
- Implementation
- Test
- References

## Environment

**<<<<<<!!{update me}!!>>>>>>**

Software

- JDK 1.8
- Maven
- Tomcat7 Maven Plugin

Dependencies

- javax.servlet-api 3.0.1
- junit 4.12
- log4j-web 2.8.2



## Building Project

### Step 1: Generating Maven Project

**<<<<<<!!{update me}!!>>>>>>**

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId={your_project_name} -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Configuring Maven Project `pom.xml`

Set Maven Project Properties

**<<<<<<!!{update me}!!>>>>>>**

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
    <tomcat.plugin.config.path>/servlet-hello</tomcat.plugin.config.path>
    <servlet.version>2.5</servlet.version>
    <servlet.artifactId>servlet-api</servlet.artifactId>
</properties>
```

Add Maven Dependencies

**<<<<<<!!{update me}!!>>>>>>**

```xml
<!-- unit test -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>${junit.version}</version>
    <scope>test</scope>
</dependency>

<!-- logging -->
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
```

Add Maven Plugins  

``` xml
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
            </configuration>
        </plugin>
    </plugins>
</build>
```

### Step 3: Add project file structures  

### **<<<<<<!!{update me}!!>>>>>>**

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example`



## Implementation

### Step 1: Write Servlets

**<<<<<<!!{update me}!!>>>>>>**

Add `HelloWorldServlet.java`

```java
package com.taogen.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private String message;

    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World! ";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        String result = new StringBuilder(message).append(System.currentTimeMillis()).toString();
        out.println("<h3>" + result + "</h3>");
    }

    public void destroy() {
        // do nothing.
    }
}

```

### Step2: Configuring Servlet 

**<<<<<<!!{update me}!!>>>>>>**



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