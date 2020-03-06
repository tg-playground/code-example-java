# jdbc-basic-01-hello Project

Content

- Environments
- Building Project
- Requirements
- Implementation
- Test
- References



## Environments

Software

- JDK 1.8
- Maven
- mysql 5

Dependencies

- Test
  - junit v4.12
  - mockito-core v2.23.4
- Logging
  - log4j-web v2.8.2
- Tools
  - lombok v1.18.10
  - gson v2.8.5
  - commons-lang3 v3.9
- Optional Tools
  - jackson v2.9.8
  - commons-io v2.6
- mysql-connector-java v5.1.48



## Building Project

### Step 1: Generating Maven Project

- maven-archetype-quickstart

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=jdbc-basic-01-hello -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Step 2: Add project directories structures  

```shell
cd jdbc-basic-01-hello
mkdir -p src/main/java/com/taogen/example/jdbc/hello src/test/java/com/taogen/example/jdbc/hello
# or
mkdir -p src\main\java\com\taogen\example\jdbc\hello src\test\java\com\taogen\example\jdbc\hello
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
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.9</version>
    </dependency>
    
    <!-- ** Optional Common Tools ** -->
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
        <groupId>commons-fileupload</groupId>
        <artifactId>commons-fileupload</artifactId>
        <version>1.4</version>
    </dependency>
    <dependency>
        <groupId>commons-io</groupId>
        <artifactId>commons-io</artifactId>
        <version>2.6</version>
    </dependency>
	MYSQL
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



## Requirements

Functions, User Roles

Business Processes

## Implementation

### Step1: xxx

...

## Test



## References

JDBC General

- [JDBC API Documentation](https://download.oracle.com/otn_hosted_doc/jdeveloper/904preview/jdk14doc/docs/guide/jdbc/index.html)
- [JDBC Basic Tutorial - doc](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
- [JDBC Tutorial - tutorialspoint](https://www.tutorialspoint.com/jdbc/index.htm)

Data Source

- [Connecting with DataSource Objects - doc](https://docs.oracle.com/javase/tutorial/jdbc/basics/sqldatasources.html)
- [Java DataSource, JDBC DataSource Example - JournalDev](https://www.journaldev.com/2509/java-datasource-jdbc-datasource-example)

Others

- [How do I get the size of a java.sql.ResultSet?](https://stackoverflow.com/questions/192078/how-do-i-get-the-size-of-a-java-sql-resultset)
- [Check if a MySQL table exists](https://electrictoolbox.com/check-if-mysql-table-exists/)
- [IF NOT EXISTS Statements](https://dev.mysql.com/doc/refman/8.0/en/create-database.html)
- [Should I close a Connection obtained from a DataSource manually?](https://stackoverflow.com/questions/23957390/should-i-close-a-connection-obtained-from-a-datasource-manually)