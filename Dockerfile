FROM amazoncorretto:1.8-alpine-jdk 
MAINTAINER LlopiU
COPY target/ull-0.0.1-SNAPSHOT.jar ull-app.jar
ENTRYPOINT ["java","-jar","/ull-app.jar"]
