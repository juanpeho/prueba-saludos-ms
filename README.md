# Prueba Saludos

## Descripción
El propósito de este ejercicio es simular el desarrollo de una aplicación de punta a punta, ejecutando los siguientes pasos:

1. Conceptualización
2. Diseño y Planteamiento de la Arquitectura
3. Selección y Aprovicionamiento del Ecosistema Tecnológico
4. Desarrollo
5. Pruebas
6. Despliegue
7. Implementación de Integración y Entrega Continua

### Conceptualización
Se desea contar con una aplicación de saludos, la cual cuente con los dos siguientes casos de uso:

**a. Saludar**
Dado un tipo de documento, número de documento y nombre, la aplicación retorne un saludo configurado con la información entregada. A su vez, el saludo quede almacenado en una base de datos.

**b. Consultar Saludo**
Dado un tipo de documento y número de documento, la aplicación retorne el saludo almacenado que concuerde con los datos entregados. En caso de no existir registro alguno, la aplicación deberá retornar un mensaje que indique la inexistencia de este.

### Diseño y Planteamiento de la Arquitectura
Dada la simpleza del escenario, se contempla entonces un microservicio encargado del contexto Saludos, el cual contenga la implementación de los dos casos de uso requeridos e igualmente exponga estas funcionalidades en calidad de servicios API. Igualmente lo acompaña un componente de base de datos, que se encargará del repositorio físico de la información.


<img src="/imagenes/diseno-basico-componentes.png"/>


**a. Caso de Uso - Saludar**

<img src="/imagenes/cu-saludar.png"/>


**b. Caso de Uso - Consultar Saludo**

<img src="/imagenes/cu-consultar.png"/>


#### Arquitectura
El microservicio será desarrollado bajo el esquema de una arquitectura hexagonal, con un planteamiento básico de [Spring](https://www.baeldung.com/hexagonal-architecture-ddd-spring).

<img src="/imagenes/arquitectura-hexagonal.png"/>

Se generará la semilla con ayuda del [Spring Initializr](https://start.spring.io/). Los que nos posibilita el uso de [Spring Boot framework](https://spring.io/projects/spring-boot).
Adicionalmente se incluyen dependencias como Spring Data, Spring Web, Swagger, entre otras; para darle un fácil manejo a la conexión con la base de datos, exposición y documentación de APIs, etc.

Para el repositorio físico, se manejará una base de datos documental, particularmente MongoDB.


### Selección y Aprovicionamiento del Ecosistema Tecnológico

<img src="/imagenes/ecosistema-tecnologico.png"/>

Se selecciona Microsoft Azure como la nube sobre la que será desplegada la aplicación Saludos.
Se cuenta entonces con un aprovicionamiento simple, en donde contamos con los siguientes elementos:

*Azure Subscription* - Suscripción de Azure que empaqueta nuestra solución.
*rg-pruebasaludos-001* - Grupo de recursos que empaqueta todos los servicios seleccinados y configurados que compoenen nuestra solución.
*plan-pruebasaludos-001* - Plan de App Service como contenedor de la App Service, el cual es requerido para el correcto funcionamiento de la imagen Docker, desplegada en el servicio App Service, en un entorno Linux.
*app-pruebasaludos-001* - App Servicie donde estará desplegada la imagen Docker del microservicio Saludos.
*db-mongo-saludos-001* - Servicio de Cosmos DB, configurado con una base de datos documental de Mongo DB.
*crpruebasaludos-001* - Registro de contenedor, en donde se almacenarán las imágenes Docker que se vayan generando, para que posteriormente sean desplegadas directamente en el servicio App Service.


### Pruebas
Se cubrirán 3 tipos de pruebas:

**1. Pruebas unitarias**
   Se hará uso de mockito para realizar las pruebas unitarias de cada unidad de código pertinente, cubriendo todos los posibles escenarios en cada caso.

**2. Pruebas funcionales**
   Se hará uso de Postman para ejecutar funcionalmente y a demanda cada unos de los servicios del API expuesto. En el repositorio se encuentra adjunto el [proyecto postman](./Saludos.postman_collection.json), para probar los servicios tanto en entorno local como en la nube.

**3. Pruebas de carga**
   Se hará uso de JMeter para ejecutar un plan de pruebas de carga, en cada uno de los dos servicio expuestos en el API. En el repositorio se encuentra adjunto el [archivo jmeter](./saludos.jmx) con dicha configuración.


### Despliegue Local
Para el despliegue local es más fácil hacerlo desde Visual Studio Code, pues haciendo uso de la extensión de Spring Boot, este detecta la aplicación y permite correrla ágilmente.

<img src="/imagenes/vscode-springboot.png"/>

Para probar el consumo del API de la aplicación, hacer uso del [proyecto postman](./Saludos.postman_collection.json) adjunto en el repositorio. 

### Despliegue en Azure
Primero se debe contar con [Docker Desktop](https://docs.docker.com/desktop/windows/install/).

Segundo construimos el artefacto desplegable .jar con el comando gradle correspondiente, el cual dejará el artefacto en la ruta ./build/libs/

    gradle clean build test

Para desplegar se puede realizar de dos formas:

1. Por medio de Visual Studio Code y sus extensiones de Azure y Docker [link](https://docs.microsoft.com/es-es/azure/app-service/quickstart-custom-container?pivots=container-linux&tabs=dotnet). Aquí ya contamos con el Dockerfile debidamente configurado y todos los componentes en Azure prebiamente aprovicionados.
   
2. Ejecutando los siguientes pasos:

   a. Desde consola, parados en la raiz del repositorio, ejecutar los siguientes comandos:

    docker login crpruebasaludos001.azure.io

    docker tag saludos crpruebasaludos001.azure.io/saludos docker push crpruebasaludos001.azure.io/saludos

   Teniendo en cuenta obtener el usuario y la contraseña del contenedor).

    b. Desplegar manualmente el App Service desde la opción que así lo permite.

    <img src="/imagenes/app-service-configuracion-manual.png"/>

Para probar el consumo del API de la aplicación, hacer uso del [proyecto postman](./Saludos.postman_collection.json) adjunto en el repositorio.


## Swagger

Local: http://localhost:8080/pruebasaludo/swagger-ui/

Azure: https://app-pruebasaludos-001.azurewebsites.net/pruebasaludo/swagger-ui/


## Integración y Entrega Continua
Con el fin de promover ágilmente un esquema de CI/CD para la aplicación Saludos, se optó por implementar un pipeline de integración y entrega continua, haciendo uso igualmente de las capacidades de Azure, pero en su vertical de Azure DevOps. En el repositorio se encuentra adjunto el [archivo yml del pipeline](./azure-pipeline.yml) con la implementación del mismo.

<img src="/imagenes/azure-pipeline.png"/>


<img src="/imagenes/azure-pipeline-ejecuciones.png"/>


<img src="/imagenes/azure-pipeline-edicion.png"/>


Para lograr esto, se debe montar el repositorio en el cliente git de esta plataforma Azure Repos.

<img src="/imagenes/azure-repos.png"/>