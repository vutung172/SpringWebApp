FROM openjdk:17-jdk-alpine
WORKDIR /app
#COPY build/libs/webapp-0.0.1-SNAPSHOT.jar app.jar
#COPY build.gradle settings.gradle /app/
COPY . .
RUN ./gradlew build
EXPOSE 4567
CMD ["java", "-jar", "build/libs/webapp-0.0.1-SNAPSHOT.jar"]