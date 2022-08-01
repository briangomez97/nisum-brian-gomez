# Nisum Brian Gomez API 

## Herramientas usadas

Las herramientas mencionadas a continuación fueron integradas por medio de Gradle

* Spring Data JPA
* Spring WEB
* Spring Security
* Spring Actuator
* Spring Cache
* Spring Devtools
* Spring Test
* Jacoco
* JWT
* Swagger
* Flyway
* H2 Database
* Lombok
* Mapstruct
* Docker

## Despliegue del API

El API tiene configuración de Docker para poder desplegarse con Docker Compose o simmplemente con Docker para cualquier entorno. Los comandos que se pueden usar para el despliegue son los siguientes:

#### NOTA: Recuerde compilar el proyecto con el comando 'build' de Gradle para la generación del archivo ejecutable de Java (.jar) antes de cualquier intento de despliegue

### Docker

```
docker build -t nisum-brian-gomez-api .
docker run -p 8020:8020 nisum-brian-gomez-api
```

### Docker Compose

```
docker-compuse up --build
```

## Comprobación de estado de la API

### Actuator

Una vez se tenga desplegada la API se puede revisar el estado de esta, consumiento los endpoints correspondientes de Spring Actuator, en este caso el endpoint 'health'

* Endpoint estado del API: GET http://localhost:8020/api/actuator/health

### H2 Console

También se puede revisar el estado de la base de datos ya que el API tiene habilitada la opción de interactuar con la consola web. Se puede acceder por medio de la siguiente URL:

* URL H2 Console: GET http://localhost:8020/api/h2console
* Valor del campo JDBC URL: jdbc:h2:mem:nisum

#### Nota: El resto de los datos deben quedar por defecto

## Base de datos en memoria H2

Para la creación del esquema con el cual se trabaja con la API, se utilizó una herramienta llamada Flyway, esta, nos permite un control de versiones sobre la base de datos y migraciones de cambios que se hagan sobre el esquema, quitandole la responsabilidad al ORM (Hibernate) y teniendo una herramienta dedicada a esto, lo cual hace que la delegación de tareas dentro de la API la haga lo más mantenible posible.

Los scripts usados para la inicialización del esquema se encuentran en el directorio:

```
src/main/resources/db/migration
```

Con esta organización de carpetas y una configuración previa, Flyway se encarga de de la inicialización del esquema y de la migración del mismo, siempre y cuando sea necesario.

## Introducción a la funcionalidad de la API

La API fue construida con el fin de darle solución a la evaluación propuesta por Nisum. Inicialmente es necesario conocer el modo en que el API responde las peticiones, ya sea para una respuesta exitosa o de error (todos los errores son controlados). Asi mismo, todas las respuestas tienen un formato transversal al API. A continuación se describen los formatos de respuesta.


## Funcionalidad de la API

Es importante aclarar que se tienen servicios asegurados con Spring Security y JWT, los cuales están condicionados a utilizar un Token previamente generado. Dicho esto, el Token que se genera, tiene una vigencia de 2 minutos (para objeto de prueba, en un entorno diferente se asignaria un tiempo más prudente).

Dado que el Token es persistido con el usuario y sabiendo lo mencionado anteriormente, cada vez que se expire el Token se debe renovar, y esto se hace por medio del Login, sin embargo, cada que vez que se hace Login se valida que el Token persistido con el usuario esté expirado, de lo contrario, retornará el Token vigente persistido.

Una vez se tenga el Token se podrán consumir los servicios enviando este como Header de la petición.

Ejemplo:

```
Header:
- Key: Authorization
- Value: Bearer {Token retornado por el API}
```

Adicionalmente, se pueden configurar las expresiones regulares que validan el 'email' y la 'password' cuando se crea un usuario en la API. Para esto se hace por medio de un identificador por expresión regular, teniendo la siguiente definición:

* Llave de expresion regular para validar email: EMAIL_REGEX
* Llave de expresion regular para validar password: PASSWORD_REGEX

Más adelante se explicará cómo y en donde se deben usar estos identificadores de las expresiones regulares.

### Respuesta exitosa API (Transversal)
```
{
  "response": {
    "body": {},
    "length": 0,
    "message": "string"
  },
  "status": 0
}
```

#### Definición

* response: Este atributo identifica la respuesta como exitosa
* body: El tipo de dato de este atributo es genérico, por lo que dependiendo del servicio se retornará el objeto que sea pertinente
* lenght: Es reemplazado cuando en el atributo 'body' se responde con una lista o un mapa, indicando el tamaño del mismo
* message: Es reemplazado por un mensaje cuando se quiere complementar la respuesta. Por lo general, no se utiliza mientras haya un 'body' en la respuesta de la petición
* status: Indica el codigo de estado de respuesta de la petición hecha

### Respuesta de error API (Transversal)

```
{
  "error": {
    "exceptionName": "string",
    "message": "string",
    "fieldErrors": [
        {
            "field": "string",
            "message": "string"
        }
    ]
  },
  "status": 0
}
```

### Definición

* error: Identifica la respuesta como un error ocurrido en la API
* exceptionName: Se asigna cuando ocurre una excepcion, y se integró para poder tener una mejor trazabilidad a la hora de identificar el error
* message: Da más detalle de la excepción, haciendo que sea más claro el error
* fieldErrors: Solamente se inicializa cuando hay un error de validación de campos en alguno de los JSON enviados al API
* field (fieldErrors): Indica el nombre del campo que generó el error de validación
* message (fieldErrors): Indica a más detalle cuál fue el error de validación con el campo
* status: Indica el codigo de estado de respuesta de la petición hecha

## Diagrama de la solución

Se evidencia por medio de la siguiente imagen un diagrama que muestra la estructura de la API y algunas de las tecnologias usadas, esto con el fin de poder tener una visión más clara de lo que se desarolló para llevar a cabo la evaluación

![image](https://user-images.githubusercontent.com/94187517/182066893-86bec893-f6cd-4b46-a835-fe861ed9d340.png)

Para el consumo de los servicios se tiene la siguiente colección de Postman que podrá ser apoyo a la hora de probar la API.

* Colección de Postman: https://www.getpostman.com/collections/b550e904f9011a4ccc23

![image](https://user-images.githubusercontent.com/94187517/182052634-737262c5-8e53-4d4b-acdc-2c73d2044798.png)

### Swagger

Dado que la API tiene implementado Swagger, una librería que nos permite poder ver los detalles de cada uno de los servicios expuestos se mostrará a continuación datos necesarios para el consumo de los mismos.

* URL Swagger: GET http://localhost:8020/api/swagger-ui.html

## Servicios expuestos

Todos los ejemplos de los parámetros, JSON o entre otras propiedades de la petición pueden ser modificados para validar la funcionalidad de la API. Lo que se muestra es netamente ejemplo funcional de lo que se podría enviar al servicio.

### Servicios sin seguridad

* Crear usuario: 

```
POST /api/auth/register

Body JSON:
{
    "name": "Paco Garcia",
    "email": "paco.garcia@mail.co",
    "password": "Nisum*2022",
    "phones": [
        {
            "number":"3059072363",
            "citycode": "6",
            "countrycode": "57"
        },
        {
            "number":"3136003736",
            "citycode": "6",
            "countrycode": "57"
        },
        {
            "number":"3113348967",
            "citycode": "6",
            "countrycode": "57"
        }
    ]
}
```

* Login de usuario: 

```
POST /api/auth/login

Query param: (Obligatorio) 
- Key: email
- Value: paco.garcia@mail.co

Query param: (Obligatorio) 
- Key: password
- Value: Nisum*2022
```

* Validar estado de API:

```
GET /api/actuator/health
```

* Endpoints habilitados por Actuator API: 

```
GET /api/actuator
```

* Consola de base de datos H2: 

```
GET /api/h2console
```

* Documentación Swagger: 

```
GET /api/swagger-ui.html
```

### Servicios con seguridad

* Obtener todos los usuarios: 

```
GET /api/user

Header: (Obligatorio) 
- Key: Authorization
- Value: Bearer {Token}
```

* Obtener todas las expresiones regulares:

```
 GET /api/regex
 
 Header: (Obligatorio) 
- Key: Authorization
- Value: Bearer {Token}
 ```

* Obtener una expresión regular: 

```
GET /api/regex/:regexName

Header: (Obligatorio) 
- Key: Authorization
- Value: Bearer {Token}

Path param: 
- Value: PASSWORD_REGEX|EMAIL_REGEX
```

* Actualizar una expresión regular: 

```
PUT /api/regex/:regexName

Header: (Obligatorio) 
- Key: Authorization
- Value: Bearer {Token}

Path param: 
- Value: PASSWORD_REGEX|EMAIL_REGEX

Body JSON:
{
    "regularexpression": "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>])"
}
```

#### NOTA: En donde :regexName es el identificador de la expresión regular mencionados en la funcionalidad de la API

## Implementación de caché

Cuando la API hace el cargue de las expresiones regulares para validar el 'email' y la 'password' cuando se crea un usuario, se asegura de guardar en caché estos datos, para que el performance de la API no se vea afectado pensando el caso en que haya una concurrencia alta en el consumo de este servicio. La diferencia es bastante notable, como se aprecia en las siguientes imagenes:

### Sin las expresiones regulares en caché:

![image](https://user-images.githubusercontent.com/94187517/182084478-0e886015-884b-42d2-ad4c-783fa6f13c23.png)

### Con las expresiones regulares en caché:

![image](https://user-images.githubusercontent.com/94187517/182084538-b38a97b6-0771-40a5-9228-24b28b03e6c4.png)

Para objeto de prueba, la vigencia del caché es de 30 segundos, y se debe tener en cuenta que el caché también se eliminará cuando se hagan actualizaciones sobre cualquiera de las expresiones regualres guardads en la BD. 

## Ejecución de pruebas de integración

Se crearon pruebas de integración las cuales prueban cada una de las diferentes capas de la API (desde el controlador hasta la base de datos). Para esto, se hizo una configuración de base de datos en memoria para la ejecución de las pruebas.

![image](https://user-images.githubusercontent.com/94187517/182086192-ad10d73b-865f-42ad-928a-beb4825f22bb.png)

Estas se pueden ejecutar por medio del comando 'test' de Gradle, teniendo el siguiente resultado:

![image](https://user-images.githubusercontent.com/94187517/182085986-84dde958-fe2b-4920-98bc-47ef6479971e.png)