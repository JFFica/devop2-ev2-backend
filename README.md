Innovatech Backend
Descripción

Este repositorio contiene el servicio Backend del proyecto Innovatech Chile, desarrollado con Spring Boot y desplegado en AWS ECS Fargate como parte de la Evaluación Parcial N°3 de la asignatura Introducción a Herramientas DevOps.

El backend expone un endpoint de validación que permite comprobar que el servicio está activo y disponible desde la nube.

Tecnologías utilizadas
Java
Spring Boot
Maven Wrapper
Docker
AWS ECR
AWS ECS Fargate
AWS CloudWatch
GitHub Actions
AWS Service Auto Scaling
Endpoint principal

El backend cuenta con el siguiente endpoint de prueba:

GET /health

Respuesta esperada:

{
  "status": "OK"
}

Este endpoint fue utilizado para validar el correcto funcionamiento del servicio tanto en ambiente local como en AWS ECS.

Ejecución local

Para ejecutar el backend localmente:

./mvnw spring-boot:run

En Windows PowerShell:

.\mvnw.cmd spring-boot:run

Luego abrir en el navegador:

http://localhost:8080/health
Docker

Para construir la imagen Docker del backend:

docker build -t innovatech-backend .

Para ejecutar el contenedor:

docker run --rm --name innovatech-backend-test -p 8080:8080 innovatech-backend

Validar en:

http://localhost:8080/health
Despliegue en AWS

El backend fue desplegado en AWS utilizando los siguientes servicios:

Amazon ECR para almacenar la imagen Docker.
Amazon ECS con Fargate para ejecutar el contenedor.
Security Group con acceso al puerto 8080.
CloudWatch Logs para revisar el comportamiento del servicio.
Service Auto Scaling para escalar el servicio según uso de CPU.

La imagen fue subida a ECR con el nombre:

innovatech-backend

El servicio ECS creado fue:

innovatech-backend-service

El clúster utilizado fue:

innovatech-cluster
Configuración de red

El backend se ejecuta en el puerto:

8080

Se configuró un Security Group permitiendo tráfico TCP al puerto 8080 para poder validar el servicio desde el navegador.

Autoscaling

Se configuró autoscaling en ECS para el servicio backend con los siguientes parámetros:

Mínimo de tareas: 1
Deseado: 1
Máximo de tareas: 3
Métrica: CPU
Umbral objetivo: 50%

Esta configuración permite que el servicio pueda aumentar la cantidad de tareas cuando sube la carga de CPU, mejorando la disponibilidad y capacidad de respuesta.

Logs y monitoreo

Los logs del backend fueron revisados en AWS CloudWatch. Esto permitió validar que el contenedor iniciara correctamente y que el servicio estuviera disponible.

Ejemplo de eventos revisados:

Inicio de Spring Boot
Tomcat iniciado en puerto 8080
Endpoint /health respondiendo correctamente
Pipeline CI/CD

Se implementó un pipeline con GitHub Actions en la ruta:

.github/workflows/deploy.yml

El pipeline realiza el siguiente flujo:

1. Checkout del repositorio
2. Configuración de credenciales AWS
3. Login en Amazon ECR
4. Build de la imagen Docker
5. Tag de la imagen
6. Push de la imagen a ECR
7. Redeploy automático del servicio en ECS

El pipeline permite automatizar el despliegue del backend cada vez que se realiza un push a la rama principal.

Secrets utilizados en GitHub Actions

Las credenciales y datos sensibles fueron configurados mediante GitHub Secrets:

AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
AWS_SESSION_TOKEN
AWS_REGION
AWS_ACCOUNT_ID
ECS_CLUSTER
ECS_SERVICE

No se dejaron credenciales escritas directamente en el código.

Validación funcional

La validación del backend se realizó accediendo al endpoint público:

http://IP_PUBLICA_BACKEND:8080/health

Resultado esperado:

{
  "status": "OK"
}

Esto demuestra que el backend se encuentra desplegado correctamente en AWS ECS y disponible desde la nube.

Problemas encontrados y solución

Durante el desarrollo se presentaron algunos problemas técnicos:

Maven no estaba disponible como comando global, por lo que se utilizó Maven Wrapper.
Spring Boot no iniciaba por falta de configuración de base de datos, por lo que se configuró una base H2 para la validación.
Docker Desktop no estaba iniciado, por lo que fue necesario abrir el servicio antes de construir imágenes.
El puerto 8080 estaba ocupado, por lo que se detuvieron procesos previos antes de ejecutar el contenedor.
El workflow de GitHub Actions estaba en una ruta incorrecta, por lo que se movió a .github/workflows/deploy.yml.
El comando docker build fallaba por problemas en el contexto de build, lo que fue corregido en el workflow.
Estado final

El backend quedó desplegado correctamente en AWS ECS Fargate, con imagen en ECR, logs en CloudWatch, autoscaling configurado y pipeline CI/CD funcional mediante GitHub Actions.