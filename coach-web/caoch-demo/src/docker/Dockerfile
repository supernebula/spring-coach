FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=caoch-demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} caoch-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8801
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "caoch-demo-0.0.1-SNAPSHOT.jar"]