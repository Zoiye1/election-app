# Gebruik een image met Java en Maven
FROM maven:3.9.9-eclipse-temurin-21

# Werkmap
WORKDIR /app

# Alles kopiëren
COPY pom.xml .
COPY src ./src

# Dependencies installeren en JAR bouwen
RUN mvn package -DskipTests

# Map voor H2 database
RUN mkdir /data

# Poort openzetten
EXPOSE 8080

# Start de applicatie
CMD ["mvn", "spring-boot:run"]
