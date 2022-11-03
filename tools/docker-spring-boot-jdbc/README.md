# docker-spring-boot-jdbc

Running MySQL container

```shell
$ docker pull mysql/mysql-server:8.0.31
# export MySQL to outside
$ docker run --name=mysql1 -d -p 3306:3306 \
-e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD=root123 \
--restart unless-stopped \
-v mysql_data:/var/lib/mysql \
mysql/mysql-server:8.0.31
# connect MySQL by container network
$ docker network create example-app
$ docker run --name=mysql1 -d \
-e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD=root123 \
--restart unless-stopped \
-v mysql_data:/var/lib/mysql \
--network example-app \
mysql/mysql-server:8.0.31
```

- `--name=mysql1 -d -p 3306:3306`: container name, running in background, expose port.
- `-e MYSQL_ROOT_HOST='%'`: enable remote connections by adding a user `root@'%'`. The password of the user `root@'%'` is the same with `root@localhost`.
- `-e MYSQL_ROOT_PASSWORD=root123`: set the password of user `root@localhost` to root123.
- `--restart unless-stopped`: --restart means always restart the container. your MySQL database will run without intervention after host machine reboots or Docker daemon updates. The unless-stopped policy used here won’t start the container if you manually stopped it with docker stop.
- `-v mysql_data:/var/lib/mysql`: Docker-managed volume. If the volume is not existing, it will be created automatically. Note, if you use docker volume and the root user data is initialed, you may can't set root user password by `-e MYSQL_ROOT_PASSWORD=root123`.
- `--network example-app`: Container Networks. You need create network first.

create a new MySQL user

```shell
$ docker exec -it mysql1 mysql -u root -p
# or
$ winpty docker exec -it mysql1 mysql -u root -p
```

```mysql
CREATE USER 'taogen'@'%' IDENTIFIED BY 'PASSWORD';
GRANT ALL PRIVILEGES ON *.* TO 'taogen'@'%' WITH GRANT OPTION;
# for not including WITH GRANT OPTION and targeting a specified database instead of all (*).
GRANT ALL PRIVILEGES ON *.* TO 'taogen'@'%';
FLUSH PRIVILEGES;
select host, user from mysql.user;
DROP USER 'taogen'@'%';
```

Spring boot configuration

```yaml
spring:
  datasource:
    url: jdbc:mysql://mysql1:3306/my_test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
    username: root
    password: root
    driverClassName: com.mysql.cj.jdbc.Driver
```

Note: the hostname in jdbcUrl is the mysql container name.

Package to jar

```shell
mvn clean package -DskipTests
```

Build images

```shell
# using dockerfile-maven-plugin
mvn package dockerfile:build -DskipTests
# list images
docker images
```

5. Running images

```shell
# list images
docker images
# running an image
docker run -d -p {host_port}:{container_port} {image_name}
docker run --name=spring-jdbc -d -p 8080:8080 --network example-app demo/docker-spring-boot-jdbc:0.0.1-SNAPSHOT
# view running containers
docker ps
# view process console output
docker logs <container_id>
# stop running containers
docker stop <container_id>
```

```sh
mvn clean package -DskipTests && mvn package dockerfile:build -DskipTests
docker stop spring-jdbc && docker rm spring-jdbc
docker run --name=spring-jdbc -d -p 8080:8080 --network example-app demo/docker-spring-boot-jdbc:0.0.1-SNAPSHOT
docker logs -f spring-jdbc
```

Visit http://localhost:8080

# References

- [mysql/mysql-server](https://hub.docker.com/r/mysql/mysql-server)
- [How to Use Docker for Your MySQL Database](https://earthly.dev/blog/docker-mysql/)
