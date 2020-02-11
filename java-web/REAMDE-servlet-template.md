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



## Building Project

### Step 1: Generating Maven Project

**<<<<<<!!{update me}!!>>>>>>**

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId={your_project_name} -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Configuring Maven Project `pom.xml`

Set Maven Project Properties

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
</properties>
```

Add Maven Dependencies

**<<<<<<!!{update me}!!>>>>>>**

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.0.1</version>
    <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
    <scope>provided</scope>
</dependency>
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```

Add Maven Plugins  

**<<<<<<!!{update me}!!>>>>>>**

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
                <source>1.8</source>
                <target>1.8</target>
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
            <version>2.2</version>
            <configuration>
                <port>9000</port>
                <path>/servlet-hello</path>
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