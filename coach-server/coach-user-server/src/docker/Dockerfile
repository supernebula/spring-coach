FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=coach-user-server-0.1-SNAPSHOT.jar
ARG BOOT_FILE=bootstrap.properties
COPY ${JAR_FILE} coach-user-server-0.1-SNAPSHOT.jar
COPY ${BOOT_FILE} bootstrap.properties
EXPOSE 8088
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "coach-user-server-0.1-SNAPSHOT.jar"]