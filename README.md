# Prueba Técnica Dux Software - Desarrollador Full Stack

Este repositorio contiene el código fuente y la documentación para la prueba técnica de Dux Software.

## Tecnologías Utilizadas

- Spring Boot
- JWT (JSON Web Tokens)
- Swagger
- Docker
- Base de datos H2 (en memoria)
- Mockito

## Instrucciones

### Generar la Imagen de Docker

Antes de generar la imagen de Docker, asegúrate de que el proyecto esté compilado y empaquetado. Para hacer esto, ejecuta el siguiente comando en la raíz del proyecto para limpiar y construir el proyecto con Maven:

```bash
mvn clean package
```
Una vez generado el archivo .war de tu aplicación, puedes construir la imagen Docker utilizando el siguiente comando:
(Debemos estar posicionados en la direccion donde se encuentra el archivo Dockerfile)

```bash
docker build --tag=service:latest .
```

### Correr la Imagen de Docker
Una vez que hayas generado la imagen Docker, puedes ejecutar el contenedor utilizando el siguiente comando:

```bash
docker run -p8080:8080 service:latest
```
Esto iniciará la aplicación y la hará accesible en http://localhost:8080.

## Swagger
Si nos dirigimos a http://localhost:8080/swagger-ui/index.html podemos probar la API utilizando Swagger. 

###Iniciar Sesión
Primero necesitamos ir al AuthController y realizar el /login con el siguiente body:

```bash
{
  "username": "test",
  "password": "12345"
}
```

###Ingresar el token
Debemos copiar el token que nos da como respuesta y colocarlo en el encabezado de autorización(esquina superior derecha). Finalmente ya podemos probar todos los servicios.

## Base de Datos H2
Para realizar consultas en la base de datos H2 en memoria, sigue estos pasos:

Abre tu navegador web y ve a http://localhost:8080/h2-console.
Ingresa la siguiente información:
JDBC URL: jdbc:h2:mem:bd
User Name: sa
Password: (deja este campo vacío)
Haz clic en "Connect".
Ahora deberías estar conectado a la consola de H2 y poder realizar tus consultas.
