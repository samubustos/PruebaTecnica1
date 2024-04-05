FROM eclipse-temurin:17
COPY target/prueba-0.0.1-SNAPSHOT.war service.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "service.war"]