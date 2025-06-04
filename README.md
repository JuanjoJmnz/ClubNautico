# ⛵ Proyecto Final Prácticas 1º DAM - Gestión de Club Náutico

Este es un sistema de gestión de un **club náutico** desarrollado con **Java**, **Spring Boot**, **JPA/Hibernate** y **MySQL**. Permite administrar **socios**, **barcos**, y **viajes o salidas al mar**, utilizando una arquitectura organizada por capas: controladores, servicios, repositorios y persistencia.

---

## 📚 Entidades Principales
Socio: Representa a un socio del club (nombre, apellido, teléfono, etc).

Barco: Asociado a un socio propietario. Tiene matrícula, nombre y cuota.

Viaje: Salida al mar. Relacionada con un barco y un patrón (socio patrón).
<br><br>

## 🚀 Funcionalidades
Crear, leer, actualizar y eliminar Socios, Barcos y Viajes.

Consultas específicas:

Buscar socios por nombre parcial o ID.

Filtrar barcos por matrícula, nombre o cuota.

Filtrar viajes por destino, fecha o patrón.

Soporte para datos de patrón independientes o referenciados desde un socio.
<br><br>

## 🎯 Tecnologías Usadas
Java 17+

Spring Boot 3

Spring Data JPA (Hibernate)

MySQL como base de datos relacional

Lombok para simplificar código

Postman / Swagger para pruebas de la API REST
<br><br>

## 🔗 Endpoints de la API
Algunos ejemplos:

### Socios
GET /socio/findAll – Listar todos los socios

GET socio/find/nombre?nombre=Juan – Buscar por nombre parcial

GET socio/find/telefono/ – Buscar socios por teléfono

### Barcos
GET /barco/find/matricula/6543AAA – Buscar por matrícula

GET /barco/findEntre/100,300 – Buscar por cuota

### Viajes
GET /viaje/find/destino/Ibiza – Buscar por destino

GET /viaje/find/fechaEntre/2025-05-25 11:00:00,2025-05-25 19:00:00 – Buscar por rango de fechas

PUT /viaje/update/{id} – Actualizar un viaje
<br><br>

## 🛠 Cómo ejecutar el proyecto

### Clona el repositorio:
<pre>
<code>
git clone https://github.com/JuanjoJmnz/ClubNautico.git
cd ClubNautico
</code>
</pre>
  
### Configura tu application.properties con tu conexión MySQL:
<pre>
<code>
spring.datasource.url=jdbc:mysql://localhost:3306/clubnautico
spring.datasource.username=root
spring.datasource.password=tucontraseña
spring.jpa.hibernate.ddl-auto=update
</code>
</pre>

### Ejecuta la aplicación desde tu IDE o con Maven:

<pre>
<code>
mvn spring-boot:run
</code>
</pre>

### Prueba los endpoints usando Postman, Insomnia o Swagger.
<br><br>

## 🧪 Testing
Se incluyen clases de prueba con JUnit 5 para los controladores principales. Puedes ejecutar los tests desde tu IDE o usando:

<pre>
<code>
mvn test
</code>
</pre>
<br><br>

## 📌 Autor
Juan José Jiménez Gil.<br>
Proyecto desarrollado para mis prácticas en empresa en el 1º curso del CFGS DAM
