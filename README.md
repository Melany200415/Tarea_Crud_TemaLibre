# Sistema CRUD de Registro de Mascotas (JavaFX + MySQL)

Este proyecto es una aplicación de escritorio desarrollada en Java con JavaFX, que permite gestionar el registro de mascotas mediante operaciones CRUD (Crear, Leer, Actualizar y Eliminar), conectada a una base de datos MySQL.

---

## Funcionalidades

- Registrar mascotas
- Listar mascotas en una TableView
- Actualizar datos de mascotas
- Eliminar registros
- Selección de registros en tabla
- Interfaz de login básica

---

## Arquitectura del Proyecto

El sistema está basado en el patrón MVC (Modelo - Vista - Controlador):

- Modelo: representa la entidad Mascota
- Vista: interfaces desarrolladas en JavaFX con FXML
- Controlador: maneja la lógica de la aplicación

Además, se implementan los siguientes patrones de diseño:

- DAO: separación del acceso a datos
- Factory: creación de objetos Mascota
- Strategy: validación de datos de entrada

---

## Tecnologías utilizadas

- Java 21
- JavaFX 21
- MySQL
- JDBC
- Maven
- Scene Builder (FXML)
- IntelliJ IDEA

---

## Base de Datos

Tabla principal: mascotas

Campos:
- codigo
- nombre
- raza
- edad
- propietario

