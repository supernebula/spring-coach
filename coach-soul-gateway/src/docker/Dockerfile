FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=target/coach-soul-gateway-1.0.jar
COPY ${JAR_FILE} coach-soul-gateway-1.0.jar
COPY main/resources/bootstrap.properties bootstrap.properties
EXPOSE 8088
ENTRYPOINT ["java","-jar","/microservice/coach-soul-gateway-1.0.jar"]