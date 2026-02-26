FROM maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="poornimj"
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -B clean package

CMD ["java", "-jar", "target/Project_Assignment_1-1.0-SNAPSHOT.jar"]