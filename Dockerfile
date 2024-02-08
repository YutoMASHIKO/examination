FROM amazoncorretto:17 AS build
COPY . .
RUN ./mvnw clean package -Dmaven.test.skip=true

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/examination-0.0.1-SNAPSHOT.jar examination.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","examination.jar"]
