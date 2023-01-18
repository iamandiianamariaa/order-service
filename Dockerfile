FROM openjdk:11-jre-slim
COPY ./target/order-service-1.0-SNAPSHOT.jar order-service.jar
CMD ["java", "-Xmx256M", "-jar", "order-service.jar"]