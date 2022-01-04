FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/user-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 9095

CMD ["java", "-jar", "user-service-0.0.1-SNAPSHOT.jar"]