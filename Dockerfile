FROM node:21 AS builder1

WORKDIR /app

COPY frontend/*.json .
COPY frontend/src src

RUN npm install -g @angular/cli

RUN npm ci && ng build

FROM maven:3-eclipse-temurin-21 AS builder2

WORKDIR /app

COPY backend/.mvn .mvn
COPY backend/src src
COPY backend/pom.xml .
COPY backend/mvnw .
COPY backend/mvnw.cmd .
COPY --from=builder1 /app/dist/frontend/browser/ src/main/resources/static/

RUN mvn package -Dmaven.test.skip=true

FROM openjdk:21-jdk-slim

WORKDIR /apps

COPY --from=builder2 /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
ENV SPRING_REDIS_HOST=localhost SPRING_REDIS_PORT=1234
ENV SPRING_REDIS_DATABASE=0
ENV SPRING_REDIS_USERNAME=default SPRING_REDIS_PASSWORD=abc123

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar