Docker Spring Boot

1. Create project on https://start.spring.io/

Add dependencies
- Spring Web
- Lombok
- Spring Boot DevTools

2. Configuring Maven

```xml
<!-- This plugin is used to create a Docker image and publish it to Docker hub-->
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>dockerfile-maven-plugin</artifactId>
    <version>1.4.13</version>
    <configuration>
        <repository>${docker.image.prefix}/${project.artifactId}
        </repository>
        <tag>${project.version}</tag>
        <buildArgs>
            <JAR_FILE>target/${project.build.finalName}.jar
            </JAR_FILE>
        </buildArgs>
    </configuration>
    <executions>
        <execution>
            <id>default</id>
            <phase>install</phase>
            <goals>
                <goal>build</goal>
                <goal>push</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Add following configuration to spring-boot-maven-plugin

```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
        <image>
            <name>${docker.image.prefix}/${project.artifactId}:${project.version}</name>
        </image>
    </configuration>
</plugin>
```

Add maven properties

```xml
<docker.image.prefix>demo</docker.image.prefix>
```

3. Write APIs

```java
@GetMapping
public String hello() {
    return "hello" + new Date();
}
```
4. Write Dockerfile

```dockerfile
FROM openjdk:8-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

5. Build Docker Images

Package to jar

```shell
mvn clean package
```

Build images

```shell
# using dockerfile-maven-plugin
mvn package dockerfile:build
# or using BUILDPACKS
./mvnw spring-boot:build-image
mvn spring-boot:build-image
# list images
docker images
```

5. Running images

```shell
# list images
docker images
# running an image
docker run -d -p {host_port}:{container_port} {image_name}
docker run -d -p 8080:8080 demo/docker-spring-boot:0.0.1-SNAPSHOT
# view running containers
docker ps
# view process console output
docker logs <container_id>
# stop running containers
docker stop <container_id>
```

Visit http://localhost:8080

6. Write docker-compose.yml

```yaml
version: '3.8'
services:
  docker-spring-boot:
    image: demo/docker-spring-boot:0.0.1-SNAPSHOT
    ports:
      - "8080:8080"
    environment:
      - "SPRING_PROFILES_ACTIVE=dev"
    networks:
      backend:
        aliases:
          - "docker-spring-boot"
networks:
  backend:
    driver: bridge

```

7. Running with docker-compose

```shell
docker-compose up
```

Visit http://localhost:8080

8. Push an image to Docker Hub

Name your local images using one of these methods:

```shell
docker build -t <hub-user>/<repo-name>[:<tag>]
docker tag <existing-image> <hub-user>/<repo-name>[:<tag>]
docker commit <existing-container> <hub-user>/<repo-name>[:<tag>]
```

push the repository to the registry

```shell
docker push <hub-user>/<repo-name>:<tag>
```