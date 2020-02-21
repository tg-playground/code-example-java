# servlet2.5-basic-11-security Project

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
- Apache Tomcat 7+

Dependencies

- Test
  - junit v4.12
  - mockito-core v2.23.4
- Logging
  - log4j-web v2.8.2
- servlet-api v2.5



## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet2.5-basic-11-security -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Add project directories structures  

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example.servlet.security`

```shell
cd {your_project_root_dir}
# Linux
mkdir -p src/main/java/com/taogen/example/servlet/security src/test/java/com/taogen/example/servlet/security
# Windows
mkdir src\main\java\com\taogen\example\servlet\security src\test\java\com\taogen\example\servlet\security
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

- Implement servlet security (Authentication and Authorization)

## Implementation

### Implementation Process

Configuring Deployment Descriptor 

- config `<security-role>`. Defining roles.
- config `<login-config>`. Defining how to authenticate user. e.g. login by username and password in login page.
- config `<security-constraint>`. Defining constrained resources URI, HTTP methods, constraint role, data constraint type. 

Configuring Tomcat `conf/tomcat-users.xml`

- config `<role>` and `<user>`

If `login-config` `auth-method` is FORM. You need add form login page and error page.

### Logic Process

If request resource is constrained, then you will redirect the login page or others authenticate mechanism.

If authenticate by form, enter your username and password on the login page. If your input information is right, then you will return your last request resources URL.

During the authenticated session you not need to authenticate username and password again.



## Test

### Running and Visiting Project

Package Project

```shell
$ mvn package
```

Move packaged `war` file to Apache Tomcat `/webapps` directory

Running Apache Tomcat server

```shell
# Windows
$ ./bin/startup.bat
# Linux
$ ./bin/startup.sh
```

Visiting HelloServlet page no authentication.

```shell
$ curl http://localhost:{your_port}/{your_context}/public/hello
```

Visiting UserServlet page authenticate by role `END_USER`.

``` shell
$ curl http://localhost:{your_port}/{your_context}/user
```

Visiting AdminServlet page authenticate by role `ADMIN`.

```shell
$ curl http://localhost:{your_port}/{your_context}/admin
```



## References

[1] [Java Servlet Security Example](https://examples.javacodegeeks.com/enterprise-java/servlet/java-servlet-security-example/)

[2] [Examples: Securing Web Applications - The Java EE 5 Tutorial](https://docs.oracle.com/javaee/5/tutorial/doc/bncbx.html)

[3] [Add tomcat server credentials to project's pom and not settings.xml - StackOverflow](https://stackoverflow.com/questions/17841070/add-tomcat-server-credentials-to-projects-pom-and-not-settings-xml)