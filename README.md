# Rick & Morty Android App

Aplicación Android desarrollada con Jetpack Compose que consume la Rick and Morty API para mostrar información sobre los personajes de la serie.

---

## DESCRIPCIÓN GENERAL

Esta aplicación permite explorar los personajes del universo de Rick & Morty mediante una interfaz moderna construida completamente con Jetpack Compose. Los usuarios pueden navegar a través de las páginas de resultados, visualizar los detalles de cada personaje y reintentar la carga en caso de errores de red.

---

## CARACTERÍSTICAS PRINCIPALES

* Listado de personajes en formato de cuadrícula de 2 columnas.
* Paginación para navegar entre las distintas páginas de resultados.
* Indicador visual de estado (vivo, muerto o desconocido) con colores dinámicos.
* Carga asíncrona de imágenes usando Coil.
* Manejo de estados (Loading, Success, Error) con retroalimentación visual.
* Tema oscuro y claro con soporte para colores dinámicos en Android 12 o superior.
* Diseño con gradiente y tarjetas personalizadas.

---

## TECNOLOGÍAS UTILIZADAS

* Kotlin
* Jetpack Compose
* Retrofit 2
* Gson
* Coil
* Material 3
* Coroutines

---

## ESTRUCTURA DEL PROYECTO

com.example.myapplication

Character.kt: modelos de datos (CharacterResponse, Info, Character, Origin)
Characteritem.kt: Composable que renderiza cada tarjeta de personaje
Characterservice.kt: interfaz Retrofit con los endpoints
Constants.kt: constantes globales (BASE_URL, rutas)
MainActivity.kt: punto de entrada y lógica de carga de datos
MainScreen.kt: UI principal con grid, paginación y estados

ui.theme

Color.kt: definición de colores base
Theme.kt: configuración del tema Material 3
Type.kt: tipografía personalizada

---

## DETALLE DE LOS COMPONENTES

### 1. Character.kt

Define los modelos de datos que representan la respuesta de la API.

* CharacterResponse: contiene la información de paginación (Info) y la lista de personajes (results).
* Info: metadatos de paginación (count, pages, next, prev).
* Character: representa un personaje con atributos como name, status, species, image, origin, location, episode.
* Origin: ubicación o planeta de origen.

---

### 2. Characteritem.kt

Composable que renderiza cada tarjeta individual de personaje.

* Imagen cuadrada cargada con Coil.
* Nombre en negrita con tipografía titleMedium.
* Indicador circular de color según estado:

  * Alive: verde
  * Dead: rojo
  * Otros: gris
* Emojis descriptivos para género, origen, ubicación y cantidad de episodios.

---

### 3. Characterservice.kt

Interfaz Retrofit que define el endpoint:

@GET(Constants.CHARACTERS_PATH)
suspend fun getCharacters(@Query("page") page: Int = 1): CharacterResponse

---

### 4. Constants.kt

Almacena las constantes de configuración:

const val BASE_URL = "https://rickandmortyapi.com/api/"
const val CHARACTERS_PATH = "character"

---

### 5. MainActivity.kt

Punto de entrada de la app.

* Configura Retrofit con GsonConverterFactory.
* Maneja el estado UiState (Loading, Success, Error).
* Gestiona la paginación actual (currentPage) y reintentos (retryTrigger).
* Usa LaunchedEffect para disparar las peticiones cuando cambia la página o el trigger.

---

### 6. MainScreen.kt

Contiene la UI principal.

* UiState (sealed interface): Loading, Success, Error.
* Gradiente de fondo con tonos oscuros azulados.
* Título "Rick & Morty" con tipografía displaySmall.
* LazyVerticalGrid de 2 columnas para mostrar los personajes.
* Controles de paginación: botones Anterior y Siguiente más indicador "Página X / Y".
* Función extractPage para calcular la página actual a partir de next y prev.

---

### 7. ui.theme

* Color.kt: paleta base (púrpuras y rosas).
* Theme.kt: configuración de Material 3 con soporte para colores dinámicos.
* Type.kt: tipografía personalizada.

---

## CÓMO EJECUTAR EL PROYECTO

1. Clonar el repositorio
   git clone <url-del-repositorio>

2. Abrir en Android Studio (Hedgehog o superior recomendado)

3. Sincronizar Gradle

4. Ejecutar en emulador o dispositivo físico (Android 7.0 o superior)

No requiere API Key, ya que la API es pública.

---

## FLUJO DE LA APLICACIÓN

MainActivity
→ LaunchedEffect llama a CharacterService.getCharacters(page)
→ UiState = Loading muestra indicador de carga
→ Si éxito: muestra grid de personajes
→ Si error: muestra mensaje y botón "Reintentar"

---

## DETALLES DE IMPLEMENTACIÓN

* key = { it.id } optimiza la recomposición del grid.
* statusColor() convierte el estado a color de forma segura.
* extractPage() usa Regex para obtener el número de página.
* enableEdgeToEdge() permite dibujar detrás de las barras del sistema.
* mutableIntStateOf mejora rendimiento frente a mutableStateOf<Int>.

---

## POSIBLES MEJORAS FUTURAS

* Pantalla de detalle del personaje
* Búsqueda por nombre
* Caché offline con Room
* Tests unitarios y de UI
* Soporte para tablets con más columnas

---

## LICENCIA

Uso educativo. Datos proporcionados por la Rick and Morty API.

---

## AUTOR

https://github.com/Guaso1396

---

¡Wubba Lubba Dub Dub!
