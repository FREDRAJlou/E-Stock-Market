FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/eureka-service-registry-0.0.1-SNAPSHOT.jar /app

EXPOSE 9091

CMD ["java", "-jar", "eureka-service-registry-0.0.1-SNAPSHOT.jar"]