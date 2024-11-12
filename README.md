# Proyecto de Arquitectura de Microservicios para Scooters

Este proyecto está desarrollado en base a la arquitectura de microservicios, centrado en el alquiler y gestión de scooters.

## Descripción
Este repositorio contiene el código necesario para el despliegue y administración de microservicios de alquiler de scooters. Utilizamos herramientas modernas y prácticas recomendadas para garantizar escalabilidad y eficiencia en el sistema.

## Requisitos
- Docker
- Postman
- Java 11 o superior
- Maven

## Ejecución
Para ejecutar el proyecto, sigue estos pasos:

1. Clona el repositorio.
2. Configura el archivo .env con las variables de entorno necesarias.
3. Corre el siguiente comando en la terminal:
   ```bash
   docker-compose up -d
  Esto iniciará los servicios en contenedores, como la base de datos y cualquier otro microservicio que esté configurado en docker-compose.yml.
4. Una vez que Docker esté en funcionamiento, inicia la aplicación Spring Boot ejecutando: ./mvnw spring-boot:run o, si usas Maven instalado en tu sistema: mvn spring-boot:run.
5. Cada microservicio estara disponible en el puerto configurado en application.properties.

## Colección de Postman

Para probar los endpoints del sistema, puedes utilizar la colección de Postman que se encuentra en la siguiente ubicación dentro del proyecto:

src/main/resources/postman/Tpe Arquitectura Microservicios Scooters.postman_collection.json

Simplemente importa esta colección en Postman para acceder a todas las solicitudes preconfiguradas.
