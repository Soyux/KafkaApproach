FROM eclipse-temurin:17-jdk-jammy
WORKDIR /App
COPY listener/target /App

RUN java -cp  App/listener-1.0-SNAPSHOT.jar com.laguna.app.App