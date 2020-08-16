# mybatis-generator Project

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
- mysql
- mybatis



## Building Project

### Step 1: Generating Maven Project

- maven-archetype-webapp 
- maven-archetype-quickstart

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example.mybatis.generator -DartifactId=mybatis-generator -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
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

Functions, User Roles

Business Processes

## Implementation

### Step1: Add MyBatis-Genertor Maven Plugin to `pom.xml`

```xml
<project>
    <!-- override mybatis generator parameters -->    <mybatis.generator.configurationFile>${basedir}/src/main/resources/mybatis/generatorConfig.xml</mybatis.generator.configurationFile>
</project>
```

```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.4.0</version>
    <executions>
        <execution>
            <id>Generate MyBatis Artifacts</id>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql-connector.version}</version>
        </dependency>
    </dependencies>
</plugin>
```

### Step2: Add `generatorConfig.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!-- Don't need to specify driver class. It's already added in pom.xml plugin dependencies. -->
    <!-- <classPathEntry location="/Program Files/IBM/SQLLIB/java/db2java.zip" />-->

    <properties resource="mybatis/db.properties"/>
    <!-- <properties url="file:///D:\Repositories\mybatis-generator\src\main\resources\mybatis\db.properties"/>-->
    <context id="MySQLTables" targetRuntime="MyBatis3">

        <!-- suppress comments -->
        <commentGenerator>
            <property name="suppressDate" value="false"/>
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <!-- <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"-->
        <!--                        connectionURL="jdbc:mysql://localhost:3306/test?serverTimezone=UTC"-->
        <!--                        userId="root"-->
        <!--                        password="root">-->
        <!-- </jdbcConnection>-->

        <jdbcConnection driverClass="${MYSQL_DRIVER_CLASS}"
                        connectionURL="${MYSQL_URL}"
                        userId="${MYSQL_USER}"
                        password="${MYSQL_PASSWD}">
        </jdbcConnection>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <javaModelGenerator targetPackage="com.taogen.example.mybatis.generator.entity" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <sqlMapGenerator targetPackage="com.taogen.example.mybatis.generator.entity" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.taogen.example.mybatis.generator.mapper"
                             targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <table tableName="t_user" domainObjectName="User"
               enableCountByExample="false" enableUpdateByExample="false"
               enableDeleteByExample="false" enableSelectByExample="false"
               selectByExampleQueryId="false">
            <!-- use database column name as class filed name -->
            <!--            <property name="useActualColumnNames" value="true"/>-->
            <!--            <generatedKey column="id" sqlStatement="MySQL" identity="true" />-->
            <!-- override column name -->
            <!--            <columnOverride column="DATE_FIELD" property="startDate" />-->
            <!-- ignore generate columns -->
            <!--            <ignoreColumn column="FRED" />&ndash;&gt;-->
            <!-- override column data type -->
            <!--            <columnOverride column="LONG_VARCHAR_FIELD" jdbcType="VARCHAR" />-->
        </table>

    </context>
</generatorConfiguration>
```



## Test

Running Maven command:

```shell
mvn mybatis-generator:generate
```



## References

[1] [MyBatis Generator](https://mybatis.org/generator/)

[2] [MyBatis Generator - Running MyBatis Generator With Maven](https://mybatis.org/generator/running/runningWithMaven.html)