# Fruits API

A simple Fruits REST API built using [Quarkus](https://quarkus.io). For RDBMS demo use the `main` branch. If you want to use NoSQL like __MongoDB__ please switch to `mongodb` branch.

## Pre-requisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) for Mac/Linux/Windows
- [Drone CI CLI](https://docs.drone.io/cli/install/)
- Docker Registry e.g [Docker Hub](https://hub.docker.com) credentials
- Java 11+

## Environment Setup

The build uses following environment variables,

- `QUARKUS_MONGODB_CONNECTION_STRING` - the connection string to MongoDB Atlas
- `PLUGIN_TAG` - the tag to push the image to Docker Registry
- `PLUGIN_REPO` - the docker registry repository

Copy the `.envrc.example` to `.envrc.local` and update the variables to suit your settings.

Copy the `secrets.example` to `secrets` and update the values,

- `image_registry_username` to your Docker Registry username
- `image_registry_password` to your Docker Registry user password

## Build the Application

```shell
drone exec --trusted --env-file=.envrc.local --secret-file=secrets
```

The command will test, build and push the container image to the `$PLUGIN_REPO:$PLUGIN_TAG`.

## Create Database

```shell
docker-compose up
```

## Run Application

```shell
docker run -p 8080:8080 \
  --env-file=.envrc.local \
  "$PLUGIN_REPO:$PLUGIN_TAG"
```

The application provides a Swagger UI that is accessible at <http://localhost:8080/q/swagger-ui>