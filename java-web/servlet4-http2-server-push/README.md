# Servlet4 HTTP2 Server Push Project

Content

- Requirements
- Steps
- References

## Requirements

Required

- JDK 1.8+
- Maven
- **Tomcat 9+**

## Steps

### Step 1: Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet4-http2-server-push -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step2: Configuring `pom.xml`

Set Maven Project Properties

```xml
<properties>
    <maven.compiler.target>1.8</maven.compiler.target>
    <maven.compiler.source>1.8</maven.compiler.source>
</properties>
```

Add Maven Dependencies

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
    <scope>provided</scope>
</dependency>
```

### Step 3: Write ServerPushServlet

Add source root `src/main/java`

Add package path `com/taogen/example`

Add `ServerPushServlet.java` 

```java
package com.taogen.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/serverPush")
public class ServerPushServlet extends HttpServlet {
    private static final long serialVersionUID = -3462096228274971485L;
    
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        PushBuilder pushBuilder = req.newPushBuilder(); 
        pushBuilder.path("images/kodedu-logo.png")
                .addHeader("content-type", "image/png")
                .push(); 
        try(PrintWriter respWriter = resp.getWriter();){
            respWriter.write("<html>" +
                    "<img src='images/kodedu-logo.png'>" + 
                    "</html>");
        }
    }
}

```

**Step 4: Configuring Tomcat 8 for support HTTP/2**

Using **OpenSSL** to generate root SSL certificate file and domain `localhost` SSL certificate file. 

or, Using  `java/bin/keytool` to generate keystore file.

Edit `conf/server.xml` set HTTP/2

```xml
<Connector protocol="org.apache.coyote.http11.Http11AprProtocol"
    port="8443" maxThreads="200"
    scheme="https" secure="true" SSLEnabled="true"
    SSLCertificateFile="C:\certificate\localhost.crt"
    SSLCertificateKeyFile="C:\certificate\localhost.key"
    SSLVerifyClient="optional" SSLProtocol="TLSv1+TLSv1.1+TLSv1.2">
    <UpgradeProtocol className="org.apache.coyote.http2.Http2Protocol" />
</Connector>
```

Optional: Enable HTTPS on localhost

### Step 5: Run Tomcat

Running Tomcat 9

Visit Index Page by `https://localhost:8443/{your_context}` 

Visit HelloWorld Servlet by `https://localhost:8443/{your_context}/serverPush`



## References

[1] [Everything new with Servlet 4.0 (JSR 369)](http://alibassam.com/everything-new-servlet-4-0/)

[2] [First Look at HTTP/2 Server Push in Java Servlet 4.0 Specification - DZone](https://dzone.com/articles/first-look-at-http2-server-push-in-java-servlet-40-1)

[3] [Get started with Servlet 4.0 - IBM Developer](https://developer.ibm.com/tutorials/j-javaee8-servlet4/)

[4] [SSL/TLS Configuration HOW-TO - Apache Tomat8](https://tomcat.apache.org/tomcat-8.0-doc/ssl-howto.html)

[5] [Configure Tomcat support HTTP/2](https://huongdanjava.com/configure-tomcat-support-http-2.html)

[6] [How to Implement HTTP2 in Tomcat?](https://geekflare.com/tomcat-http2/)

[7] [How to get HTTPS working on your local development environment in 5 minutes](https://www.freecodecamp.org/news/how-to-get-https-working-on-your-local-development-environment-in-5-minutes-7af615770eec/)