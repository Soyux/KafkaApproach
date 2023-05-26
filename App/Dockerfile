FROM eclipse-temurin:17-jdk-jammy
WORKDIR /App
COPY listener/target /App

CMD ["java", "-jar","-cp","/App/listener-1.0-SNAPSHOT.jar com.laguna.App"]
