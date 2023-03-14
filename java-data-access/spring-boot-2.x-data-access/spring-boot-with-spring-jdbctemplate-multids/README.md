# spring-boot-with-spring-jdbctemplate-multi-ds

```shell
# mysql 1
docker run --name=mysql1 -d -p 3306:3306 \
-e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD=root123 \
--restart unless-stopped \
-v mysql1_data:/var/lib/mysql \
mysql/mysql-server:8.0.31
# mysql 2
docker run --name=mysql2 -d -p 4306:3306 \
-e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD=root123 \
--restart unless-stopped \
-v mysql2_data:/var/lib/mysql \
mysql/mysql-server:8.0.31
```

```sql
CREATE DATABASE `my_test`;

CREATE TABLE `user` (
	id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(64) NOT NULL,
	age INT NULL
);

INSERT INTO `user` (NAME, age) VALUE ("Jack", 18), ("Tom", 21);

INSERT INTO `user` (NAME, age) VALUE ("John", 28), ("Bob", 21);
```

## JDBC Connection Pool

The default connection pool in Spring Boot 2 is HikariCP. It provides enterprise-ready features and better performance. HikariCP is a JDBC DataSource implementation that provides a connection pooling mechanism.

- If the HikariCP is present on the classpath, the Spring Boot automatically configures it.
- If the HikariCP is not found on the classpath, Spring Boot looks for the Tomcat JDBC Connection Pool. If it is on the classpath Spring Boot, pick it up.
- If both the above options are not available, Spring Boot chooses Apache Commons DBCP2 as the JDBC connection pool.

On the other hand, we can also skip the connection pool scanning algorithm that Spring Boot uses. We can explicitly specify a connection pooling datasource by adding the property spring.datasource.type in the application.properties file.

You can get connection pool name by `dataSource.getClass().getName()`. For example: 

- com.zaxxer.hikari.HikariDataSource
- org.apache.tomcat.jdbc.pool.DataSource