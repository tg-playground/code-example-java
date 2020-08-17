# mybatis-xml Project

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

Dependencies

- Test
  - junit v4.12
  - mockito-core v2.23.4
- Logging
  - log4j-web v2.8.2
- mysql-connector-java
- mybatis



## Building Project

### Step 1: Generating Maven Project

- maven-archetype-webapp 
- maven-archetype-quickstart

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example.mybatis.xml -DartifactId=mybatis-xml -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

### Step 2: Add project directories structures  

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
    <mysql-connector.version>8.0.19</mysql-connector.version>
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
	
    <!-- MyBatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.5</version>
    </dependency>

    <!-- MySQL Connector -->
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
    <Properties>
        <Property name="my_pattern">%d [%t] %-5level %logger{36} - %msg%n</Property>
        <Property name="filename">app.log</Property>
    </Properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${my_pattern}"/>
        </Console>
        <File name="File" fileName="${filename}">
            <PatternLayout pattern="${my_pattern}" />
        </File>
    </Appenders>
    <Loggers>
        <Logger name="com.taogen.example" level="debug" additivity="false">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Logger>
        <Root level="error">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```



## Requirements

Functions

- Basic CURD in Main
- Single-table CURD in service and unit tests

## Implementation

### Step1: Generate entity classes, entity mapper xmls, mapper interfaces from the generator project.

```shell
mvn mybatis-generator:generate
```

### Step2: Config mybatis-config.xml

```xml
<configuration>
    <properties resource="mybatis/db.properties">
    </properties>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${MYSQL_DRIVER_CLASS}"/>
                <property name="url" value="${MYSQL_URL}"/>
                <property name="username" value="${MYSQL_USER}"/>
                <property name="password" value="${MYSQL_PASSWD}"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <mapper resource="mybatis/mapper/UserMapper.xml" />
    </mappers>
</configuration>
```

### Step3: Write Main.java

```java
public static void main(String[] args) throws IOException {
    String resource = "mybatis/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    try (SqlSession session = sqlSessionFactory.openSession()) {
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1);
        logger.debug("user is {}", user);
    }
}
```

### Step4: Write Services

See the source code.



## Test

Run `Main.java`

Run `UserServiceImplTest.java`

Run All Tests:

```shell
mvn test
```



## References

[1] [Quick Guide to MyBatis](https://www.baeldung.com/mybatis)

[2] [MyBatis Getting started](https://mybatis.org/mybatis-3/getting-started.html)