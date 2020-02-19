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

- test
  - junit v4.12
  - mockito-core v2.23.4
- logging
  - log4j-web v2.8.2
- tools
  - lombok v1.18.10
  - gson v2.8.5
  - jackson v2.9.10.2
  - commons-lang3 v3.9
  - commons-io v2.6
- javax.servlet-api v3.0.1



## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

**<<<<<<!!{update me}!!>>>>>>**

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId={your_project_name} -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Add project directories structures  

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example`

**<<<<<<!!{update me}!!>>>>>>**

```shell
cd {your_project_root_dir}
# Linux
mkdir -p src/main/java/com/taogen/example src/test/java/com/taogen/example
# Windows
mkdir src\main\java\com\taogen\example src\test\java\com\taogen\example
```

### Step 3: Configuring Maven Project `pom.xml`

Set Maven project properties, add Maven dependencies, and add Maven plugins

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
    <!-- large json file (MB) -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.9.10.2</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>2.9.10.2</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.10.2</version>
    </dependency>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.9</version>
    </dependency>
    <dependency>
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.6</version>
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



## Implementation

### Step 1: Write Servlets

**<<<<<<!!{update me}!!>>>>>>**

Add `HelloWorldServlet.java`

```java
package com.taogen.example;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Data
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger();
    private String message;
	
    @Override
    public void init() throws ServletException {
        // Do required initialization
        message = "Hello World!";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("HelloServlet doGet() called");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>HelloServlet</title></head><body><h3>");
        out.println(message);
        out.println("</h3></body></html>");
    }
    
	@Override
    public void destroy() {
        // do nothing.
    }
}
```

Add `HelloServletTest.java`

```java
package com.taogen.example.servlet.servletcontext;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class HelloServletTest extends MyServletTest {

    private static final HelloServlet helloServlet = new HelloServlet();

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        this.stringWriter = new StringWriter();
        this.printWriter = new PrintWriter(stringWriter);
        buildResponse(response, this.printWriter);
    }

    @After
    public void closeResources() throws IOException {
        this.stringWriter.flush();
        this.printWriter.flush();
    }

    @BeforeClass
    public static void init() {
        helloServlet.setMessage("Hello World!");
    }

    @Test
    public void doGet() throws IOException, ServletException {
        Map<String, String> params = new HashMap<>();
        buildRequestParams(request, params);
        helloServlet.doGet(request, response);
        String result = stringWriter.getBuffer().toString().trim();
        assertTrue(result.contains("Hello World"));
    }
}

```

Add `MyServletTest.java`

```java
package com.taogen.example.servlet.servletcontext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mockito.Mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.mockito.Mockito.when;

public class MyServletTest {

    protected static final Logger logger = LogManager.getLogger();

    @Mock
    protected HttpServletRequest request;

    @Mock
    protected HttpServletResponse response;

    protected StringWriter stringWriter;

    protected PrintWriter printWriter;

    public static void buildRequestParams(HttpServletRequest request, Map<String, String> params) {
        for (String key : params.keySet()) {
            when(request.getParameter(key)).thenReturn(params.get(key));
        }
    }

    public static void buildResponse(HttpServletResponse response, PrintWriter printWriter) throws IOException {
        when(response.getWriter()).thenReturn(printWriter);
    }

}
```



### Step2: Configuring Servlet 

**<<<<<<!!{update me}!!>>>>>>**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	      http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	      version="2.5">
    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>com.taogen.example.servlet.servletcontext.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
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

**<<<<<<!!{update me}!!>>>>>>**