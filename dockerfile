# Use an official Gradle image to build the application
FROM gradle:8.4.0-jdk21 AS build
WORKDIR /app

# Copy the Gradle project files
COPY build.gradle settings.gradle /app/
COPY src /app/src

# Build the application
RUN gradle bootJar --no-daemon

# Use a lightweight JDK base image to run the application
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copy the built JAR from the build stage (using wildcard to handle version numbers)
COPY --from=build /app/build/libs/*.jar /app/

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application, dynamically pointing to the JAR file
ENTRYPOINT ["sh", "-c", "java -jar /app/*.jar"]
