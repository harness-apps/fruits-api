# Fruits API

A simple Fruits REST API built using [Quarkus](https://quarkus.io).

- For RDBMS demo use the [main](../../tree/main) branch
- NoSQL(__MongoDB__) please switch to [mongodb](../../tree/mongodb) branch.

## Pre-requisites

- [Docker Desktop](https://www.docker.com/products/docker-desktop/) for Mac/Linux/Windows
- [Drone CI CLI](https://docs.drone.io/cli/install/)
- Docker Registry e.g [Docker Hub](https://hub.docker.com) credentials
- Java 11+
- [MongoDB Atlas](https://www.mongodb.com/atlas) account

## Create Database and Collection

Ensure you have database called `demodb` created on your __MongoDB Atlas__ account with a collection named `fruits`.

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

>__TIP:__ If you are using nexus then enable the `maven_mirror_url` on the drone java maven plugin to point to maven mirror url
> e.g. for a local nexus running your Docker for Desktop
>
> ```shell
> drone exec --trusted --env-file=.env --network=<your nexus network>
> ```

The command will test, build and push the container image to the `$PLUGIN_REPO:$PLUGIN_TAG`.

## Run Application

### Connecting to Local MongoDB

```shell
docker-compose up 
```

### Connecting to MongoDB Atlas

Update the `QUARKUS_MONGODB_CONNECTION_STRING` in the .env file to point to your MongoDB Atlas and run,

```shell
docker-compose up fruits-api
```

## Trying API

The application provides a Swagger UI that is accessible at <http://localhost:8080/q/swagger-ui/>, where you can try all the available API methods.
