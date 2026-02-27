FROM eclipse-temurin:21-jdk-jammy
ARG JAR_FILE=target/cursoSpringBoot-0.0.1.jar
COPY ${JAR_FILE} app_SpringBoot.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_SpringBoot.jar"]