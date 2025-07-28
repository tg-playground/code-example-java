# Eureka Auth with Keycloak

- [Keycloak and Spring Boot: The Ultimate Guide to Implementing Single Sign-On](https://dev.to/bansikah/keycloak-and-spring-boot-the-ultimate-guide-to-implementing-single-sign-on-1af7)

## Initial Keycloak

1\. Create a realm

Manage realms > Create realm

- Realm name: `my-realm`
- Enable: On

2\. Add a client

Clients > Create client

- General settings:
    - Client type: `OpenID Connect`
    - Client ID: `my-client`
- Capability config:
    - Client Authentication: On (confidential)
    - Authorization: On
    - Authentication flow: Standard flow, Direct access grants, Service account roles
- Login settings:
    - Valid Redirect URIs: `http://localhost:8082/*`
    - Valid post logout redirect URIs: `http://localhost:8082/*`
    - Web Origins: `*`

3\. Add client roles

- USER
- ADMIN

4\. create realm roles

- my-admin
    - Associated roles -> Assign role -> Client roles -> ADMIN
- my-user
    - Associated roles -> Assign role -> Client roles -> USER

5\. Create user

User1

- General
    - Email verified: On
    - Username: `user1`
    - Email: `user1@x.com`
    - First name: `User`
    - Last name: `One`
- Credentials
    - Set password
        - Password: `123456`
        - Temporary: Off
- Role mapping
    - Assign role -> Realm roels -> my-user

User2

- General
    - Email verified: On
    - Username: `admin1`
    - Email: `admin1@x.com`
    - First name: `Admin`
    - Last name: `One`
- Credentials
    - Set password
        - Password: `123456`
        - Temporary: Off
- Role mapping
    - Assign role -> Realm roels -> my-admin

6\. Test user authentication

- URL: http://localhost:8081/realms/my-realm/protocol/openid-connect/token
- Method: POST
- Authorization: Basic
    - Username: `my-client`
    - Password: `my-client-secret`
- Content-Type: application/x-www-form-urlencoded
- Form data:
    - grant_type: password
    - username: user1
    - password: 123456

Response:

```json
{
    "access_token": "xxx",
    "expires_in": 300,
    "refresh_expires_in": 1800,
    "refresh_token": "xxx",
    "token_type": "Bearer",
    "not-before-policy": 0,
    "session_state": "xxx",
    "scope": "profile email"
}
```

Decode the JWT access_token with https://jwt.io to retrieve all the access token information

## Note

Make sure your keycloak configuration is correct, especially the client ID and secret. The client ID and secret should
match the ones you set in your Spring Boot application properties.
