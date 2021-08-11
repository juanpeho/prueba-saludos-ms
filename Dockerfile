FROM openjdk:11

COPY applications/app-service/build/libs/prueba-saludos-ms-*-all.jar prueba-saludos-ms.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/prueba-saludos-ms.jar"]