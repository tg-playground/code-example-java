# `elasticsearch-springboot-high-level-rest-client` Project

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
- Elasticsearch v7.x

Dependencies

Required

- `spring-boot-starter-web`
- `org.elasticsearch.client:elasticsearch-rest-high-level-client v7.12.0`
  - `org.elasticsearch:elasticsearch v7.12.0`
  - `org.elasticsearch.client:elasticsearch-rest-client v7.12.0`

Optional

- `spring-boot-devtools`
- `lombok`
- `spring-boot-starter-test`

## Building Project

Generated from [Spring Initializr](https://start.spring.io/)

Configurations

- Project: Maven Project
- Language: Java
- Spring Boot: 2.4.4
- Project Metadata
  - Group: com.taogen.example.es
  - Artifact: elasticsearch-springboot-high-level-rest-client
  - Name: elasticsearch-springboot-high-level-rest-client
  - Description: Demo project for elasticsearch-springboot-high-level-rest-client
  - Package name: com.taogen.example.es.springboot.highlevelrestclient
  - Packaging: Jar
  - Java: 8
- Dependencies
  - Spring Boot DevTools
  - Lombok
  - Spring Web

### Building Project

Add dependencies

```xml
<!-- https://mvnrepository.com/artifact/org.elasticsearch.client/elasticsearch-rest-client -->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-client</artifactId>
    <version>7.12.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.elasticsearch/elasticsearch -->
<dependency>
    <groupId>org.elasticsearch</groupId>
    <artifactId>elasticsearch</artifactId>
    <version>7.12.0</version>
</dependency>

<!-- The High Level Java REST Client depends on the following artifacts and their transitive dependencies: -->
<!-- org.elasticsearch.client:elasticsearch-rest-client -->
<!-- org.elasticsearch:elasticsearch -->
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.12.0</version>
</dependency>
```
