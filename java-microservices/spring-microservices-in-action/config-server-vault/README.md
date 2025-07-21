# configserver vault

## configserver

1\. Start Vault server

```sh
docker run -d --name=dev-vault -p 8200:8200 -e 'VAULT_DEV_ROOT_TOKEN_ID=myroot' --cap-add=IPC_LOCK hashicorp/vault:1.20
```

or use Docker Compose

```yaml
services:
  vault:
    image: 'hashicorp/vault:1.20'
    ports:
      - ${VAULT_PORT}:8200
    cap_add:
      - IPC_LOCK
    environment:
      - 'VAULT_DEV_ROOT_TOKEN_ID=${VAULT_DEV_ROOT_TOKEN_ID}'
```

```shell
docker compose up -d
```

2\. Initialize Vault

Open the Vault UI at http://localhost:8200 and log in with the root token `myroot`.

Add a Secret Engine

Add Secret

- Path: `{application}/{profile}`
- Secret data:
    - key: value
    - key: value

3\. application.yml

```yaml
spring:
  application:
    name: config-server-vault
  config:
    import: optional:file:.env[.properties],optional:file:../.env[.properties]
  profiles:
    active: vault
  cloud:
    config:
      server:
        vault:
          host: localhost
          port: ${VAULT_PORT}
          kv-version: 2
          authentication: token
          token: ${VAULT_DEV_ROOT_TOKEN_ID}
          profile-separator: /
          scheme: http
          backend: myapp
server:
  port: 8888
```

## Accessing configuration information

```sh
# Get src/main/resources/config/myapp.properties
curl http://localhost:8888/myapp/default
# Get src/main/resources/config/myapp-dev.properties
curl http://localhost:8888/myapp/dev
# Get src/main/resources/config/myapp-prod.properties
curl http://localhost:8888/myapp/prod
```
