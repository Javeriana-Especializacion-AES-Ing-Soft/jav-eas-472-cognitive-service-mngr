FROM openjdk:11
ADD target/jav-eas-472-cognitive-service-mngr-0.0.1-SNAPSHOT.jar jav-eas-472-cognitive-service-mngr-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "jav-eas-472-cognitive-service-mngr-0.0.1-SNAPSHOT.jar"]

