# Spring Boot 2 + Hibernate Validator



### Steps

- Creating a maven project. Crate from archetype `maven-archetype-quickstart`

- Add properties, dependencies, and plugins in `pom.xml`

  ```xml
  <properties>
      <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
      <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
      <java.version>1.8</java.version>
  </properties>
  
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.1.6.RELEASE</version>
  </parent>
  
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
      </dependency>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-autoconfigure</artifactId>
      </dependency>
      <!-- hibernate validator already in starter-web -->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  	<!-- lombok -->
      <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <version>1.18.4</version>
          <scope>provided</scope>
      </dependency>
  </dependencies>
  
  <build>
      <pluginManagement>
          <plugins>
              <!-- Package as an executable jar/war. $ mvn package spring-boot:repackage -->
              <plugin>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <executions>
                      <execution>
                          <goals>
                              <goal>repackage</goal>
                          </goals>
                      </execution>
                  </executions>
                  <configuration>
                      <jvmArguments>
                          -Duser.timezone=Asia/Shanghai
                      </jvmArguments>
                  </configuration>
              </plugin>
          </plugins>
      </pluginManagement>
  </build>
  ```

  

- Writing Spring Boot Main function

  ```java
  @SpringBootApplication
  public class App
  {
      public static void main(String[] args)
      {
          SpringApplication.run(App.class, args);
      }
  }
  ```

  

- Writing business code

  - Writing entity `User.java`, and add validate annotation.
  - Writing controller `UserController.java`
  - Writing form page. `index.html`

- Running Unit Test

- Running Application.