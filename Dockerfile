# Multi-stage Dockerfile for diet-analysis-service (Java 11)
# Build Stage
FROM maven:3.8.5-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests -B

# Run Stage
FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/diet-analysis-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082

# JVM tuning options for cloud-native containers
ENTRYPOINT ["java", "-XX:+UseG1GC", "-XX:+UseContainerSupport", "-jar", "app.jar"]
