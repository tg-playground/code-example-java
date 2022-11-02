# Docker-HTML

Dockerfile

```dockerfile
FROM nginx
COPY . /usr/share/nginx/html
```

Building and running

```sh
docker build -t my-html-app .
docker run -p 80:80 my-html-app
```

Visit http://localhost
