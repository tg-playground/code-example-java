# servlet-basic-the-request Project 

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

- servlet-api v3.5
- org.json v20190722

## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet-basic-the-request -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step2: Configuring Maven Project `pom.xml`

Set Maven Project Properties

```xml
<properties>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
</properties>
```

Add Maven Dependencies

```xml
<!-- servlet api -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
    <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
    <scope>provided</scope>
</dependency>

<!-- JSON parser -->
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20190722</version>
</dependency>
```

Add Tomcat Maven Plugin and Tomcat Configuration

```xml
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
                    <port>9000</port>
                    <path>/servlet-basic-the-request</path>
                </configuration>
            </plugin>
        </plugins>
    </build>
    ...
</project>
```

### Step 3: Add project file structures

Add source root `src/main/java`

Add package path `com/taogen/example/servlet/request`



## Implementation

### Step 1: Write Servlets





## Test

### Running and Visiting Project

Running Maven Project by Maven Tomcat 7 Plugin

```shell
$ mvn tomcat7:run
```

Visiting Index Page

```shell
$ curl http://localhost:{your_port}/{your_context}
```

Visiting `HelloWorldServlet`

```shell
$ curl http://localhost:{your_port}/{your_context}/hello
```



## References
