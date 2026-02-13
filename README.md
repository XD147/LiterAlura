# 📚 LiterAlura

Aplicación desarrollada en **Java** que permite buscar, almacenar y consultar libros y autores utilizando una **API pública de libros** y una **interfaz por consola**.

## 🚀 Características

- Búsqueda de libros por título
- Almacenamiento de libros y autores en base de datos
- Listado de libros registrados
- Listado de autores con sus libros
- Consulta de autores vivos en un año determinado
- Filtro de libros por idioma
- Interfaz por consola
- Proyecto desarrollado en **IntelliJ IDEA**

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **IntelliJ IDEA**
- **Gutendex API**  
  🔗 https://gutendex.com

## 📋 Funcionalidades del Menú

Al ejecutar la aplicación, se muestra el siguiente menú:

```
...::: Bienvenidos a LiterAlura :::...
--------------------------------------
Elija la opción a través de su número:

1 - Buscar libro por titulo 
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos a un determinado año
5 - Listar libros por idioma

0 - Salir
```

## ⚙️ Funcionamiento

- El usuario selecciona una opción del menú
- La aplicación consulta la API de Gutendex cuando es necesario
- Los datos obtenidos se almacenan en la base de datos PostgreSQL
- Se muestran los resultados formateados en consola

## ▶️ Ejecución

1. Clonar el repositorio  
2. Configurar la base de datos PostgreSQL en `application.properties`
3. Ejecutar el proyecto desde **IntelliJ IDEA** o con:

```bash
mvn spring-boot:run
```

## 🎯 Objetivo del Proyecto

Proyecto académico orientado a:

- Practicar consumo de APIs externas
- Aplicar persistencia con JPA
- Trabajar con relaciones entre entidades
- Desarrollar aplicaciones de consola en Java
- Aplicar buenas prácticas de programación
