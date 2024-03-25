FROM amazoncorretto:17-alpine-jdk

COPY build/libs/*.jar app.jar

EXPOSE 8092

ENTRYPOINT ["java", "-jar", "app.jar"]