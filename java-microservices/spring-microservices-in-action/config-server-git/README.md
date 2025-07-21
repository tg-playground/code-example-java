# configserver

By using Git, you can get all the benefits of putting your configuration management properties under source control and
provide an easy mechanism to integrate the deployment of your property configuration files in your build and deployment
pipeline.

## GIT HTTPS

1\. Generating a personal access token (PAT)

New fine-grained personal access token

- Only select repositories: spring-cloud-config
- Permissions:
    - Contents: Read-only

Token (classic)

- Permissions:
    - repo: Full control of private repositories

2\. Add the PAT to your environment variables

The `.env` must in the root directory of the project.

3\. application.yml

```yaml
spring:
  config:
    import: optional:file:.env[.properties],optional:file:../.env[.properties]
  cloud:
    config:
      server:
        git:
          cloneOnStart: true
          timeout: 10
          uri: "https://github.com/tg-playground/spring-cloud-config.git"
          defaultLabel: main
          username: ${username}
          password: ${personalAccessToken}
```

## GIT SSH

By default, the JGit library used by Spring Cloud Config Server uses SSH configuration files such as ~/.ssh/known_hosts
and /etc/ssh/ssh_config when connecting to Git repositories by using an SSH URI. In cloud environments such as Cloud
Foundry, the local filesystem may be ephemeral or not easily accessible. For those cases, SSH configuration can be set
by using Java properties. In order to activate property-based SSH configuration, the
spring.cloud.config.server.git.ignoreLocalSshSettings property must be set to true

JGit requires RSA keys in PEM format.

```shell
ssh-keygen -m PEM -t rsa -b 4096
```

## GIT Local

1\. Create a local Git repository

```shell
mkdir -p ~/config/spring-cloud-configuration
cd ~/config/spring-cloud-configuration
git init .
echo greetings=Hello, World! > myapp.properties
echo greetings=Hello, Dev! > myapp-dev.properties
echo greetings=Hello, Prod! > myapp-prod.properties
git add .
git commit -m "Initial commit"
```

2\. application.yml

```yaml
spring:
  cloud:
    config:
      server:
        git:
          # On Linux, file://${user.home}/config-repo
          # On Windows, you need an extra "/" in the file URL if it is absolute with a drive prefix (for example,file:///${user.home}/config-repo, file:///D:/config/config-repo).
          uri: file://Users/taogen/config/spring-cloud-configuration
          defaultLabel: main
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
