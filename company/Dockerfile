FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/company-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 9093

CMD ["java", "-jar", "company-service-0.0.1-SNAPSHOT.jar"]