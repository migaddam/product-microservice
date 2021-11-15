FROM openjdk:8
EXPOSE 8081
ADD target/product-microservice.jar product-microservice.jar
ENTRYPOINT ["java","-jar","/product-microservice.jar"]