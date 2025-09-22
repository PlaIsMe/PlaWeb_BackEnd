FROM openjdk:21-jdk
WORKDIR /springbootserver
EXPOSE 8080
COPY ./target/*.jar springbootserver.jar
CMD ["java", "-jar", "./springbootserver.jar"]