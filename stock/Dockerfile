FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/stock-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 9094

CMD ["java", "-jar", "stock-service-0.0.1-SNAPSHOT.jar"]