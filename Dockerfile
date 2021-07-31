FROM adoptopenjdk/openjdk11:jdk-11.0.11_9-alpine-slim as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM adoptopenjdk/openjdk11:jre-11.0.11_9-alpine
EXPOSE 8080 8081
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]