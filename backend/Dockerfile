FROM eclipse-temurin:21-jre-noble

WORKDIR /supermarket-stock

COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]