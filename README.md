# Spring Web API 

## Technologies
- Java 8
- Spring Boot 2
- Spring Security (JSON Web Token - JWT)
- Maven
- H2 (ultimately PQSL)


### Demo user accounts
There are three user accounts present to demonstrate the different levels of access to the endpoints in
the API and the different authorization exceptions:
```
Admin - admin:admin
User - user:password
Disabled - disabled:password (this user is disabled)
```

There are three endpoints that are reasonable for the demo:
```
/auth - authentication endpoint with unrestricted access - need to pass username and password
/persons - an example endpoint that is restricted to authorized users (a valid JWT token must be present in the request header)
/protected - an example endpoint that is restricted to authorized users with the role 'ROLE_ADMIN' (a valid JWT token must be present in the request header)
/user - returns a current user (with tokens informations)
/refresh - refresh current tokens exp. time
```

### Generating password hash for new users

I'm using [bcrypt](https://en.wikipedia.org/wiki/Bcrypt) to encode passwords.

#### Inspiration 
https://www.toptal.com/java/rest-security-with-jwt-spring-security-and-java
