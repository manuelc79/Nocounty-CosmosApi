FROM amazoncorretto:11-alpine-jdk
LABEL maintainer="Cosmos-API"

COPY target/Cosmos-API-0.0.1-SNAPSHOT.jar Cosmos-API.jar

ENTRYPOINT ["java", "-jar", "Cosmos-API.jar"]
