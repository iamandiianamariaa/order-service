FROM openjdk:11
COPY ./target/order-service-1.0-SNAPSHOT.jar order-service.jar
CMD ["java", "-jar", "order-service.jar"]