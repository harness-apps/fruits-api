# Fruits API

A simple Fruits REST API built using [Quarkus](https://quarkus.io).

- For RDBMS demo use the [main](../../tree/main) branch
- NoSQL(__MongoDB__) please switch to [mongodb](../../tree/mongodb) branch.

## Pre-requisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) for Mac/Linux/Windows
- [Drone CI CLI](https://docs.drone.io/cli/install/)
- Docker Registry e.g [Docker Hub](https://hub.docker.com) credentials
- Java 11+

## Environment Setup

Copy the `.env.example` to `.env` and update the following variables to suit your settings.

- `PLUGIN_REGISTRY` - the docker registry to use
- `PLUGIN_TAG`      - the tag to push the image to docker registry
- `PLUGIN_REPO`     - the docker registry repository
- `PLUGIN_USERNAME` - the docker Registry username
- `PLUGIN_PASSWORD` - the docker registry password

## Build the Application

```shell
drone exec --trusted --env-file=.env
```

The command will test, build and push the container image to the `$PLUGIN_REPO:$PLUGIN_TAG`.

## Run Application

```shell
docker-compose up
```

The application provides a Swagger UI that is accessible at <http://localhost:8080/q/swagger-ui> which could be used to work with the API.