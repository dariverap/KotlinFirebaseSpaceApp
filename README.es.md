🇺🇸 English Version: [Read here](./README.md)

<div align="center">

# Explora Spaces+

![Android](https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.8-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Backend-FFCA28?style=flat-square&logo=firebase&logoColor=black)
![Room](https://img.shields.io/badge/Room-SQLite-4479A1?style=flat-square&logo=sqlite&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-Networking-673AB7?style=flat-square&logo=square&logoColor=white)

</div>

<br />

### Creado por Diego Rivera

## 📋 Resumen Ejecutivo

**Explora Spaces+** es una aplicación nativa de Android diseñada como una enciclopedia interactiva para la serie animada "Final Space". Resuelve la necesidad de contar con una plataforma unificada donde los fanáticos puedan acceder a información de personajes, gestionar sus favoritos de manera offline y contribuir con contenido personalizado a una base de datos comunitaria en la nube.

Al integrar una persistencia local robusta con sincronización en la nube, la aplicación asegura una experiencia de usuario fluida sin importar la conectividad de la red, proporcionando autenticación segura y actualizaciones de datos en tiempo real.

## ✨ Características Principales

*   **Integración de API Externa:** Consume la **Final Space API** vía Retrofit para obtener y mostrar datos exhaustivos de los personajes.
*   **Persistencia Offline (Favoritos):** Utiliza **Room Database** (abstracción de SQLite) para permitir a los usuarios guardar y acceder a sus personajes favoritos sin conexión a internet.
*   **Sincronización en la Nube:** Integra **Firebase** (Firestore y Storage) permitiendo a los usuarios crear personajes personalizados, subir imágenes y sincronizar datos entre dispositivos.
*   **Autenticación Segura:** Implementa **Firebase Auth** soportando inicio de sesión con Correo/Contraseña y **Google Sign-In**.
*   **Arquitectura UI Moderna:** Construida con componentes de Android Jetpack, incluyendo **Navigation Component** para transiciones fluidas y **ViewBinding** para una interacción segura con las vistas.

## 🏗️ Arquitectura

Este proyecto sigue el patrón arquitectónico **MVVM (Model-View-ViewModel)**, asegurando una clara separación de responsabilidades y facilidad de prueba:

1.  **Capa de Vista (`/view`):**
    *   Activities (`MainActivity`, `InicioActivity`) y Fragments (`ExploraListFragment`, `DetailFragment`).
    *   Responsable de renderizar la UI y observar LiveData de los ViewModels.
2.  **Capa de ViewModel (`/view/fragments`):**
    *   `ListViewModel`: Gestiona el flujo de datos de la API.
    *   `FavoriteViewModel`: Maneja las interacciones con la base de datos local.
    *   `FirestoreViewModel`: Gestiona la sincronización de datos en la nube.
    *   Actúa como puente, manteniendo el estado de la UI y sobreviviendo a cambios de configuración.
3.  **Capa de Modelo/Repositorio (`/data`):**
    *   **Patrón Repositorio:** `PersonajesRepository` arbitra entre la Fuente de Datos Remota (Retrofit) y la Fuente de Datos Local (Room).
    *   **Servicios Retrofit:** Interfaces que definen los endpoints de la API.
    *   **DAO:** Data Access Objects para operaciones en la base de datos Room.

## 🛠️ Stack Tecnológico

*   **Lenguaje:** Kotlin
*   **Core:** Android SDK (Min SDK 24, Target SDK 33)
*   **Red:** Retrofit 2, GSON, OkHttp
*   **Persistencia:** Room Database, Firebase Firestore, Firebase Storage
*   **Auth:** Firebase Authentication, Google Sign-In
*   **UI/Media:** Glide (Carga de imágenes), FancyToast, Material Design 3
*   **Concurrencia:** Kotlin Coroutines

## 🚀 Instalación y Configuración

### Prerrequisitos
*   Android Studio Flamingo o superior
*   JDK 11 o superior

### Pasos

1.  **Clonar el repositorio**
    ```bash
    git clone https://github.com/tu-usuario/explora-spaces.git
    ```

2.  **Abrir en Android Studio**
    Selecciona "Open an Existing Project" y apunta al directorio clonado.

3.  **Configuración de Firebase**
    *   Crea un proyecto en [Firebase Console](https://console.firebase.google.com/)
    *   Registra una app Android con el package name `com.diegorivera.ejercicio`
    *   Descarga tu `google-services.json` y colócalo en el directorio `app/`
    *   Habilita Firebase Authentication (Email/Password y Google Sign-In)
    *   Registra tu huella SHA-1 para que funcione Google Sign-In

4.  **Construir y Ejecutar**
    *   Sincroniza el proyecto con los archivos Gradle (Sync Project with Gradle Files).
    *   Selecciona un Emulador o dispositivo físico.
    *   Haz clic en **Run 'app'**.

---
*© 2023 Diego Rivera. Todos los derechos reservados.*