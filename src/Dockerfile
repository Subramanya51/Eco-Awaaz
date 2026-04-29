# Use Java 17 runtime
FROM eclipse-temurin:17-jre-alpine

# Set working directory inside container
WORKDIR /app

# Copy built jar into container
COPY target/Eco-Awaaz-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render will override with PORT env)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]