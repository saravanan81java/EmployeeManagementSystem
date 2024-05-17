#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build

LABEL maintainer="Test"
LABEL org.label-schema.version="2.0"

COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/home/app/target/employee-system-api-0.0.1-SNAPSHOT.jar"]