# servlet-test-junit-with-mockito Project

Content

- Environments
- Building Project
- Implementation
- Test
- References

## Environment

Software

- JDK 1.8
- Maven
- Tomcat7 Maven Plugin

Dependencies

- javax.servlet-api 3.0.1
- junit 4.12
- mockito 2.23.4



## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts 

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet-test-junit-with-mockito -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Configuring Maven Project `pom.xml`

Set Maven Project Properties

```xml
<properties>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
</properties>
```

Add Maven Dependencies

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.0.1</version>
    <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
    <scope>provided</scope>
</dependency>

<!-- unit test -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>

<!-- mockito -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>2.23.4</version>
    <scope>test</scope>
</dependency>
```

Add Maven Plugins

``` xml
<project>
  ...
  <build>
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
                <port>9000</port>   //Configure port number
                <path>/servlet-test-junit-with-mockito</path>   //Configure application root URL
            </configuration>
        </plugin>
    </plugins>
  </build>
  ...
</project>
```

### Step 3: Add project file structures  

Add source root `src/main/java`

Add package path `com/taogen/example/servlet/test`



## Implementation

### Step 1: Write Servlets

Add `JunitWithMockitoServlet.java`  

```java
package com.taogen.example.servlet.test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/junitWithMockito")
public class JunitWithMockitoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String hobby = req.getParameter("hobby");
        StringBuilder result = new StringBuilder(name).append("-").append(hobby);
        out.println(result.toString());
    }
}
```

### Step2: Write Tests

Add `JunitWithMockitoServletTest.java`

```java
package com.taogen.example.servlet.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class JunitWithMockitoServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGet() throws IOException, ServletException {
        Map<String, String> params = new HashMap<>();
        params.put("name", "Taogen");
        params.put("hobby", "Computer");
        buildRequestParams(params);
        String result = getResponse();
        assertEquals("Taogen-Computer", result);
    }

    private void buildRequestParams(Map<String, String> params) {
        for (String key : params.keySet()) {
            when(request.getParameter(key)).thenReturn(params.get(key));
        }
    }

    private String getResponse() throws IOException, ServletException {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        when(response.getWriter()).thenReturn(pw);
        JunitWithMockitoServlet myServlet = new JunitWithMockitoServlet();
        myServlet.doGet(request, response);
        return sw.getBuffer().toString().trim();
    }
}
```



## Test

### Running Tests

```shell
$ mvn test
```

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

```shell
$ curl http://localhost:{your_port}/{your_context}/junitWithMockito?name=Taogen&hobby=Computer
```



## References

[1] [JUnit HttpServletRequest Example - java code geeks](https://examples.javacodegeeks.com/core-java/junit/junit-httpservletrequest-example/)

