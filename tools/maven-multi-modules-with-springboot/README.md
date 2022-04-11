# Maven Multi-Module Project

## Spring Boot Project Maven Configuration

In parent project `pom.xml`

1. Add spring boot 

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.6.4</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

or

```xml
<properties>
    <!-- included spring-boot-starter-parent -->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
    <!-- custom properties -->
    <spring-boot.version>2.6.4</spring-boot.version>
</properties>

<dependencyManagement>
    <dependencies>
        <!-- included spring-boot-starter-parent -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<build>
    <plugins>
        <!-- included spring-boot-starter-parent. For unit testing. -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.0.0-M5</version>
        </plugin>
    </plugins>
</build>
```

2. Add modules

```xml
<modules>
    <module>web</module>
    <module>service</module>
</modules>
```

In runnable submodule project `pom.xml`

1. Add package plugin `spring-boot-maven-plugin`

```xml
<build>
    <plugins>
        <!-- for package runnable jar -->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

2. Add parent

```xml
<parent>
    <artifactId>maven-multi-modules-project</artifactId>
    <groupId>com.taogen.demo</groupId>
    <version>1.0-SNAPSHOT</version>
</parent>
```

3. Add dependent submodule

```xml
<dependency>
    <groupId>com.taogen.demo</groupId>
    <artifactId>service</artifactId>
    <version>${project.parent.version}</version>
</dependency>
```

## Testing

Testing all modules:

```
$ mvn test
```

## Running

In the submodule directory, running submodule web project by following command:

```
cd web
mvn spring-boot:run
```

In the parent project directory, running submodule web project by following command:

```java
mvn spring-boot:run -pl <module_name>
```

```java
cd maven-multi-modules-project
mvn spring-boot:run -pl web
```

Visit http://localhost:8080/?name=<name> to see the result.

## Packaging

Only add spring-boot-maven-plugin configuration into runnable web submodule project:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

Notice: `<build><plugins><plugin>..spring-boot-maven-plugin`, not `<build><pluginManagement><plugins><plugin>..spring-boot-maven-plugin..`. 

If not add the plugin, when you running the jar by `java -jar web-1.0-SNAPSHOT.jar`, it will throws a error `no main manifest attribute, in web-1.0-SNAPSHOT.jar`.

In the parent directory or runnable submodule directory:

```java
mvn clean package -DskipTests
```

```
java -jar .\web\target\web-1.0-SNAPSHOT.jar
```

