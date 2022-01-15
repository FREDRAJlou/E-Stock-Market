FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/company-command-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 9097

CMD ["java", "-jar", "company-command-service-0.0.1-SNAPSHOT.jar"]