# Builder
FROM maven:3.8.6-eclipse-temurin-17-alpine AS builder
WORKDIR /usr/src
COPY ./ /usr/src
ENV PORT=8080
RUN mvn clean package

# Server
FROM openjdk:17
COPY --from=builder /usr/src/target/clientes-0.0.1-SNAPSHOT.jar /clientes-0.0.1-SNAPSHOT.jar
WORKDIR /
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java" ,"-jar" ,"/clientes-0.0.1-SNAPSHOT.jar"]
