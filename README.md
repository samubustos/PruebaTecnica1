DUX SOFTWARE | PRUEBA TÉCNICA 
=======
# Prueba Técnica Dux Software - Desarrollador Full Stack
Este repositorio contiene el código fuente y la documentación para la prueba técnica de Dux Software.

El sistema desarrollado con Spring Boot y Java 17 proporciona una API para gestionar información de equipos de fútbol. Permite realizar consultas, búsquedas, creación, actualización y eliminación de equipos. Utiliza JWT para autenticación y H2 Database para la persistencia de datos en memoria. Se han escrito pruebas unitarias con mocks para asegurar la calidad del servicio. La aplicación está dockerizada para facilitar su despliegue y se ha generado documentación utilizando Swagger.

## Tecnologías Utilizadas

- Spring Boot
- JWT (JSON Web Tokens)
- Swagger
- Docker
- Base de datos H2 (en memoria)
- Mockito

## Instrucciones

### Creación imagen Docker

Para crear la imagen de Docker primero debemos asegurarnos de que el proyecto está empaquetado y compilado. Para realizar esto podemos utilizar el siguiente comando con maven:
--Debemos estar posicionados en la direccion donde se encuentra el archivo Dockerfile.

```bash
mvn clean package
```
Con esto generamos nuestro archivo .war que nos va a servir para crear la imagen de Docker de la siguiente manera:

```bash
docker build --tag=service:latest .
```

### Iniciar el contenedor Docker 
Una vez que hayas generado la imagen Docker, puedes ejecutar el contenedor utilizando el siguiente comando:

```bash
docker run -p8080:8080 service:latest
```
Podras acceder a la aplicación en http://localhost:8080 

## Swagger
En http://localhost:8080/swagger-ui/index.html observamos la documentación de la aplicación.

### Autenticación
La autenticación se realizo mediante JWT restringiendo el acceso a los endpoints. Por lo tanto antes de utilizar las operaciones debemos iniciar sesión en /auth/login con el siguiente usuario.

```bash
{
  "username": "test",
  "password": "12345"
}
```
Tambien podremos registrar nuestro mismo usuario en /auth/register completando el siguiente body (obligatorios):

```bash
{
  "username": "string",
  "password": "string"
}
```

### Ingresar el token
Debemos copiar el token que nos da como respuesta y colocarlo en el encabezado de autorización(esquina superior derecha). Finalmente ya podemos probar todos los servicios.
```bash
{
  "token": token
}
```

## Base de Datos H2
Las consultas las podemos realizar en http://localhost:8080/h2-console. Iniciando sesión con la configuracion establecida en application.properties.

```bash
{
  JDBC URL: jdbc:h2:mem:bd
  User Name: sa
  Password: 
}
```
Proyecto realizado por Bustos Puntis, Samuel.
