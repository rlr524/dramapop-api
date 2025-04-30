FROM amazoncorretto:17-alpine-jdk

# Copy the JAR file into the container
COPY build/libs/dramapop-api-0.0.1-SNAPSHOT.jar app-v1.jar

# Expose port 8080
EXPOSE 8080

# Define app entry point
ENTRYPOINT ["java", "-jar", "app-v1.jar"]