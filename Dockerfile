FROM ubuntu:latest

RUN DEBIAN_FRONTEND="noninteractive" TZ="Europe/London" apt-get update && apt-get -y install tzdata
RUN apt-get update && apt-get -y install \
 openjdk-17-jdk-headless \
 maven

EXPOSE 8080

COPY . ./library-monolith

WORKDIR ./library-monolith

RUN mvn clean package

CMD ["java","-jar","./target/library-monolith-0.0.1-SNAPSHOT.jar"]

