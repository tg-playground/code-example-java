# Spring Boot 2 + JUnit 4

### Steps

- Create a maven project. Crate from archetype `maven-archetype-quickstart`
- Add Properties, Dependencies, Plugins in `pom.xml`
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
            <!-- unit test -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
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
                            -Dapp.name=hot-crawler
                            -Duser.timezone=Asia/Shanghai
                        </jvmArguments>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
    ```

- Writing Spring Boot Start main function

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

- Writing HelloController.java

  ```java
  package com.taogen;
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RestController;
  
  @RestController("HelloController")
  @RequestMapping({"/"})
  public class HelloController
  {
      @Autowired
      private HelloService helloService;
  
      @GetMapping(value = "/hello", produces = "application/json;charset=UTF-8")
      public String sayHello()
      {
          String message = helloService.sayHello();
          return "{ret_code: 0, ret_msg: " + message + "}";
      }
  }
  ```

- Writing HelloService.java

  ```java
  package com.taogen;
  
  import org.springframework.stereotype.Service;
  
  @Service
  public class HelloService
  {
      public String sayHello()
      {
          return "Hello World!";
      }
  }
  ```

- Writing HelloControllerTest.java

  ```java
  package com.taogen;
  
  import org.junit.Assert;
  import org.junit.Before;
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.mock.web.MockServletContext;
  import org.springframework.test.context.junit4.SpringRunner;
  import org.springframework.test.web.servlet.MockMvc;
  import org.springframework.test.web.servlet.MvcResult;
  import org.springframework.test.web.servlet.setup.MockMvcBuilders;
  import org.springframework.web.context.WebApplicationContext;
  
  import javax.servlet.ServletContext;
  
  import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
  import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
  import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
  import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
  
  @RunWith(SpringRunner.class)
  @SpringBootTest
  public class HelloControllerTest
  {
      @Autowired
      private WebApplicationContext wac;
  
      private MockMvc mockMvc;
  
      @Before
      public void setup()
      {
          this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
      }
  
      @Test
      public void verifyContext() {
          ServletContext servletContext = wac.getServletContext();
  
          org.junit.Assert.assertNotNull(servletContext);
          Assert.assertTrue(servletContext instanceof MockServletContext);
          Assert.assertNotNull(wac.getBean("HelloController"));
      }
      
      @Test
      public void sayHelloTest() throws Exception
      {
          MvcResult mvcResult = this.mockMvc.perform(get("/hello")).andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.ret_code").value(0))
                  .andExpect(jsonPath("$.ret_msg").value("Hello World!"))
                  .andReturn();
          Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
      }
  }
  ```

- Writing HelloServiceTest.java

  ```java
  package com.taogen;
  
  import org.junit.Assert;
  import org.junit.Test;
  import org.junit.runner.RunWith;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.test.context.junit4.SpringRunner;
  
  @RunWith(SpringRunner.class)
  @SpringBootTest
  public class HelloServiceTest
  {
      @Autowired
      private HelloService helloService;
  
      @Test
      public void sayHelloTest()
      {
          Assert.assertEquals("Hello World!", helloService.sayHello());
      }
  }
  ```

- Running Unit Test

  ```shell
  $ mvn test
  ```

- Running Application

  ```shell
  $ mvn spring-boot:run
  $ curl http://localhost:8080/hello
  ```

--END--