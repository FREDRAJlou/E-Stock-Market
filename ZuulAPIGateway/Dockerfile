FROM openjdk:10-jre-slim

WORKDIR /app
COPY ./target/ZuulApiGateway-0.0.1-SNAPSHOT.jar /app

EXPOSE 9089

CMD ["java", "-jar", "ZuulApiGateway-0.0.1-SNAPSHOT.jar"]