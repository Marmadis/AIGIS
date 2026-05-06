# Этап 1: Сборка приложения
FROM gradle:8.7-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# Собираем JAR без запуска тестов, чтобы сэкономить время
RUN gradle build -x test --no-daemon

# Этап 2: Запуск приложения
FROM eclipse-temurin:21-jre-jammy
EXPOSE 8080
RUN mkdir /app
# Копируем только собранный JAR из первого этапа
COPY --from=build /home/gradle/src/build/libs/*.jar /app/application.jar
ENTRYPOINT ["java", "-jar", "/app/application.jar"]