FROM openjdk:8-alpine
LABEL maintainer="dounia.hm@hotmail.com" 
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]