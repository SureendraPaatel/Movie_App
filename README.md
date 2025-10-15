Movie App - Android Coding Challenge
A modern Android movie application demonstrating Clean Architecture with Multi-Module design and best practices.
📱 Features

Browse movies with circular posters, titles, release dates, and ratings
Search movies by title, genre, director, or cast
Sort by title, release date, or rating (ascending/descending)
View detailed movie information with cast, director, and box office
Bookmark favorite movies with offline access

🏗️ Architecture
Multi-Module Clean Architecture with clear separation of concerns:
app/          → DI & Navigation (Dagger)
domain/       → Business Logic (Use Cases, Entities)
data/         → Data Layer (Retrofit, Room, Repository)
presentation/ → UI Layer (Compose, ViewModels)
core/         → Shared Utilities
Data Flow: UI → ViewModel → Use Case → Repository → API/Database
🛠️ Tech Stack

Language: Kotlin
UI: Jetpack Compose + Material 3
Architecture: MVVM + Clean Architecture
DI: Dagger
Networking: Retrofit + OkHttp + Gson
Database: Room (bookmarks)
Async: Coroutines + Flow
Image Loading: Coil
Navigation: Navigation Compose

🔑 Key Implementation
Domain Layer
Pure Kotlin module with business logic, use cases, and repository interfaces. No Android dependencies.
Data Layer

Remote: Retrofit API service with proper error handling
Local: Room database with TypeConverters for complex types
Repository: Combines remote and local data sources, manages bookmark state

Presentation Layer

ViewModels: State management with StateFlow
Compose UI: Declarative UI with Material 3 components
State Handling: Loading, error, and empty states with retry functionality

Dependency Injection
Dagger modules for each layer with proper scoping (@Singleton) ensuring efficient dependency management.
🚀 Setup
bashgit clone https://github.com/yourusername/movie-app.git
cd movie-app
./gradlew clean build
./gradlew installDebug
Requirements: Android Studio Hedgehog+, JDK 17, Min SDK 24
🌐 API
Base URL: https://68cc08ab716562cf507620db.mockapi.io/
GET /movies          # All movies
GET /movies/{id}     # Movie details
🧪 Testing
Unit tests implemented for:

ViewModels (state management)
Use Cases (business logic)
Repository (data operations)
Mappers (data transformations)

📊 Code Quality

Kotlin coding conventions
Clean code principles (SOLID, DRY)
Proper error handling with Result wrapper
Null safety and type safety
Comprehensive inline documentation

