
FROM --platform=amd64 openjdk:17.0.2-oraclelinux8

WORKDIR /app
#
COPY target/quiz-0.0.1-SNAPSHOT.jar quizservice.jar

EXPOSE 8085
CMD ["java", "-jar", "quizservice.jar"]