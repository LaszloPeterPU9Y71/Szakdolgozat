FROM openjdk:20-jdk-alpine
ENV port 8080
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ./target/Szakdolgozat_Backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]