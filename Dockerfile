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

ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG PRIVATE_KEY
ARG GOOGLE_MAPS_API_KEY
ARG EMAIL_USERNAME
ARG EMAIL_PASSWORD

ENV MYSQL_USER=$MYSQL_USER
ENV MYSQL_PASSWORD=$MYSQL_PASSWORD
ENV PRIVATE_KEY=$PRIVATE_KEY
ENV GOOGLE_MAPS_API_KEY=$GOOGLE_MAPS_API_KEY
ENV EMAIL_USERNAME=$EMAIL_USERNAME
ENV EMAIL_PASSWORD=$EMAIL_PASSWORD

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
