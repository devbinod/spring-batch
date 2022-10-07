FROM maven:latest as builder
WORKDIR /build
COPY . .
RUN mvn clean install -DskipTests

FROM openjdk:17.0.2-jdk as deploy
MAINTAINER BINOD PANT
WORKDIR /app
COPY --from=builder /app/target/*.jar batchjob.jar
CMD ["java","-jar","batchjob.jar"]