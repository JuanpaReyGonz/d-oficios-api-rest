FROM amazoncorretto:17.0.11-al2023-headless

WORKDIR /app

COPY ./target/api-rest-v240526.2100.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "api-rest-v240526.2100.jar"]