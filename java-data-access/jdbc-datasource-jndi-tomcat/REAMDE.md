# jdbc-datasource-jndi-tomcat Project 

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
- Apache Tomcat 8

Dependencies

- Test
  - junit v4.12
  - mockito-core v2.23.4
- Logging
  - log4j-web v2.8.2
- javax.servlet-api v3.0.1
- mysql-connector-java v5.1.48



## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=jdbc-datasource-jndi-tomcat -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Add project directories structures  

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example.jdbc.datasource`

```shell
cd jdbc-datasource-jndi-tomcat
# Linux
mkdir -p src/main/java/com/taogen/example/jdbc/datasource src/test/java/com/taogen/example/jdbc/datasource
# Windows
mkdir src\main\java\com\taogen\example\jdbc\datasource src\test\java\com\taogen\example\jdbc\datasource
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
    <junit.version>4.12</junit.version>
    <log4j.version>2.8.2</log4j.version>
    <!-- changeit -->
    <servlet.version>3.0.1</servlet.version>
    <servlet.artifactId>javax.servlet-api</servlet.artifactId>
    <mysql-connector.version>5.1.48</mysql-connector.version>
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
    
    <!-- MySQL Conncector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql-connector.version}</version>
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

Not need web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
	      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	      xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
	      http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	      version="3.0">
```



## Requirements

Functions

- Connection Tomcat JDBC Data Source

## Implementation

1. Configuring Tomcat Data Source in `conf/context.xml`

```xml
<!-- MySQL 8 -->
<Resource name="jdbc/TestDB" auth="Container" type="javax.sql.DataSource"
          maxActive="100" maxIdle="30" maxWait="10000"
          username="root" password="root" driverClassName="com.mysql.cj.jdbc.Driver"
          url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC"/>
```

2. Creating `maven-archetype-webapp` project

3. Add `DataSourceUtil` to get Tomcat DataSource by JNDI and get Connection

```java
public class DataSourceUtil {

    private static final Logger logger = LogManager.getLogger();
    private static DataSource dataSource;
    
	public static DataSource getDataSource(){
        if (dataSource == null){
            synchronized (DataSourceUtil.class){
                if (dataSource == null){
                    try {
                        Context context = new InitialContext();
                        dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
                    } catch (NamingException e) {
                        logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
                    }
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
        } catch (SQLException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
        return connection;
    }
}
```

4. Add `HelloServlet` to print database table data.





## Test

### Running and Visiting Project

Package project, move war file to tomcat `/webapps`, running tomcat

Visiting Index Page

```shell
$ curl http://localhost:{your_port}/{your_context}
```

Visiting `HelloWorldServlet`  

```shell
$ curl http://localhost:{your_port}/{your_context}/hello
```



## References

- [Tomcat DataSource JNDI Example in Java](https://www.journaldev.com/2513/tomcat-datasource-jndi-example-java)