FROM gradle:8-jdk17 AS build

WORKDIR /app

# Copiar archivos de configuración
COPY build.gradle settings.gradle ./
COPY src ./src

# Generar código OpenAPI y compilar
RUN gradle clean openApiGenerate build -x test --no-daemon

RUN gradle clean build -x test

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]