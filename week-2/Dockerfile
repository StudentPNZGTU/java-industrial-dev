# Stage 1 - build jar
FROM maven:3.8.8-amazoncorretto-17-debian AS build
WORKDIR /app
COPY . .
#RUN mvn clean package

# Create production image for jar run
FROM openjdk:17-jdk-slim AS prod
WORKDIR /app
COPY --from=build /app/target/compose-0.1.jar /app/compose-0.1.jar

CMD ["java", "-jar", "compose-0.1.jar"]
