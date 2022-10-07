FROM maven:3.8.6-amazoncorretto-17 AS builder
WORKDIR /app
COPY . .
RUN mvn install

FROM openjdk:17.0.2-jdk as deploy
MAINTAINER BINOD PANT
WORKDIR /app
COPY --from=builder /app/target/*.jar batchjob.jar
CMD ["java","-jar","batchjob.jar"]