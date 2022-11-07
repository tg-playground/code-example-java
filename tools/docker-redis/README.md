# Docker Redis

## Running with images

```sh
$ docker run --name redis1 -d -p 16379:6379 \
-v redis_data:/data \
redis \
redis-server \
--save 60 1 \
--loglevel warning \
--requirepass 123456
```

- `--save 60 1`:  save a snapshot of the DB every 60 seconds if at least 1 write operation was performed

```sh
$ docker ps
$ docker logs -f redis1
$ docker exec -it redis1 redis-cli 
redis> auth 123456
redis> set name Taogen
```

## Build and Running

It's not working, I don't know why.

```dockerfile
FROM redis
COPY redis.conf /etc/redis.conf
CMD [ "redis-server", "/etc/redis.conf" ]
```

```sh
$ docker build -t redis2 .
$ docker run --name redis2 -d -p 16379:16379 \
redis2:latest \
redis-server \
--save 60 1 --loglevel warning \
--requirepass 123456
$ docker logs -f redis2
```



## References

- [redis - Docker Images](https://hub.docker.com/_/redis)
- [How to Use the Redis Docker Official Image](https://www.docker.com/blog/how-to-use-the-redis-docker-official-image/)

