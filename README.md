# Rick & Morty App

Aplicación Android desarrollada en Android Studio con Kotlin y Jetpack Compose que consume la API pública de Rick and Morty API para mostrar un catálogo de personajes de la serie.

---

## Descripción

La app obtiene una lista de personajes desde el endpoint /character y los presenta en una cuadrícula de 2 columnas con su imagen, nombre, estado, especie, género, origen, ubicación y cantidad de episodios. Incluye paginación para navegar entre las distintas páginas de resultados, un indicador visual de estado (vivo, muerto o desconocido) con colores dinámicos y manejo de estados de carga y error con opción de reintentar.

Proyecto desarrollado en Android Studio, versión Hedgehog o superior.

---

## Características principales

* Listado de personajes en cuadrícula de 2 columnas.
* Paginación con botones Anterior y Siguiente, más indicador de página.
* Indicador circular de estado: verde para vivo, rojo para muerto, gris para desconocido.
* Carga asíncrona de imágenes con Coil.
* Manejo de estados: Loading, Success y Error con reintento.
* Tema oscuro y claro con soporte para colores dinámicos en Android 12 o superior.
* Diseño con gradiente de fondo y tarjetas redondeadas.

---

## Tecnologías utilizadas

* Kotlin
* Jetpack Compose
* Material 3
* Retrofit 2
* Gson
* Coil
* Coroutines
* Gradle Kotlin DSL

---

## Estructura del proyecto

El proyecto se organiza en el paquete com.example.myapplication con los siguientes archivos:

* Character.kt: modelos de datos CharacterResponse, Info, Character y Origin.
* Characteritem.kt: Composable que renderiza cada tarjeta de personaje.
* Characterservice.kt: interfaz Retrofit con los endpoints.
* Constants.kt: constantes globales como BASE_URL y rutas.
* MainActivity.kt: punto de entrada y lógica de carga de datos.
* MainScreen.kt: UI principal con grid, paginación y estados.

Dentro del paquete ui.theme se encuentran:

* Color.kt: definición de colores base.
* Theme.kt: configuración del tema Material 3.
* Type.kt: tipografía personalizada.

---

## Requisitos

* Android Studio Ladybug o superior.
* JDK 17 o 21.
* compileSdk 35.
* minSdk 24.
* targetSdk 35.
* Conexión a internet.

No usar Java 25 con Gradle 8.9.

---

## Configuración

1. Clonar el repositorio.
2. Verificar que el archivo gradle.properties contenga:
   org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
   org.gradle.configuration-cache=true
   android.useAndroidX=true
   kotlin.code.style=official
3. Configurar Gradle JDK en Android Studio con JDK 17 o 21.
4. Sincronizar el proyecto.
5. Ejecutar en un emulador o dispositivo físico.

---

## API

La aplicación utiliza la base URL https://rickandmortyapi.com/api/ y el endpoint GET /character?page={page}.

La API devuelve un JSON con dos campos principales: info y results. El campo info contiene count, pages, next y prev. El campo results es una lista de personajes, donde cada personaje incluye id, name, status, species, gender, image, origin, location y episode.

---

## Modelos Kotlin

El modelo CharacterResponse contiene un objeto Info y una lista de Character.

El objeto Info tiene los campos count, pages, next y prev.

La clase Character tiene los campos id, name, status, species, gender, image, origin, location y episode. El campo episode es una lista de cadenas que por defecto está vacía.

La clase Origin solo tiene el campo name.

---

## Arquitectura

La arquitectura sigue un flujo sencillo:

La UI en MainActivity y Compose llama a MainScreen y CharacterItemView. Luego se comunica con CharacterService mediante Retrofit. Finalmente, se conecta con la API externa.

---

## Problemas conocidos

* Java 25 no está soportado; usar JDK 17 o 21.
* Si falta android.useAndroidX, agregarlo en gradle.properties.
* La API puede devolver datos inconsistentes; validar campos opcionales como next y prev.
* Las imágenes pueden tardar en conexiones débiles; Coil maneja caché automáticamente.

---

## Mejoras futuras

* Implementar ViewModel con StateFlow.
* Añadir pantalla de detalle del personaje.
* Incluir búsqueda por nombre.
* Agregar caché con Room.
* Realizar pruebas unitarias y de UI.
* Dar soporte para tablets con más columnas en el grid.
