FROM maven:3.8.5-openjdk-17-slim as build

COPY . /app
WORKDIR /app

RUN mvn clean install

FROM openjdk:17-jdk-slim

COPY --from=build /app/target/landpage-*.jar /landpage.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/landpage.jar"]
