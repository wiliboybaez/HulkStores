# APLICACION HulkStores
Este proyecto contiene dos partes el BACKEND 

# 1. BACKEND  (HulkSpringMaven)
	Necesita configura la BDD antes de ejecutar el Rest API.
## Base de datos
* Crear la base de datos con el nombre "hulk", 
* Esta bdd contiene tablas del sistema: (Usar el script hulk.sql)


## Rest API
Este Rest API se encuentra realizado en el IDE Eclipse(Spring Tool Suite) con Java 8 y Spring Boot, PostgresSQL,JPA,Hibernate con Maven. 
* Configurar las variables de conexion a la Base de datos.
	* 1. Verificar el archivo HulkSpringMaven\src\main\resources\application.properties
		spring.datasource.url=jdbc:postgresql://localhost:5432/hulk (Verifica la ruta de conexion:puerto/'NombreBDD')
		spring.datasource.username=postgres (Usuario de la Bdd)
		spring.datasource.password=root (Password de la Bdd)
Ejecutar la aplicación en modo de desarrollo.<br>
[http://localhost:8080](http://localhost:8080) Ruta para ver en el navegador.


# 2. FRONT-END (clientstore)-- ESTA INCOMPLETO

Este proyecto esta realizado con REACT Incluye Hooks, Context.
La aplicación Web consume los servicios del RestApi 
* Configurar las variables de conexion al Rest API.
	* 1. Verificar el archivo flightclient\.env.development.local
		REACT_APP_BACKEND_URL=http://localhost:8080


## Iniciar Proyecto en consola

### `npm install`

### `npm start`

Ejecutar la aplicación en modo de desarrollo.<br>
[http://localhost:3000](http://localhost:3000) Ruta para ver en el navegador.

