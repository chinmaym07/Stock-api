# Stock Trade Application with JWT Authentication


> ##### DB Used: MYSQL 8
> ##### Language: JAVA 17
> #####  Framework: Spring Boot 3.4.4

## Steps to Execute

#### 1. Create Database & Tables in MYSQL

```
CREATE DATABASE tradeapplication;

CREATE TABLE user (
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE trade (
    id VARCHAR(255) PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    user_id INT NOT NULL,
    symbol VARCHAR(50) NOT NULL,
    shares INT NOT NULL,
    price DOUBLE NOT NULL,
    time_stamp DATETIME NOT NULL
);
```

#### 2. Run the Application

a. Register User

```
curl --request POST \
  --url http://localhost:8080/api/register \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.2' \
  --data '{
	"id": 3,
	"username": "chin3@test.com",
	"password": "chin3"
}'
```

b. Login User

```
curl --request POST \
  --url http://localhost:8080/api/login \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.2' \
  --data '{
	"username": "chin3@test.com",
	"password": "chin3"
}'
```

c. Copy the auth token received in Response in further api call's

d. Create New Trade

```
curl --request POST \
  --url http://localhost:8080/trades \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGluMyIsImlhdCI6MTc0NDE0NzQ5OSwiZXhwIjoxNzQ0MTQ4MDk5fQ.of-wgWpDDKEF_6Z5z7dTtfa9tCZYthRVDFHJHw6LUd0' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.2' \
  --data '{
	"type": "sell",
	"user_id": 1,
	"symbol": "ASML",
	"shares": 12,
	"price": 15,
	"timestamp": 1931822701000
}'
```

e. Fetch All Trades

```
curl --request GET \
  --url 'http://localhost:8080/trades?type=buy&user=' \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGluMyIsImlhdCI6MTc0NDE0NzQ5OSwiZXhwIjoxNzQ0MTQ4MDk5fQ.of-wgWpDDKEF_6Z5z7dTtfa9tCZYthRVDFHJHw6LUd0' \
  --header 'User-Agent: insomnia/11.0.2'
```

f. Fetch All trades By type & User

```
curl --request GET \
  --url 'http://localhost:8080/trades?type=buy&userId=1' \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGluMyIsImlhdCI6MTc0NDE0NzQ5OSwiZXhwIjoxNzQ0MTQ4MDk5fQ.of-wgWpDDKEF_6Z5z7dTtfa9tCZYthRVDFHJHw6LUd0' \
  --header 'User-Agent: insomnia/11.0.2'
```

g. Update/Delete are not allowed

```
curl --request PUT \
  --url http://localhost:8080/trades/0 \
  --header 'Authorization: Basic Y2hpbjM6Y2hpbjM=' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.2' \
  --data '{
	"type": "buy",
	"user_id": 23,
	"symbol": "ABX",
	"shares": 30,
	"price": 134,
	"timestamp": 1531522701000
}'
```

h. Refresh Token

```
curl --request POST \
  --url http://localhost:8080/refresh-token \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGluMyIsImlhdCI6MTc0NDE0OTMwMiwiZXhwIjoxNzQ0MTQ5OTAyfQ.r8U9j5wwYnjR7czZINTaPJX2yAfe6gTAO8vCClet7uw' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/11.0.2' \
  --data '{
	"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGluMyIsImlhdCI6MTc0NDE0OTMwMiwiZXhwIjoxNzQ0MTQ5OTAyfQ.r8U9j5wwYnjR7czZINTaPJX2yAfe6gTAO8vCClet7uw"
}'
```


# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.4/gradle-plugin/packaging-oci-image.html)
* [Spring Data JDBC](https://docs.spring.io/spring-boot/3.4.4/reference/data/sql.html#data.sql.jdbc)
* [Spring Session](https://docs.spring.io/spring-session/reference/)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.4/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Using Spring Data JDBC](https://github.com/spring-projects/spring-data-examples/tree/master/jdbc/basics)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

