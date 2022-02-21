FROM openjdk:8-jdk-alpine
RUN mkdir /microservice
WORKDIR /microservice
ARG JAR_FILE=coach-soul-gateway-1.0.jar
ARG BOOT_FILE=bootstrap.yml
COPY ${JAR_FILE} coach-soul-gateway-1.0.jar
COPY ${BOOT_FILE} bootstrap.yml
EXPOSE 8088
ENTRYPOINT ["java","-jar", "coach-soul-gateway-1.0.jar"]