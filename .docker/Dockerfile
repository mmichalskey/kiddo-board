# ---------- BUILD ----------
FROM gradle:9-jdk21 AS build
WORKDIR /app

COPY build.gradle* settings.gradle* gradle.properties* ./
COPY gradle ./gradle

RUN gradle build -x test --no-daemon || true

COPY src ./src

RUN gradle bootJar -x test --no-daemon

# ---------- RUNTIME ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -XX:MaxRAMPercentage=75.0 -jar app.jar"]

