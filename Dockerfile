# Stage 1: Compile and build the application
FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
# Assuming your backend directory is at the root of your repo
# Adjust the copy paths to match the structure
COPY backend/pom.xml .
COPY backend/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Package the application in an Amazon Corretto runtime
FROM amazoncorretto:21-alpine-jdk
LABEL authors="bora_"
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
