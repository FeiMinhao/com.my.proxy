#load jdk alpine with maven build
FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
MAINTAINER Minhao Fei
# copy pom and src into build folder
COPY ./pom.xml /build/
COPY ./src /build/src/
COPY ./application.yaml /build/
# set build folder as work directory
WORKDIR /build/
# build package
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
# move jar file into app folder
COPY --from=MAVEN_BUILD /build/target/searchproxy-0.0.1-SNAPSHOT.jar /app/
COPY --from=MAVEN_BUILD /build/application.yaml /app/
EXPOSE 4041
ENTRYPOINT ["java", "-jar", "searchproxy-0.0.1-SNAPSHOT.jar", "-Dspring.config.location=./application.properties"]