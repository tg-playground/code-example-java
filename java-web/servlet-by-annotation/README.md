# Servlet by Annotation Project

Content

- Requirements
- Steps
- References

## Requirements

Required

- JDK 1.8
- Maven

## Steps

Step 1: Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet-by-annotation -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

Step2: Add Maven Dependencies

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
    <scope>provided</scope>
</dependency>
```

Step 3: Add Tomcat Maven Plugin and Tomcat Configuration

``` xml
<project>
  ...
  <build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
        </resource>
    </resources>
    <plugins>
        <plugin>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.5.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
 
        <!-- Tomcat plugin-->
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <port>9000</port>   //Configure port number
                <path>/servlet-by-annotation</path>   //Configure application root URL
            </configuration>
        </plugin>
    </plugins>
  </build>
  ...
</project>
```

Step 4: Write HelloWorld Servlet with Annotation

Add source root `src/main/java`

Add package path `com/taogen/example`

Add `HelloWorldServlet.java` 

```java
package com.taogen.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    private static final long serialVersionUID = -3462096228274971485L;
    
	@Override
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().println("Hello World!");
    }
}

```

Step 5: Run Servlet Maven Project

```shell
$ mvn tomcat7:run
```

Visit Index Page by `http://localhost:{your_port}/{your_context}` 

Visit HelloWorld Servlet by `http://localhost:{your_port}/{your_context}/hello`

## References

[1] [Servlet Annotation Example](https://javatutorial.net/servlet-annotation-example)