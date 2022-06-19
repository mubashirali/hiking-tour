FROM openjdk:8
ADD target/hiking-tour-0.0.1-SNAPSHOT.jar hiking-tour-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","hiking-tour-0.0.1-SNAPSHOT.jar"]
