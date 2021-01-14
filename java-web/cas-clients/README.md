# CAS Clients

## CAS Client with Spring Boot

1.Add dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>net.unicon.cas</groupId>
    <artifactId>cas-client-autoconfig-support</artifactId>
</dependency>
```

2.Add Spring Boot configuration

`application.yml`

```yaml
server:
  port: 7777
  ssl:
    key-store: classpath:cert/thekeystore
    key-store-password: changeit
cas:
  server-url-prefix: https://localhost:8443/cas
  server-login-url: https://localhost:8443/cas/login
  client-host-url: https://localhost:7777
  validation-type: cas3
app:
  cas:
    server-logout-url: https://localhost:8443/cas/logout
```

3.Generating self-signed certificate by Java keytool

Generating keystore and put it into `src/main/resources/cert` 

```
keytool -genkey -keyalg RSA -alias thekeystore -keystore thekeystore -storepass changeit -validity 360 -keysize 2048
```

Input your information

- Your first and last name: input your domain, eg: localhost or xxx.com
- The name of organization unit: input your organization unit, eg: Web Development
- The name of organization: input your organization, eg: localhost, Inc.
- The name of your city: your city name, e.g. Nanjing
- The name of your state or province: your province name, e.g Jiangsu
- Two-letter country code: your country code, e.g. CN
- Confirm your input information: yes
- key password for keystore: enter (set key password same with keystore password)

import certificate from generated keystore to JDK trusted keystore 

```
keytool -importkeystore -srckeystore thekeystore -destkeystore ${your JAVA_HOME}/jre/lib/security/cacerts
```

The default password for the source and destination keystore is `changeit`. 

On Unix systems, we may have to run this command with admin (sudo) privilege. After importing, we should restart all instances of Java that's running or restart the system.

4.Enable CAS Client

`xxxApplication.java`

```java
@EnableCasClient
```

The `@EnableCasClient` annotation will automatically register the current CAS client to your CAS server.

5.Write controllers

6.Visit your Web API to test single sign on of CAS

Visiting https://localhost:7777, then the page will redirect to CAS server login page. Input username and password from database or others to authentication. If you login successfully, the page will redirect to the CAS client page before you visited.


### CAS Server configurations

Auto register CAS clients

```yaml
cas.serviceRegistry.initFromJson=true
```

## CAS Client with Spring Boot and Spring Security

### 1.Build Spring Security Application

Add dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-cas</artifactId>
        <version>5.3.0.RELEASE</version>
    </dependency>
</dependencies>
```

Add Spring Boot configuration

```yaml
server:
  port: 9999
  ssl:
    key-store: classpath:cert/thekeystore
    key-store-password: changeit
app:
  cas:
    server-logout-url: https://localhost:8443/cas/logout
    server-url-prefix: https://localhost:8443/cas
    server-login-url: https://localhost:8443/cas/login
    client-host-url: https://localhost:9999
```

Generating self-signed certificate by Java keytool

### 2.Write controllers

### 3.Visit your Web API to test spring security

Visit any web API such as https://localhost:9999, then the page will redirect login page of spring security

Input username and password to authentication

- username: user
- password: find password from the application running console print information. e.g. `Using generated security password: d4b9c835-76b9-4dd5-b0ee-ec640a50969b`

After your login successfully, the page will redirect to your before visited web API URL.

visit https://localhost:9999/logout to logout spring security.

### 4.Add CAS client configurations

`CasSecuredApplication.java`

```java
@Configuration
public class CasSecuredApplication {

    private Logger logger = LogManager.getLogger();

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService("https://localhost:9999");
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    @Primary
    public AuthenticationEntryPoint authenticationEntryPoint(
            ServiceProperties sP) {

        CasAuthenticationEntryPoint entryPoint
                = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl("https://localhost:8443/cas/login");
        entryPoint.setServiceProperties(sP);
        return entryPoint;
    }

    @Bean
    public TicketValidator ticketValidator() {
        return new Cas30ServiceTicketValidator(
                "https://localhost:8443/cas");
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider() {

        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(ticketValidator());
        provider.setUserDetailsService(
                s -> new User("username", "password", true, true, true, true,
                        AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
        provider.setKey("CAS_PROVIDER_LOCALHOST_9000");
        return provider;
    }
}
```

`WebSecurityConfig.java`

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .regexMatchers("/cassecured.*", "/")
                .authenticated()
                .and()
                .authorizeRequests()
                .regexMatchers("/")
                .permitAll()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(
                Arrays.asList(authenticationProvider));
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties sP)
            throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(sP);
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
```

### 5.Register CAS client to CAS server

Spring Security CAS can't use auto register. It needs to register in CAS services.
`src/main/resources/etc/cas/services/casSecuredApp-9999.json`

```json
{
  "@class" : "org.apereo.cas.services.RegexRegisteredService",
  "serviceId" : "https://localhost:9999",
  "name" : "casSecuredApp-9999",
  "id" : 9999,
  "logoutType" : "BACK_CHANNEL",
  "logoutUrl" : "http://localhost:9999/logout"
}
```



### 6.Visit the entry point URL to test single sign on of CAS

Visiting https://localhost:9999, then the page will redirect to CAS server login page. Input your username and password from database or others to login. if you login successfully, the page will redirect to CAS client URL before you visited.



## References

CAS client With Spring Boot

- [springboot 集成CAS 实现单点登录](https://cloud.tencent.com/developer/article/1649349)

CAS clients with Spring Boot and Spring Security

- [Single sign-on in CAS client setup with spring security - Medium](https://medium.com/@venkateshpnk22/single-sign-on-in-cas-client-setup-with-spring-security-b51a7e70294d)
- [CAS SSO With Spring Security - Baeldung](https://www.baeldung.com/spring-security-cas-sso)
