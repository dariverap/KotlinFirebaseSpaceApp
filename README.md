🇪🇸 Versión en Español: [Leer aquí](./README.es.md)

<div align="center">

# Explora Spaces+

![Android](https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.8-7F52FF?style=flat-square&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-Backend-FFCA28?style=flat-square&logo=firebase&logoColor=black)
![Room](https://img.shields.io/badge/Room-SQLite-4479A1?style=flat-square&logo=sqlite&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-Networking-673AB7?style=flat-square&logo=square&logoColor=white)

</div>

<br />

### Created by Diego Rivera

## 📋 Executive Summary

**Explora Spaces+** is a native Android application designed to serve as an interactive encyclopedia for the "Final Space" animated series. It addresses the need for a unified platform where fans can access character information, manage their personal favorites offline, and contribute custom content to a cloud-based community database.

By integrating robust local persistence with cloud synchronization, the app ensures a seamless user experience regardless of network connectivity, while providing secure authentication and real-time data updates.

## ✨ Key Features

*   **External API Integration:** Consumes the **Final Space API** via Retrofit to fetch and display comprehensive character data.
*   **Offline Persistence (Favorites):** Utilizes **Room Database** (SQLite abstraction) to allow users to save and access their favorite characters without an internet connection.
*   **Cloud Synchronization:** Features a **Firebase** integration (Firestore & Storage) enabling users to create custom characters, upload images, and sync data across devices.
*   **Secure Authentication:** Implements **Firebase Auth** supporting both Email/Password and **Google Sign-In** flows.
*   **Modern UI Architecture:** Built with Android Jetpack components, including **Navigation Component** for seamless transitions and **ViewBinding** for type-safe view interaction.

## 🏗️ Architecture

This project is built using the **MVVM (Model-View-ViewModel)** architectural pattern, ensuring a clean separation of concerns and testability:

1.  **View Layer (`/view`):**
    *   Activities (`MainActivity`, `InicioActivity`) and Fragments (`ExploraListFragment`, `DetailFragment`).
    *   Responsible for rendering UI and observing LiveData from ViewModels.
2.  **ViewModel Layer (`/view/fragments`):**
    *   `ListViewModel`: Manages API data flow.
    *   `FavoriteViewModel`: Handles local database interactions.
    *   `FirestoreViewModel`: Manages cloud data synchronization.
    *   Acts as a bridge, holding UI state and surviving configuration changes.
3.  **Model/Repository Layer (`/data`):**
    *   **Repository Pattern:** `PersonajesRepository` arbitrates between the Remote Data Source (Retrofit) and Local Data Source (Room).
    *   **Retrofit Services:** Interfaces defining API endpoints.
    *   **DAO:** Data Access Objects for Room database operations.

## 🛠️ Tech Stack

*   **Language:** Kotlin
*   **Core:** Android SDK (Min SDK 24, Target SDK 33)
*   **Network:** Retrofit 2, GSON, OkHttp
*   **Persistence:** Room Database, Firebase Firestore, Firebase Storage
*   **Auth:** Firebase Authentication, Google Sign-In
*   **UI/Media:** Glide (Image Loading), FancyToast, Material Design 3
*   **Concurrency:** Kotlin Coroutines

## 🚀 Installation & Setup

### Prerequisites
*   Android Studio Flamingo or newer
*   JDK 11 or higher

### Steps

1.  **Clone the repository**
    ```bash
    git clone https://github.com/your-username/explora-spaces.git
    ```

2.  **Open in Android Studio**
    Select "Open an Existing Project" and point to the cloned directory.

3.  **Firebase Configuration**
    *   The project requires a valid `google-services.json` file in the `app/` directory (an example is included, but for full functionality, ensure your SHA-1 fingerprint is registered in the Firebase Console).

4.  **Build and Run**
    *   Sync Project with Gradle Files.
    *   Select an Emulator or physical device.
    *   Click **Run 'app'**.

---
*© 2023 Diego Rivera. All Rights Reserved.*