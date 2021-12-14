# Fruits App Demo

A simple fruits API demo with Gloo Edge and Gitops. Check the
repository https://github.com/kameshsampath/fruits-api-gitops on how this API is used and deployed as part of your
GitOps workflow.

## Pre-requisites

- Docker Desktop for Mac/Linux/Windows
- Java 11+

## Start Database

Open a new terminal,

```shell
docker-compose up 
```

## Build the Application

### Hotspot

```shell
./mvnw clean package
```

### Native

```shell
./mvnw -Pnative clean package
```

## Run the application

## Hotspot

```shell
java -jar ./target/quarkus-app/quarkus-run.jar
```

## Native

```shell
./target/fruits-api-runner
```

## Powered-by

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .