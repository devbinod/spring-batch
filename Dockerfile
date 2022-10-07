#FROM gradle:jdk17 as builder
#
#WORKDIR /app
#COPY . .
#RUN ./gradlew build
#
#FROM openjdk:17.0.2-jdk as deploy
#MAINTAINER BINOD PANT
#WORKDIR /app
#COPY --from=builder /app/build/libs/dockerdemo-0.0.1-SNAPSHOT.jar springbatch.jar
#CMD ["java","-jar","springbatch.jar"]