# Step 1: Build the application using Maven and Temurin JDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application using a lightweight Temurin JRE image
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/StudentManagementSystem-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]