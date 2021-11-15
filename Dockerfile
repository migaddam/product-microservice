FROM openjdk:8
EXPOSE 8081
ADD target/product-microservice-0.0.1-SNAPSHOT.jar product-microservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/product-microservice-0.0.1-SNAPSHOT.jar"]