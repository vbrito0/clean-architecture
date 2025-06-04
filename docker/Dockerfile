FROM openjdk:21-jdk
WORKDIR app/
COPY target/clean-architecture.jar /app/clean-architecture.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/clean-architecture.jar"]