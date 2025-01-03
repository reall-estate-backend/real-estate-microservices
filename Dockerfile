# Use the official Ubuntu image as the base
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Expose the application port
EXPOSE 8888

# Copy the JAR file to the container
COPY target/config-server-0.0.1-SNAPSHOT.jar app.jar

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]