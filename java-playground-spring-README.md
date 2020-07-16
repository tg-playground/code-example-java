# Spring Play



<h3 id="content">Content</h3>

- [About Dependency](#ady)
- Spring Core
  - Spring IOC
    - [spring-ioc-by-xml](#sibx)
    - [spring-ioc-by-annotation](#siba)
    - [spring-ioc-by-java](#sibj)
    - [spring-ioc-by-xml-with-servlet-startup](#sibxwss)
    - [spring-ioc-by-anno-with-servlet-startup](#sibawss)
    - [spring-ioc-di](#sid)
  - AOP
    - [spring-aop-by-xml](#sabx)
    - [spring-aop-by-annotation](#saba)
- Web Application
  - Spring MVC
    - [spring-mvc-basic](#smb)
    - [spring-mvc-data-validation](#smdv)
    - spring-mvc-data-formatter
    - spring-mvc-type-conversion
    - spring-mvc-return-object2json
  - REST APIs
- Working with Data 
  - JdbcTemplate
  - Spring Data JPA
  - Transaction
  - spring-integrate-orm
- Spring Test
  - [spring-test-basic](#stb)
- Reactive Programming
  - Spring WebFlux
- Spring Boot
- Spring Security
  - spring-security
- Spring Integration Spring Batch
- Spring Deploy with Docker
- Spring Cloud



---

### Main

<h3 id="ady">About Dependency</h3>

Description of modules

- `spring-beans`, `spring-core`, `spring-context` is the most important dependencies in spring. It accomplish the IOC function.

Usage

- the `spring-context` dependency contains `spring-beans` , `spring-core `. Just add the one dependency in your `pom.xml` when you using IOC.
- the `spring-web` dependency when you are developing a web application.

[`back to content`](#content)

### Spring IOC
<h3 id="sibx">Spring IOC by xml</h3>

Steps with this play

- Creating a new blank maven project
- Add spring-context Dependency in pom.xml.  

    pom.xml

    ```xml
    <properties>
        <spring.version>5.1.5.RELEASE</spring.version>
    </properties>
    <dependencies>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${spring.version}</version>
        </dependency>
    </dependencies>
    ```

- Add a Java Class file of my bean.

    MyBean.java

    ```java
    public class MyBean
    {
        private String name;
        public MyBean() {}
        public MyBean(String name)
        {
            this.name = name;
        }
        public void sayHello()
        {
            System.out.println("hello by " + name);
        }
    }
    ```

- Creating a file of Spring bean configuration. To configuring bean.

    applicationContext.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans 
                               http://www.springframework.org/schema/beans/spring-beans.xsd">
        <bean id="MyBean" class="com.taogen.springiocbyxml.MyBean">
            <constructor-arg value="HelloBean"></constructor-arg>
        </bean>
    </beans>
    <!-- 
    // notice content
    xsi:schemaLocation="http://www.springframework.org/schema/beans 
    <bean id="MyBean" class="com.taogen.springiocbyxml.MyBean">
    -->
    ```

- Writing Main Class to Test. Getting bean by ClassPathXmlApplicationContext Object.

    Main.java

    ```java
        public static void main(String[] args)
        {
            BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
            MyBean bean = (MyBean) beanFactory.getBean("MyBean");
            bean.sayHello();
        }
    ```

[`back to content`](#content)

---


<h3 id="siba">Spring IOC by Annotation</h3>

Steps with this play

- Creating a new blank maven project.

- Adding spring-context dependency in pom.xml

    pom.xml

    ```xml
    <properties>
        <spring.version>5.1.5.RELEASE</spring.version>
    </properties>
    <dependencies>
        <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${spring.version}</version>
        </dependency>
    </dependencies>
    ```

- Creating a Java Class file of my bean with annotation @Repository.

    MyBean.java

    ```java
    @Repository
    public class MyBean
    {
        private String name;
        public MyBean() {}
        public MyBean(String name)
        {
            this.name = name;
        }
        public void sayHello()
        {
            System.out.println("hello by " + name);
        }
    }
    ```

- Creating  a file of Spring Bean Configuration. To configuring context:componet-scan

    applicationContext.xml

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xmlns:context="http://www.springframework.org/schema/context"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd">

        <context:component-scan base-package="com.taogen.springiocbyannotation">
        </context:component-scan>
    </beans>
    <!--
    // notice content
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/context
    <context:component-scan base-package="com.taogen.springiocbyannotation">
    -->
    ```

- Writing Main class to test. Getting bean by AnnotationConfigApplicationContext Object.

    Main.java

    ```java
        public static void main(String[] args)
        {
            // method 1
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
            ctx.register(MyBean.class);
            ctx.refresh();
            MyBean myBean = (MyBean) ctx.getBean(MyBean.class);
            myBean.sayHello();
            
            // method 2 and 3
            BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
            MyBean myBean1 = (MyBean) beanFactory.getBean("MyBean1"); // @Component(value = "MyBean1")
            myBean1.sayHello();
    
            MyBean myBean2 = (MyBean) beanFactory.getBean(MyBean.class);
            myBean2.sayHello();
    
        }
    ```

[`back to content`](#content)

---


<h3 id="sibj">Spring IOC by Java</h3>

Steps with this play

- Creating a new blank maven project.
- Adding spring-context dependency in pom.xml
- Creating a Java Class file of my bean.
- Create a Java-based configuration file. 

    AppConfig.java

    ```java
    /**
     * The AppConfig class above would be equivalent to the following Spring <beans/> XML:
     * <beans>
     *   <bean id="myBean" class="com.taogen.springiocbyjava.MyBean"/>
     * </beans>
     */
    @Configuration
    public class AppConfig
    {
        @Bean
        public MyBean myBean()
        {
            return new MyBean();
        }
    }
    ```

- Creating  a file of Spring Bean Configuration. To configuring context:componet-scan

	applicationContext.xml

    ```xml
  <context:component-scan base-package="com.taogen.springiocbyjava"></context:component-scan>
    ```

- Writing Main class to test. Get bean by AnnotationConfigApplicationContext Object.

	```java
    public static void main(String[] args)
    {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MyBean myBean = ctx.getBean(MyBean.class);
        myBean.sayHello();
    }
	```

[`back to content`](#content)

---



<h3 id="sibxwss">Spring IOC by XML with Servlet Startup</h3>

Steps of this play

- Creating a new webapp maven project.

    Idea -- menu bar -- File -- New -- Project -- Select Maven in left of new window -- Create from archietype -- Select org.apache.maven.archetypes:maven-archetype-webapp -- Next -- fill your GroupId, Artifactid -- Next -- Maven home directory don't care, just click Next -- Fill your project name -- Finish.

- Adding dependencies. Such as `javax.servlet-api `, `spring-context`, `spring-web`.

  ```xml
    <properties>
        <spring.version>5.1.5.RELEASE</spring.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
    </dependencies>
  ```

- Writing a servlet class file.
  MyServlet.java

  ```java
  public class MyServlet extends HttpServlet
  {
      @Override
      public void doGet(HttpServletRequest request, HttpServletResponse response)
      {
          try {
              PrintWriter pw = response.getWriter();
              pw.write("Hello by MyServlet");
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  }
  ```
  
- Configuring servlet map in web.xml

  web.xml

  ```xml
    <servlet>
      <servlet-name>MyServlet</servlet-name>
      <servlet-class></servlet-class>
    </servlet>
    <servlet-mapping>
      <servlet-name>MyServlet</servlet-name>
      <url-pattern>/MyServlet</url-pattern>
    </servlet-mapping>
  ```

- Running Servlet with tomcat

  (1) Running maven package

  Idea --> menu bar --> Run --> Edit Configurations --> click + --> Maven --> fill your Name: package, command line: package --> apply --> OK.

  menu bar --> run package(your just configured)

  (2) Configuring tomcat

  Idea --> menu bar --> Run --> Edit Configurations --> click + --> Select Tomcat Server --> Local --> Fill your server Name -- > Configure --> find tomcat path or select choice exist added server --> switch to "Deployment" tab --> click + --> Select Artiface --> select one of Your artifact --> OK --> Apply --> OK

  (3) Run tomcat

  menu bar --> run tomcat (your just configured)

  (4) Visiting your servlet

  Your http://localhost:8080 + Application Context + Servlet Name

  example http://localhost:8080/MyServlet

- Creating your bean.

    MyBean.java

    ```java
    public class MyBean
    {
        private String name;
        public MyBean() {}
        public MyBean(String name)
        {
            this.name = name;
        }
        public void sayHello()
        {
            System.out.println("hello by " + name);
        }
    }
    ```

- Creating a bean configuration file. 

  applicationConext.xml
  
  ```xml
  <bean id="MyBean" class="com.taogen.springiocwithservlet.bean.MyBean">
      <constructor-arg value="My Bean 1"></constructor-arg>
  </bean>
  ```
  
- Creating Main.java to test Spring IOC 

    Main.java

    ```java
    public static void main (String[] args)
    {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyBean myBean = (MyBean) beanFactory.getBean("MyBean");
        myBean.sayHello();
    }
    ```

- add contextLoaderListener in `web.xml`.  **When servlet application running, the spring ioc will instantiated**

    ```xml
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>
    
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    ```

- Using Spring bean in Servlet by org.springframework.web.context.support.WebApplicationContextUtils

    ```java
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    {
        ApplicationContext beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        MyBean myBean = (MyBean) beanFactory.getBean("MyBean", MyBean.class);
        myBean.sayHello();
    }
    ```



[`back to content`](#content)

---


<h3 id="sibawss">spring IOC by Annotation with Servlet Startup</h3>

This play simple steps. More details you can refer "spring IOC by XML with Servlet Startup".

- Creating a new webapp maven project.

- Adding dependencies.

- Writing a servlet class file.

- Configuring servlet map in web.xml

- Running Servlet with tomcat

- Creating your bean with annotation @Component.

  ```java
  @Component(value = "MyBean1")
  public class MyBean
  {
      private String name;
      public MyBean() {}
      public MyBean(String name)
      {
          this.name = name;
      }
      public String sayHello()
      {
          System.out.println("hello by " + name);
          return "hello by " + name;
      }
  }
  ```

- Creating a bean configuration file.

  applicationConext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
      <context:component-scan base-package="com.taogen.springiocwithservlet.bean" />
  </beans>
  ```

- Creating Main.java to test Spring IOC 

  Main.java

  ```java
  BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
  MyBean myBean1 = (MyBean) beanFactory.getBean("MyBean1");
  myBean1.sayHello();
  ```

- add contextLoaderListener in `web.xml`.  **When servlet application running, the spring ioc will instantiated**.

- Using Spring bean in Servlet by org.springframework.web.context.support.WebApplicationContextUtils.

[`back to content`](#content)

---

<h3 id="sid">Spring IOC Dependency Injection</h3>

- Spring IOC DI by XML. Using ref.

- Spring IOC DI by Annotation. Using @autowired.

- Spring IOC DI by Java. Using setter.

This play steps. 

- Creating new maven project.

- Add dependencies in pom.xml.

  pom.xml

  ```xml
  <properties>
  	<spring.version>5.1.5.RELEASE</spring.version>
  </properties>
  <dependencies>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${spring.version}</version>
      </dependency>
  </dependencies>
  ```

- Creating your bean java class.

  MyXmlBean.java and MyJavaBean.java

  ```java
  public class MyXmlBean implements AbstractBean
  {
      private String name;
      private MyInjectBean injectBean;
      public String getName()
      {
          return name;
      }
      public void setName(String name)
      {
          this.name = name;
      }
  
      public MyInjectBean getInjectBean()
      {
          return injectBean;
      }
  
      public void setInjectBean(MyInjectBean injectBean)
      {
          this.injectBean = injectBean;
      }
      @Override
      public String sayHello() {
          String s = "Hello by " + this.name;
          System.out.println(s);
          injectBean.sayHello();
          return s;
      }
  }
  ```

  MyAnnoBean.java

  ```java
  @Component
  public class MyAnnoBean implements AbstractBean
  {
      @Value("AnnoBean1")
      private String name;
      @Autowired
      private MyInjectBean injectBean;
      @Override
      public String sayHelo() 
      {
          String s = "Hello by " + this.name;
          System.out.println(s);
          injectBean.sayHelo();
          return s;
      }
  }
  ```

  MyInjectBean.java

  ```java
  public class MyInjectBean implements AbstractBean
  {
      private String name;
      public String getName() {
          return name;
      }
      public void setName(String name) {
          this.name = name;
      }
      public MyInjectBean(){}
      public MyInjectBean(String name){this.name = name;}
      @Override
      public String sayHelo() {
          String s = "I am Inject Bean. My name is " + this.name;
          System.out.println(s);
          return s;
      }
  }
  ```

- Creating spring bean configuration file. 

  applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
      <context:component-scan base-package="com.taogen.springiocdi.Bean"  />
  
      <bean id="MyXmlBean" class="com.taogen.springiocdi.Bean.MyXmlBean">
          <property name="name" value="xmlBean1" />
          <property name="injectBean" ref="MyInjectBean" />
      </bean>
  
      <bean id="MyInjectBean" class="com.taogen.springiocdi.Bean.MyInjectBean">
          <property name="name" value="injectBeanByXmlBean" />
      </bean>
  </beans>
  ```

- Creating Spring Bean by Java.

  AppConfig.java

  ```java
  @Configuration
  public class AppConfig
  {
      @Bean
      public MyJavaBean getMyJavaBean()
      {
          MyJavaBean javaBean = new MyJavaBean("javaBean1");
          javaBean.setInjectBean(new MyInjectBean("injectBeanByJavaBean"));
          return javaBean;
      }
  }
  ```

- Creating Main class to test ioc.

  Main.java

  ```java
  public static void main(String[] args)
  {
      ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
      MyXmlBean xmlBean = applicationContext.getBean(MyXmlBean.class);
      xmlBean.sayHello();
      MyAnnoBean annoBean = applicationContext.getBean(MyAnnoBean.class);
      annoBean.sayHello();
      MyJavaBean javaBean = applicationContext.getBean(MyJavaBean.class);
      javaBean.sayHello();
  }
  /*
  result:
  Hello by xmlBean1
  I am Inject Bean. My name is injectBeanByXmlBean
  Hello by AnnoBean1
  I am Inject Bean. My name is injectBeanByXmlBean
  Hello by javaBean1
  I am Inject Bean. My name is injectBeanByJavaBean
  */
  ```

  

[`back to content`](#content)

---



### Spring AOP

<h3 id="sabx">Spring AOP By XML</h3>

This play steps

- Creating a new maven project. 

- Adding dependencies `spring-context`, `aspectJ` in pom.xml

  pom.xml

  ```xml
  <properties>
  	<spring.version>5.1.5.RELEASE</spring.version>
  </properties>
  <dependencies>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.aspectj</groupId>
          <artifactId>aspectjweaver</artifactId>
          <version>1.9.3</version>
      </dependency>
  </dependencies>
  ```

- Creating Aspect Java Class.

  Logging.java

  ```java
  public class Logging
  {
      public void beforeAdvice()
      {
          System.out.println("before..");
      }
  
      public void afterAdvice()
      {
          System.out.println("after..");
      }
  
      public void afterReturnAdvice()
      {
          System.out.println("after return..");
      }
  
      public void afterThrowingAdvice()
      {
          System.out.println("after throwing..");
      }
  }
  ```

- Creating My Target Bean Java Class.

  MyBean

  ```java
  public class MyBean
  {
      public String sayHello()
      {
          System.out.println("sayHello()...");
          return "sayHello";
      }
  }
  ```

- Creating Spring configuration xml file.

  applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:aop = "http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
      <bean id="logging" class="com.taogen.springaopbyxml.aspect.Logging" />
      <bean id="myBean" class="com.taogen.springaopbyxml.bean.MyBean" />
      <aop:config>
          <aop:aspect id="log" ref="logging">
              <aop:pointcut id="logMyBean" expression="execution(* com.taogen.springaopbyxml.bean.*.*(..))" />
              <aop:before method="beforeAdvice" pointcut-ref="logMyBean" />
              <aop:after method="afterAdvice" pointcut-ref="logMyBean" />
              <aop:after-returning method="afterReturnAdvice" pointcut-ref="logMyBean" />
              <aop:after-throwing method="afterThrowingAdvice" pointcut-ref="logMyBean" />
          </aop:aspect>
      </aop:config>
  </beans>
  ```

- Creating Main Java Class to test Spring AOP.

  Main.java

  ```java
  public static void main(String[] args)
  {
      BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
      MyBean myBean = beanFactory.getBean(MyBean.class);
      myBean.sayHello();
  }
  /* 
  Result:
  before..
  sayHello()...
  after..
  after return..
  */
  ```

[`back to content`](#content)

---

<h3 id="saba">Spring AOP by Annotation</h3>

This play steps

- Creating a new maven project.

- Adding dependencies `spring-context`, `aspectjweaver` in pom.xml

  pom.xml refer spring aop by xml section.

- Creating my aspect Java class with annotation `@Aspect`, `@Component`.

  MyAspect.java

  ```java
  @Aspect
  @Component
  public class MyAspect
  {
      @Pointcut("execution(* com.taogen.springaopbyanno.bean.*.*(..))")
      public void loggingMyBean() {}
  
      @Before("loggingMyBean()")
      public void beforeAdvice()
      {
          System.out.println("Before..");
      }
      @After("loggingMyBean()")
      public void afterAdvice()
      {
          System.out.println("After..");
      }
      @AfterReturning("loggingMyBean()")
      public void afterReturnAdvice()
      {
          System.out.println("After return..");
      }
  }
  ```

- Creating my bean Java class with annotation `@Component`.

  MyBean.java

  ```java
  @Component
  public class MyBean
  {
      public String sayHello()
      {
          System.out.println("sayHello()...");
          return "sayhello()";
      }
  }
  ```

- Creating Spring configuration file.

  applicationContext.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:aop = "http://www.springframework.org/schema/aop"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
         http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
      <context:component-scan base-package="com.taogen.springaopbyanno"  />
      <aop:aspectj-autoproxy/>
  </beans>
  ```

- Creating Main java class to test spring AOP.

  Main.java

  ```java
  public  static void main(String[] args)
  {
      BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
      MyBean myBean = beanFactory.getBean(MyBean.class);
      myBean.sayHello();
  }
  /* 
  Result:
  Before..
  sayHello()...
  After..
  After return..
  */
  ```

  

[`back to content`](#content)

---



### Web Application

<h3 id="smb">Spring MVC Basic</h3>

This play steps

- Creating new maven project.

- Adding dependencies in pom.xml. `spring-context`, `spring-aop`, `spring-web`, `spring-webmvc`.

  pom.xml

  ```xml
  <properties>        
  	<spring.version>5.1.5.RELEASE</spring.version>
  </properties>
  <dependencies>
  	<dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-aop</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-web</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-webmvc</artifactId>
          <version>${spring.version}</version>
      </dependency>
  </dependencies>
  ```

- Creating My Controller Java class file with annotation `@Controller`, `@RequestMapping`.

  MyController.java

  ```java
  @Controller
  public class MyController
  {
      @ResponseBody
      @RequestMapping("/sayHello")
      public String sayHello()
      {
          return "hello by My Controller!";
      }
  
      @RequestMapping("/indexPage")
      public String indexPage()
      {
          return "index";
      }
  }
  ```

- Configuring web.xml, add Spring MVC DispatcherServlet.

  web.xml

  ```xml
  <servlet>
      <servlet-name>DispatcherServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param> 
          <param-name>contextConfigLocation</param-name>
          <param-value>classpath:springmvc.xml</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
      <servlet-name>DispatcherServlet</servlet-name>
      <url-pattern>/</url-pattern>
  </servlet-mapping>
  <!-- contextConfigLocation: To specify the springMVC configuration file location. Otherwise, the default configuration file is /WEB-INF/{your servlet name}-servlet.xml -->
  ```

  

- Creating Spring bean configuration xml file. Configuring `context:component-scan`, `ViewResolver`.

  springmvc.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd ">
      <context:component-scan base-package="com.taogen.springmvc" />
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/" />
          <property name="suffix" value=".jsp" />
      </bean>
  </beans>
  ```

- Running web application on Tomcat.

  Refer spring-ioc-with-servlet-startup.

- Visiting your URL.

  http://localhost:8080/sayHello

  http://localhost:8080/indexPage
  
  

[`back to content`](#content)

---



<h3 id="smdh">Spring MVC Data Validation</h3>

Data Validation. Validating field if has error forward to form page and display error message.

This Play Steps.

- Creating new Maven project. 

- Adding dependencies in pom.xml. `spring-context`, `spring-web`, `spring-webmvc`, `javax.validation`

- Creating Entity Java Class.

  User.java

  ```java
  public class User
  {
      @NotNull
      @Min(1)
      private Integer id;
  
      @NotNull
      @Size(min=2, max=30)
      private String name;
  
      @NotNull
      @Range(min=1, max=150)
      private Integer age;
  
      @NotNull
      private String address;
      //... Constructor, getter, setter
  }
  ```

- Creating Controller Java Class.

  MyController.java

  ```java
  @RequestMapping(value="/postUser")
  public String postUser(@Valid User user)
  {
      return user.toString();
  }
  ```

- Configuring web.xml add Spring DispatcherServlet

  web.xml

  ```xml
  <servlet>
      <servlet-name>DispatcherServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  </servlet> 
  <servlet-mapping>
      <servlet-name>DispatcherServlet</servlet-name>
      <url-pattern>/</url-pattern>
  </servlet-mapping>
  ```

- Creating Spring Configuration xml file.

  springmvc.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd
          http://www.springframework.org/schema/mvc  http://www.springframework.org/schema/mvc/spring-mvc.xsd">
      <context:component-scan base-package="com.taogen.springmvcdatahandle" />
      <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/"/>
          <property name="suffix" value=".jsp" />
      </bean>
      <mvc:annotation-driven ></mvc:annotation-driven>
      <bean class="org.springframework.context.support.ResourceBundleMessageSource"
            id="messageSource">
          <property name="basename" value="messages" />
      </bean>
  </beans>
  ```

- Running this web application with Tomcat.

- Visiting your form page. Submit form.

[`back to content`](#content)

---

### Working with Data




[`back to content`](#content)

---



### Spring Test

<h3 id="stb">Spring Test Basic</h3>

This play steps

- Creating a new maven project.

- Adding dependencies in pom.xml. Dependencies for springmvc `spring-context`, `spring-aop`, `spring-web`, `spring-webmvc`, `javax.servlet-api`. Dependencies for test `spring-test`, `jnit`, `json-path`.

  pom.xml

  ```xml
  <properties>       
      <spring.version>5.1.5.RELEASE</spring.version>
  </properties>
  <dependencies>
      <!-- test -->
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-test</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>com.jayway.jsonpath</groupId>
          <artifactId>json-path</artifactId>
          <version>2.3.0</version>
      </dependency>
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
      </dependency>
  
      <!-- Spring mvc -->
      <dependency>
          <groupId>javax.servlet</groupId>
          <artifactId>javax.servlet-api</artifactId>
          <version>3.0.1</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-context</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-aop</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-web</artifactId>
          <version>${spring.version}</version>
      </dependency>
      <dependency>
          <groupId>org.springframework</groupId>
          <artifactId>spring-webmvc</artifactId>
          <version>${spring.version}</version>
      </dependency>
  </dependencies>
  ```

- Configuring web.xml add Spring MVC DispatcherServlet

  web.xml

  ```xml
  <servlet>
      <servlet-name>DispatcherServlet</servlet-name>
      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
      <init-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>classpath:springmvc.xml</param-value>
      </init-param>
      <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
      <servlet-name>DispatcherServlet</servlet-name>
      <url-pattern>/</url-pattern>
  </servlet-mapping>
  ```

- Creating my Controller

  ```java
  @Controller
  public class MyController
  {
      @RequestMapping("/sayHello")
      @ResponseBody
      public String sayHello()
      {
          return "hello by MyController";
      }
  
      @RequestMapping("/toIndex")
      public String toIndexPage()
      {
          return "index";
      }
  
      @RequestMapping(value="/returnJson", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
      @ResponseBody
      public String returnJson()
      {
          return "{message:ok, user: { name : Tom }}";
      }
  
      @RequestMapping(value="/returnJson", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
      @ResponseBody
      public String returnJsonByPost(String name)
      {
          return "{message:ok, user: { name : "+name+"}}";
      }
  }
  ```

- Creating Spring configuration file.

  springmvc.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns:context="http://www.springframework.org/schema/context"
         xmlns:mvc="http://www.springframework.org/schema/mvc"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/context  http://www.springframework.org/schema/context/spring-context.xsd
         http://www.springframework.org/schema/mvc  http://www.springframework.org/schema/mvc/spring-mvc.xsd ">
      <!-- componet scan -->
      <context:component-scan base-package="com.taogen.springtest" />
      <!-- view resolver -->
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="prefix" value="/" />
          <property name="suffix" value=".jsp" />
      </bean>
      <!-- annotion driven: using json on response body -->
      <mvc:annotation-driven/>
  </beans>
  ```

- Running this web application with Tomcat.

- Creating Controller Test class.

  MyControllerTest.java

  ```java
  // entry-point to start using Spring Test framework
  @RunWith(SpringJUnit4ClassRunner.class)
  // Loading spring context configuration.
  @ContextConfiguration(locations = "classpath:springmvc.xml") // @ContextConfiguration(class={ApplicationConfig.class})
  // Loading the web application context. Default path src/main/webapp. You can override by passing value argument.
  @WebAppConfiguration()
  public class MyControllerTest
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
  
          Assert.assertNotNull(servletContext);
          Assert.assertTrue(servletContext instanceof MockServletContext);
          Assert.assertNotNull(wac.getBean("myController"));
      }
  
      @Test
      public void testViewName() throws Exception
      {
          this.mockMvc.perform(get("/toIndex")).andDo(print())
                  .andExpect(view().name("index"))
                  .andExpect(forwardedUrl("/index.jsp"));
      }
      
      @Test
      public void testResponseBody() throws Exception {
          MvcResult mvcResult = this.mockMvc.perform(get("/returnJson")).andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.message").value("ok"))
                  .andExpect(jsonPath("$.user.name").value("Tom"))
                  .andReturn();
          Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
      }
      
      @Test
      public void testPostRequest() throws Exception {
          this.mockMvc.perform(post("/returnJson").param("name", "Tom1")).andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType("application/json;charset=UTF-8"))
                  .andExpect(jsonPath("$.message").value("ok"))
                  .andExpect(content().string(containsString("Tom1")));
      }
      
      /*
      All Expects
          status().isOk()
          status().isFound() //302 
          flash().attributeExists("page_error")
          view().name("index")
          forwardedUrl("/index.jsp")
          content().contentType("application/json;charset=UTF-8")
          content().string(containsString("Tom1"))
          jsonPath("$.message").value("ok")
          model().attribute("signupForm", any(SignupForm.class))
          model().attributeHasFieldErrors("signupForm", "email")
      */
  }
  ```

[`back to content`](#content)

---



--END--