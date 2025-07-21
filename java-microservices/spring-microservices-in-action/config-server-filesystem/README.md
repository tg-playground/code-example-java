# configserver

Using a filesystem as the backend repository for the Spring Cloud Configuration Server can be **impractical** for a
cloud-based application. That’s because the development team has to set up and manage a shared filesystem that’s mounted
on all instances of the Config Server, and the Config Server integrates with different backend repositories that can be
used to host the application configuration properties.

## Accessing configuration information

```sh
# Get src/main/resources/config/myapp.properties
curl http://localhost:8888/myapp/default
# Get src/main/resources/config/myapp-dev.properties
curl http://localhost:8888/myapp/dev
# Get src/main/resources/config/myapp-prod.properties
curl http://localhost:8888/myapp/prod
```
