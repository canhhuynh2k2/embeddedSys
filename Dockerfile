
FROM openjdk:17

WORKDIR /app
#
COPY target/quiz-0.0.1-SNAPSHOT.jar quizservice.jar

EXPOSE 8085
CMD ["java", "-jar", "quizservice.jar"]
#FROM maven:3-openjdk-17 AS build
#COPY . .
#RUN mvn clean package -DskipTests
#
#FROM openjdk:17.0.1-jdk-slim
#COPY --from=build /target/quiz-0.0.1-SNAPSHOT.jar quizservice.jar
#EXPOSE 8085
#ENTRYPOINT ["java", "-jar", "quizservice.jar"]