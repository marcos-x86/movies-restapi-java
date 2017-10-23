[![Build Status](https://travis-ci.org/marcos-x86/movies-restapi.png?branch=develop)](https://travis-ci.org/marcos-x86/movies-restapi)
[![codecov](https://codecov.io/gh/marcos-x86/movies-restapi-java/branch/develop/graph/badge.svg)](https://codecov.io/gh/marcos-x86/movies-restapi-java)

# movies-restapi-java
Basic Java Spring Boot Rest API implementation using MongoDB for data persistence.

### Requirements
* JDK 1.8
* Gradle 3.x
* MongoDB 3.x

### Execution steps
1) Clone the repository.
2) Edit the application.properties file according to your environment parameters (example included).
3) Open a terminal located in the root folder of the project.
4) Build the project with the following command:
Unix based systems:
```
./gradlew clean build
```
Windows:
```
gradlew.bat clean build
```

5) Run the service with the following command:
Unix based systems:
```
./gradlew bootRun
```
Windows:
```
gradlew.bat bootRun
```
6) Or Run the service with the following command:
Unix based systems:
```
java -jar build/libs/movies-restapi-java-0.1.jar
```
7) The service is up in: http://localhost:{port} ({port} is the port designed in the application.properties file)

### Endpoints information
This RestAPI has one basic endpoint that supports GET, POST, PUT and DELETE request methods.

1) GET all movies Request:
Endpoint: http://localhost:{port}/api/v1/movies

2) GET movie by Id Request:
Endpoint: http://localhost:{port}/api/v1/movies/{id}

3) POST movie Request:
Endpoint: http://localhost:{port}/api/v1/movies
Request body example:
```
{
	"title": "The Dark Knight",
	"year": "2008",
	"imDBScore": 9.0,
	"synopsis": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice."
}
```
Response body example:
```
{
	"id": "59e80f23e9d7b50a54d127cf",
	"title": "The Dark Knight",
	"year": "2008",
	"imDBScore": 9.0,
	"synopsis": "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham, the Dark Knight must accept one of the greatest psychological and physical tests of his ability to fight injustice."
}
```

4) PUT movie Request:
Endpoint: http://localhost:{port}/api/v1/movies/{id}
Request body example:
```
{
	"title": "The Dark Knight",
	"year": "2008",
	"imDBScore": 9.5,
	"synopsis": "When the menace known as the Joker emerges from his mysterious past."
}
```
Request body example:
```
{
	"id": "59e80f23e9d7b50a54d127cf",
	"title": "The Dark Knight",
	"year": "2008",
	"imDBScore": 9.5,
	"synopsis": "When the menace known as the Joker emerges from his mysterious past."
}
```

5) DELETE movie Request:
Endpoint: http://localhost:{port}/api/v1/movies/{id}
