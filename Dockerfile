FROM openjdk:latest
COPY . /usr/src/automation-framework
WORKDIR /usr/src/automation-framework
RUN mvn clean test