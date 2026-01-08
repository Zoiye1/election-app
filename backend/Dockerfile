# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn package -DskipTests -B

# Stage 2: Run the application
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port (Render will set PORT env variable)
EXPOSE 8080

# Run the application
# Render sets the PORT environment variable automatically
ENTRYPOINT ["java", "-jar", "app.jar"]