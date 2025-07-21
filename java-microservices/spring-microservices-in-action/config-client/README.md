# Config Client

## Injecting properties from config server

Injecting config server properties like injecting properties from current project `application.properties`:

```
@Value("${greetings}")
private String greetings;
```

## Testing APIs

Visiting http://localhost:8080/greeting will return the greeting message from the config server.
