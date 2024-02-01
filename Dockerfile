FROM openjdk:17-jdk-slim
COPY "./target/microservice-0-api-gateway-0.0.1-SNAPSHOT.jar" "app.jar"
ENTRYPOINT ["java", "-jar", "app.jar"]