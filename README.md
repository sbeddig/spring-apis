# spring-apis

This repository gives examples for different spring api technics to communication with other services or frontends like angular, vue or react.

**Table of Contents**

- [api-technology](#api-technology)
- [usage](#usage)
- [services](#services)
    - [simple-rest-api](#simple-rest-api)
    - [reactive-rest-api](#reactive-rest-api)
    - [graphql-api](#graphql-api)

## api-technology

- [simple-rest](simple-rest)
- [reactive-rest](reactive-rest)
- [graphql](graphql)
- [grpc](grpc) TODO

## usage

You can use [task](https://taskfile.dev/#/), to build docker images for all api services and run the applications at the end. To run task, use the following command in the root directory:

```shell
task
```

This command executes the default task from the [taskfile](Taskfile.yml).

After execution, you deployed all api services described in the next section on your local machine.

## services

### simple-rest-api

Endpoints:
```shell
GET http://localhost:8081/rest/articles
GET http://localhost:8081/rest/articles/{id}
POST http://localhost:8081/rest/articles
PUT http://localhost:8081/rest/articles/{id}
DELETE http://localhost:8081/rest/articles/{id}
```

### reactive-rest-api

Endpoints:
```shell
GET http://localhost:8082/rest/articles
GET http://localhost:8082/rest/articles/{id}
POST http://localhost:8082/rest/articles
PUT http://localhost:8082/rest/articles/{id}
DELETE http://localhost:8082/rest/articles/{id}
```

### graphql-api

Endpoints:
```shell
GET http://localhost:8083/graphiql # ui for graphql queries and mutations
POST http://localhost:8083/graphql # graphql endpoint
```