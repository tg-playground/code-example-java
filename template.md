# xxx Project

Content

- Environments
- Building Project
- Implementation
- Test
- References



## Environments

Software

- JDK 1.8
- Maven
- ...

Dependencies



## Building Project

### Step 1: Generating Maven Project

### Step 2: Configuring Maven Project `pom.xml`

Set Maven Project Properties

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
    <!-- custom properties -->
    <project.java.version>1.8</project.java.version>
</properties>
```

Add Maven Dependencies

```xml
<!-- unit test-->

<!-- logging -->

```

Add Maven Plugins

```xml
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

        <!-- Tomcat plugin -->
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <port>9000</port>
                <path>/servlet-logging-log4j2</path>
            </configuration>
        </plugin>
    </plugins>
</build>
```



### Step 3: Add project file structures  

### **<<<<<<!!{update me}!!>>>>>>**

Add "Sources Root" `src/main/java`

Add "Test Sources Root" `src/test/java`

Add package `com.taogen.example`



## Implementation

### Step1: xxx

...

## Test



## References