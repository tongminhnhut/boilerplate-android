# 🚀 Android Compose Boilerplate

A modern Android boilerplate project built with **Jetpack Compose**, following **MVVM + Clean Architecture** principles.  
This boilerplate helps you quickly start new projects with a clean, scalable, and testable architecture.

---

## 📌 Features

- **Jetpack Compose** – Modern UI toolkit for building native UI.
- **MVVM + Clean Architecture** – Separation of concerns with well-defined layers.
- **Room Database** – Local persistence for offline-first applications.
- **Ktor Client** – Lightweight and asynchronous networking library.
- **Paging 3** – Efficiently load and display large data sets with pagination.
- **Kotlin Coroutines & Flow** – Asynchronous programming with structured concurrency.
- **Dependency Injection** – Easily switchable (Hilt or Koin).
- **Navigation Compose** – Type-safe navigation for Compose screens.


---

## 🏗️ Project Structure

app/
├── data/                  # Data Layer: repository implementations, DB, network
│   ├── local/             # Room database, DAO
│   ├── remote/            # Ktor API services
│   ├── repository/        # Repository implementations
│
├── domain/                # Domain Layer: business logic
│   ├── model/             # Domain models
│   ├── repository/        # Repository interfaces
│   ├── usecase/           # Interactors / UseCases
│
├── presentation/          # Presentation Layer: UI + ViewModels
│   ├── ui/                # Compose screens, components
│   ├── viewmodel/         # ViewModels (MVVM)
│
├── di/                    # Dependency Injection setup
├── utils/                 # Utilities, extensions
└── MainApplication.kt     # App entry point

 
---

## ⚙️ Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM + Clean Architecture
- **Networking:** Ktor Client
- **Database:** Room
- **Pagination:** Paging 3
- **Async:** Coroutines + Flow
- **DI:** Hilt or Koin (configurable)

---

## 🔌 Getting Started