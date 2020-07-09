# ds-1-app-cluster Project

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
- Servlet



## Building Project

### Step 1: Generating Maven Project

- maven-archetype-webapp 

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=ds-1-app-cluster -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Add project directories structures  

```shell
cd ds-1-app-cluster
mkdir -p src/main/java/com/taogen/example/appcluster src/test/java/com/taogen/example/appcluster
# or
mkdir -p src\main\java\com\taogen\example\appcluster src\test\java\com\taogen\example\appcluster
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
    <servlet.version>3.0.1</servlet.version>
    <servlet.artifactId>javax.servlet-api</servlet.artifactId>
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

Books, Docs, URLs

**<<<<!!{{update me}}!!>>>>**