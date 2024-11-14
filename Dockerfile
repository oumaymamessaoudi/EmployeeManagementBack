# Define Nexus parameters
# Use JDK 17 base image

# Use a base image with JDK 17/ smaller version of the full JDK image
FROM openjdk:17-jdk-slim

LABEL authors="ouma"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file downloaded from Nexus
COPY target/Management-0.0.2.jar app.jar

# Expose the port your application will run on
EXPOSE 8083

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]