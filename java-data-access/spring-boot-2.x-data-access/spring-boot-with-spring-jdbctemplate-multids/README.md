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