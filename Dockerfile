FROM eclipse-temurin:11-jre-jammy
WORKDIR /app
ADD target/taskkrista-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]