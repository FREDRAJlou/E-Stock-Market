FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/stock-command-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 9096

CMD ["java", "-jar", "stock-command-service-0.0.1-SNAPSHOT.jar"]