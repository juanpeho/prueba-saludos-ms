FROM openjdk:11

COPY build/libs/prueba-saludos-ms-*.jar prueba-saludos-ms-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/prueba-saludos-ms-0.0.1-SNAPSHOT.jar"]