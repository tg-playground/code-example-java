# spring-boot-with-spring-data-elasticsearch

Elasticsearch data access

- Java REST client (Deprecated in 7.15.0)
  - ✅[Java Low Level REST client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-low.html) (org.elasticsearch.client:elasticsearch-rest-client) (Since 5.6.x)
  - ❌[Java High Level REST Client](https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.9/java-rest-high.html)) (org.elasticsearch.client:elasticsearch-rest-high-level-client) (5.6.x~7.17.x)
- ❌[Java Transport Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/index.html) (Deprecated in 7.0.0) (org.elasticsearch.client:transport) (5.0.x~7.17.x)
- ✅[Java API Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api-client/current/index.html) (co.elastic.clients:elasticsearch-java:x.x.x) (Since 7.15.x) Dependency: org.elasticsearch.client:elasticsearch-rest-client
- ✅Spring data elasticsearch
  - repository
  - restTemplate

Functions

- CRUD for document
- Statistics Query for document
- // CRUD for index. (if the index do not exist, it will create automatically)

Implementation methods:

- high level client
- low level client
- rest-template
- repository

## References

- [Elasticsearch in Java Spring Boot: Starter Pack](https://hackernoon.com/elasticsearch-in-java-spring-boot-starter-pack-3kx330h)
- [Spring Data Elasticsearch - Reference Documentation](https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#repositories.create-instances.java-config)

