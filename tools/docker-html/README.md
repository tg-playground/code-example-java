# Docker-HTML

Dockerfile

```dockerfile
FROM nginx
COPY . /usr/share/nginx/html
```

Building and running

```sh
docker build -t docker-html .
docker run -d -p 80:80 docker-html
docker ps
docker logs -f <container_id>
docker stop <container_id>
```

Visit http://localhost
