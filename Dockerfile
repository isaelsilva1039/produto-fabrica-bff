# Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/api-java-core-0.0.1-SNAPSHOT.jar app.jar
ENV PORT 8086
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]