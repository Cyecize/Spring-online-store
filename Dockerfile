FROM maven:3.8.6-eclipse-temurin-11-alpine

WORKDIR /application

COPY src ./src
COPY pom.xml ./pom.xml

RUN mvn clean package

WORKDIR /

CMD java -jar /application/target/*.jar

