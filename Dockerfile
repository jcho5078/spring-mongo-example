FROM openjdk:11
ADD target/springboot-mongo-test.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]