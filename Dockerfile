# Paso 1: Compilar la aplicación (Stage de construcción)
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Crear la imagen ligera para ejecutar (Stage de ejecución)
FROM eclipse-temurin:21-jdk-jammy
COPY --from=build /target/cursoSpringBoot-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]