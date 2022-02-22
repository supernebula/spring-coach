FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=coach-web-client-0.1-SNAPSHOT.jar
COPY ${JAR_FILE} coach-web-client-0.1-SNAPSHOT.jar
EXPOSE 8088
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "coach-web-client-0.1-SNAPSHOT.jar"]