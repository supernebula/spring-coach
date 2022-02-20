FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=coach-business-server-0.1-SNAPSHOT.jar
COPY ${JAR_FILE} coach-business-server-0.1-SNAPSHOT.jar
COPY bootstrap.properties bootstrap.properties
EXPOSE 8088
ENTRYPOINT ["java","-jar", "coach-business-server-0.1-SNAPSHOT.jar"]