# Devices App

Aplicación Android desarrollada en Kotlin con Jetpack Compose que consume la API pública de https://api.restful-api.dev para mostrar un catálogo de dispositivos electrónicos (teléfonos, tablets, relojes, audífonos, etc.).

---

## Descripción

La app obtiene una lista de dispositivos desde el endpoint `/objects` y los presenta en una lista desplazable con su nombre, color, capacidad y precio. Cada ítem incluye un ícono de teléfono y un separador visual.

---

## Tecnologías utilizadas

* Kotlin
* Jetpack Compose
* Material 3
* Retrofit 2
* Gson
* Coroutines
* ViewModel / Lifecycle
* Gradle Kotlin DSL
* Version Catalog (libs.versions.toml)

---

## Estructura del proyecto

<img width="292" height="608" alt="image" src="https://github.com/user-attachments/assets/e24d1cc9-b030-4e18-bc97-22848dcb99ba" />


---

## Requisitos

* Android Studio Ladybug o superior
* JDK 17 o 21
* compileSdk 35
* minSdk 24
* targetSdk 35
* Conexión a internet

No usar Java 25 con Gradle 8.9.

---

## Configuración

1. Clonar repositorio
   git clone <url-del-repositorio>
   cd Devices

2. Verificar gradle.properties

org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8
org.gradle.configuration-cache=true
android.useAndroidX=true
kotlin.code.style=official

3. Configurar Gradle JDK en Android Studio (JDK 17 o 21)

4. Sincronizar proyecto

5. Ejecutar en emulador o dispositivo

---

## API

Base URL: https://api.restful-api.dev
Endpoint: GET /objects

Ejemplo JSON:

[
{
"id": "1",
"name": "Google Pixel 6 Pro",
"data": {
"color": "Cloudy White",
"capacity": "128 GB"
}
},
{
"id": "4",
"name": "Apple iPhone 11, 64GB",
"data": {
"price": 389.99,
"color": "Purple"
}
}
]

---

## Modelos Kotlin

data class Device(val id: Long, val name: String, val data: Specs?)

data class Specs(
@SerializedName(value = "color", alternate = ["Color"])
val color: String?,
@SerializedName(value = "capacity", alternate = ["Capacity", "capacity GB"])
val capacity: String?,
@SerializedName(value = "price", alternate = ["Price"])
val price: Float?
)

---

## Arquitectura

UI (MainActivity / Compose)
↓
MainScreen / DeviceItem
↓
DeviceService (Retrofit)
↓
API externa

---

## Problemas conocidos

* Java 25 no soportado → usar JDK 17 o 21
* android.useAndroidX faltante → agregar en gradle.properties
* Datos inconsistentes en API → usar SerializedName con alternate

---

## Mejoras futuras

* ViewModel con StateFlow
* Manejo de estados de carga y error
* Pantalla de detalle
* Caché con Room
* Pruebas unitarias
* Búsqueda y filtrado

---

## Contribuciones

1. Fork
2. Crear rama
3. Commit
4. Push
5. Pull Request

---

## Licencia

MIT

---

## Autor

Tu Nombre
https://github.com/Guaso1396

---

## Agradecimientos

https://restful-api.dev
https://developer.android.com/jetpack/compose
https://square.github.io/retrofit/

---

## Referencias

https://developer.android.com/docs
https://developer.android.com/jetpack/compose
https://square.github.io/retrofit/
https://m3.material.io/
https://kotlinlang.org/docs/coroutines-overview.html
