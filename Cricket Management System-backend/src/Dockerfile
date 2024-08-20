# Use a minimal base image with OpenJDK 11
FROM eclipse-temurin:11-jdk-alpine

# Create a directory for the application at runtime
VOLUME /tmp

# Copy the built JAR file from the target directory into the container
COPY target/*.jar app.jar

# Specify the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
