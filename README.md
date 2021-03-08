# APLICACION HulkStores
Este proyecto contiene dos partes el BACKEND Y FRONT-END

# 1. BACKEND  (HulkSpringMaven)
	Necesita configura la BDD antes de ejecutar el Rest API.
## Base de datos
* Crear la base de datos con el nombre "hulk", 
* Esta bdd contiene tablas del sistema: (Usar el script hulk.sql)


## Rest API
Este Rest API se encuentra realizado en el IDE Eclipse(Spring Tool Suite) con Java 8 y Spring Boot, PostgresSQL,JPA,Hibernate con Maven. 
Contiene un CRUD con la tabla de "ticket" y auntenticación del usuario SUPERVISOR con la tabla "usuario".

* Configurar las variables de conexion a la Base de datos.
	* 1. Verificar el archivo HulkSpringMaven\src\main\resources\application.properties
		spring.datasource.url=jdbc:postgresql://localhost:5432/hulk (Verifica la ruta de conexion:puerto/'NombreBDD')
		spring.datasource.username=postgres (Usuario de la Bdd)
		spring.datasource.password=root (Password de la Bdd)
Ejecutar la aplicación en modo de desarrollo.<br>
[http://localhost:8080](http://localhost:8080) Ruta para ver en el navegador.


