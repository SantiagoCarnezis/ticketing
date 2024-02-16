FROM openjdk:17-alpine
WORKDIR application
COPY zazoo.jar app.jar
CMD ["java", "-jar", "app.jar"]