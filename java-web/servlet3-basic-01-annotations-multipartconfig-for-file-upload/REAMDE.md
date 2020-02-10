# servlet3-basic-01-annotations-multipartconfig-for-file-upload Project

Content

- Environments
- Building Project
- Implementation
- Test
- References

## Environment

Software

- JDK 1.8
- Maven
- Tomcat7 Maven Plugin

Dependencies

- javax.servlet-api 3.0.1

## Building Project

### Step 1: Generating Maven Project

Using Maven Template to Generate Project Structure and Artifacts

```shell
$ mvn archetype:generate -DgroupId=com.taogen.example -DartifactId=servlet3-basic-01-annotations-multipartconfig-for-file-upload -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

### Step 2: Configuring Maven Project `pom.xml`

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
    <version>3.0.1</version>
    <!-- provided: indicates you expect the JDK or a container to provide the dependency at runtime. set the dependency on the Servlet API and related Java EE APIs to scope provided because the web container provides those classes. -->
    <scope>provided</scope>
</dependency>
```

Add Maven Plugins

``` xml
<project>
  ...
  <build>
    <plugins>
        <!-- maven compile -->
        <plugin>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.5.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
        
        <!-- maven package war -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-war-plugin</artifactId>
            <version>3.2.3</version>
            <configuration>
              <webResources>
                <resource>
                  <!-- this is relative to the pom.xml directory -->
                  <directory>src/main/resources</directory>
                </resource>
              </webResources>
            </configuration>
      </plugin>
 
        <!-- Tomcat plugin-->
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <port>9000</port>   //Configure port number
                <path>/servlet3-basic-01-annotations-multipartconfig-for-file-upload</path>   //Configure application root URL
            </configuration>
        </plugin>
    </plugins>
  </build>
  ...
</project>
```

### Step 3: Add project file structures  

Add source root `src/main/java`

Add package path `com/taogen/example`



## Implementation

### Step 1: Write Servlets

Add `FileUploadByMultipartConfigServlet.java`

```java
package com.taogen.example.servlet.annotations;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/fileUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 100 // 100MB
)
public class FileUploadByMultipartConfigServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String uploadPath = getUploadPath(req);
        ensureUploadDirectoryExist(uploadPath);
        writeFileFromParts(req.getParts(), uploadPath, out);
    }

    private String getUploadPath(HttpServletRequest req) {
        String applicationPath = req.getServletContext().getRealPath("");
        String uploadPath = new StringBuilder().append(applicationPath).append(File.separator).append(UPLOAD_DIR)
                .toString();
        return uploadPath;
    }

    private void ensureUploadDirectoryExist(String uploadPath) {
        File uploadDirectory = new File(uploadPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }
    }

    private void writeFileFromParts(Collection<Part> parts, String uploadPath, PrintWriter out) {
        List<String> uploadFileNames = new ArrayList<>();
        for (Part part : parts) {
            System.out.println("part ContentType: " + part.getContentType());
            if (part.getContentType() != null) {
                String fileName = getFileNameByPart(part);
                try {
                    part.write(new StringBuilder(uploadPath).append(File.separator).append(fileName).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                uploadFileNames.add(fileName);
            } else {
                String paramValue = null;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream()));) {
                    paramValue = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                StringBuilder result = new StringBuilder("Param ").append(part.getName()).append(" is ").append(paramValue);
                System.out.println(result.toString());
            }
        }
        if (!uploadFileNames.isEmpty()) {
            for (String fileName : uploadFileNames) {
                out.println(new StringBuilder("File ").append(fileName).append("has uploaded successfully!"));
            }
        } else {
            out.println("No files upload!");
        }

    }

    private String getFileNameByPart(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition of header: " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}
```



## Test

### Running and Visiting Project

Running Maven Project by Maven Tomcat 7 Plugin

```shell
$ mvn tomcat7:run
# or
$ mvn tomcat7:run-war
```

Visiting Index Page

```shell
$ curl http://localhost:{your_port}/{your_context}
```

Visiting `HelloWorldServlet` 

```shell
$ curl http://localhost:{your_port}/{your_context}/fileUpload
```



## References

[1] [Servlet 3 File Upload – @MultipartConfig, Part](https://www.journaldev.com/2122/servlet-3-file-upload-multipartconfig-part)