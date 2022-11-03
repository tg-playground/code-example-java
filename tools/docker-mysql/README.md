Docker MySQL

### MySQL Images

https://hub.docker.com/_/mysql (the "official" image) vs https://hub.docker.com/r/mysql/mysql-server (the Oracle's image)

An additional, and nowadays pretty important difference is that the Oracle based docker images are available in an ARM64/v8 version, next to an AMD64 version. This for example allows you to run the image without virtualization on M1 macs with Apple sillicon.

So if you're running an X86 architecture, choose the one you like. If you're running on ARM, go for the Oracle images for best performance.

### Downloading a MySQL Server Docker Image

```sh
$ docker pull mysql/mysql-server:tag
```

If `:tag `is omitted, the `latest` tag is used, and the image for the latest GA version of MySQL Server is downloaded.

### Starting a MySQL Server Instance

```sh
$ docker run --name=mysql1 -d mysql/mysql-server:tag
```

```sh
$ docker stop mysql1 && docker rm mysql1

# export MySQL to outside
$ docker run --name=mysql1 -d -p 3306:3306 \
-e MYSQL_ROOT_HOST='%' -e MYSQL_ROOT_PASSWORD=root123 \
--restart unless-stopped \
-v mysql_data:/var/lib/mysql \
mysql/mysql-server:8.0.31

# connect MySQL by container network
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

The `--name` option, for supplying a custom name for your server container (`mysql1` in the example), is optional; if no container name is supplied, a random one is generated. If the Docker image of the specified name and tag has not been downloaded by an earlier `docker pull` or `docker run` command, the image is now downloaded. After download completes, initialization for the container begins, and the container appears in the list of running containers when you run the `docker ps` command.

```sh
$ docker ps
CONTAINER ID   IMAGE                       COMMAND                  CREATED         STATUS                            PORTS                       NAMES
cb55c561c67c   mysql/mysql-server:8.0.31   "/entrypoint.sh mysq…"   3 seconds ago   Up 2 seconds (health: starting)   3306/tcp, 33060-33061/tcp   mysql1
```

Once initialization is finished, the command's output is going to contain the random password generated for the root user; check the password with, for example, this command:

```sh
$ docker logs mysql1 2>&1 | grep GENERATED
GENERATED ROOT PASSWORD: Axegh3kAJyDLaRuBemecis&EShOs
```

### Connecting to MySQL Server from within the Container

Once the server is ready, you can run the `mysql` client within the MySQL Server container you just started and connect it to the MySQL Server. Use the `docker exec -it` command to start a `mysql` client inside the Docker container:

```sh
$ docker exec -it mysql1 mysql -u root -p
# or (in git bash)
$ winpty docker exec -it mysql1 mysql -u root -p
```

Enter the generated root password (see the instructions above on how to find it). Because the `MYSQL_ONETIME_PASSWORD` option is true by default.

### Update MySQL password

After you have connected a `mysql` client to the server, you must reset the server root password by issuing this statement:

```sh
ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';
```

Substitute `password` with the password of your choice. Once the password is reset, the server is ready for use.

```sh
select host, user from mysql.user;
CREATE USER 'root'@'%' IDENTIFIED BY 'PASSWORD';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
# for not including WITH GRANT OPTION and targeting a specified database instead of all (*).
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;
select host, user from mysql.user;
```

`# ERROR 1410 (42000): You are not allowed to create a user with GRANT`

Starting with MySQL 8 you no longer can (implicitly) create a user using the GRANT command. Use CREATE USER instead, followed by the GRANT statement

```sh
CREATE USER 'taogen'@'%' IDENTIFIED BY 'PASSWORD';
GRANT ALL PRIVILEGES ON *.* TO 'taogen'@'%' WITH GRANT OPTION;
# for not including WITH GRANT OPTION and targeting a specified database instead of all (*).
GRANT ALL PRIVILEGES ON *.* TO 'taogen'@'%';
FLUSH PRIVILEGES;
select host, user from mysql.user;
DROP USER 'jeffrey'@'localhost';
```

Note: 'root'@'localhost' is not the same user with 'root'@'%' 

### Stop, restart, and remove a container

```sh
docker stop <container_id>/<container_name>
docker stop mysql1

docker start <container_id>/<container_name>
docker start mysql1

docker rm <container_id>/<container_name>
docker rm mysql1
docker stop mysql1 && docker rm mysql1
```

### Usage custom MySQL Configuration

```sh
```

### Persisting Data With Volumes

While the container created above is a fully functioning MySQL server, you need to set up volumes so your data isn’t lost when the container stops. The MySQL Docker image is configured to store all its data in the `/var/lib/mysql` directory. Mounting a volume to this directory will enable persistent data storage that outlives any single container instance.

```sh
-v mysql_data:/var/lib/mysql
```

### Using Container Networks

`-p host_port:container_port`, port forwarding was used to expose the MySQL server on your host’s network. If you’ll only be connecting to MySQL from within another Docker container, such as your API server, a better approach is to create a dedicated Docker network. This improves security by limiting your database’s exposure.

```sh
docker network create example-app
```

Specify this network when starting your MySQL container:

```
docker run --name mysql -d \
    -e MYSQL_ROOT_PASSWORD=change-me \
    -v mysql:/var/lib/mysql \
    --network example-app \
    mysql:8
```

Connect another container to the same network:

```
docker run --name api-server -d \
    -p 80:80 \
    --network example-app \
    example-api-server:latest
```

Your API and MySQL containers now share a network. You can connect to MySQL from your API container by referencing the MySQL container’s hostname. This matches the container’s name by default. Here your application should connect to port 3306 on the `mysql` host.

## References

- [mysql/mysql-server](https://hub.docker.com/r/mysql/mysql-server)
- [How to Use Docker for Your MySQL Database](https://earthly.dev/blog/docker-mysql/)