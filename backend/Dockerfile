FROM amazoncorretto:21-alpine-jdk
LABEL authors="bora_"

VOLUME /tmp

# Copy the jar file into the image
COPY istasyon.jar .

# Set the entry point
ENTRYPOINT ["java", "-jar", "/istasyon.jar"]