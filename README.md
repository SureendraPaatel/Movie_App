# Movie App - Android Coding Challenge

A modern Android movie application built with **Clean Architecture** and **Multi-Module** design, demonstrating best practices in Android development.

##  Features

- **Movie List**: Browse movies with circular poster images, titles, release dates, and ratings
- **Search**: Find movies by title, genre, director, or cast
- **Sorting**: Sort movies by title, release date, or rating (ascending/descending)
- **Movie Details**: View comprehensive movie information including cast, director, box office, and more
- **Bookmarks**: Save favorite movies locally with persistent storage
- **Offline Support**: Access bookmarked movies without internet connection

##  Architecture

This project follows **Clean Architecture** principles with a **Multi-Module** structure for separation of concerns, testability, and scalability.

### Module Structure

```
MovieApp/
├── app/                    # Application module (DI, Navigation)
├── domain/                 # Business logic (Use Cases, Entities, Repository Interfaces)
├── data/                   # Data layer (Repository Impl, API, Database)
├── presentation/           # UI layer (ViewModels, Compose Screens)
└── core/                   # Shared utilities and common code
```

### Architecture Layers

```
┌─────────────────────────────────────────────────────────────┐
│                     Presentation Layer                       │
│         (UI Components, ViewModels, Compose Screens)         │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                       Domain Layer                           │
│              (Use Cases, Business Logic, Entities)           │
└──────────────────────────┬──────────────────────────────────┘
                           │
┌──────────────────────────▼──────────────────────────────────┐
│                        Data Layer                            │
│         (Repository, API Service, Local Database)            │
└─────────────────────────────────────────────────────────────┘
```

##  Tech Stack

### Language & Framework
- **Kotlin** - 100% Kotlin codebase
- **Jetpack Compose** - Modern declarative UI
- **Material 3** - Latest design system

### Architecture & Design Patterns
- **Clean Architecture** - Separation of concerns
- **MVVM** - Model-View-ViewModel pattern
- **Multi-Module** - Modular architecture
- **Repository Pattern** - Single source of truth
- **Use Cases** - Business logic encapsulation

### Dependency Injection
- **Dagger** - Compile-time dependency injection

### Asynchronous Programming
- **Kotlin Coroutines** - Asynchronous operations
- **Flow** - Reactive data streams
- **StateFlow** - State management

### Networking & Data
- **Retrofit** - REST API client
- **Gson** - JSON serialization
- **OkHttp** - HTTP client with logging interceptor

### Local Storage
- **Room Database** - SQLite abstraction for bookmarks
- **Type Converters** - Complex data type handling

### Image Loading
- **Coil** - Image loading and caching

### Navigation
- **Navigation Compose** - Type-safe navigation

##  Project Structure

### Domain Module (`:domain`)
Pure Kotlin module with no Android dependencies
```kotlin
domain/
├── model/                  # Domain entities (Movie, MovieDetails, SortOption)
├── repository/             # Repository interfaces
└── usecase/               # Business logic use cases
    ├── GetMoviesUseCase
    ├── GetMovieDetailsUseCase
    ├── SearchMoviesUseCase
    ├── SortMoviesUseCase
    └── Bookmark operations
```

### Data Module (`:data`)
Implementation of data operations
```kotlin
data/
├── api/                   # Retrofit API service
│   └── model/            # API response models
├── database/             # Room database
│   ├── dao/             # Data Access Objects
│   └── entity/          # Room entities
├── repository/          # Repository implementations
├── mapper/             # Data mappers (API ↔ Domain ↔ DB)
└── source/
    ├── remote/         # Remote data source
    └── local/          # Local data source
```

### Presentation Module (`:presentation`)
UI components and ViewModels
```kotlin
presentation/
├── ui/
│   ├── screen/
│   │   ├── movielist/      # Movie list screen
│   │   ├── moviedetail/    # Movie detail screen
│   │   └── bookmarks/      # Bookmarks screen
│   ├── component/          # Reusable UI components
│   └── theme/             # Material 3 theming
└── viewmodel/             # ViewModels with state management
```

### App Module (`:app`)
Application entry point with DI setup
```kotlin
app/
├── di/
│   ├── component/         # Dagger components
│   ├── module/           # DI modules
│   └── scope/            # Custom scopes
├── navigation/           # Navigation setup
└── MovieApplication.kt   # Application class
```

##  Data Flow

```
UI (Compose) 
    ↓ User Action
ViewModel 
    ↓ Calls
Use Case 
    ↓ Executes
Repository 
    ↓ Fetches from
API / Database 
    ↓ Maps to
Domain Model 
    ↓ Emits via Flow
ViewModel 
    ↓ Updates
UI State 
    ↓ Renders
UI (Compose)
```

##  Key Features Implementation

### 1. Movie List Screen
- **Circular Images**: Movie posters in circular shape using `CircleShape` clip
- **Search Bar**: Real-time filtering by title, genre, director, or cast
- **Sort Dialog**: Material 3 dialog with radio buttons for sort options
- **Lazy Loading**: Efficient list rendering with `LazyColumn`

### 2. Movie Detail Screen
- **Rich Information**: Full movie details including genres, cast, director, box office
- **Bookmark Toggle**: Add/remove bookmarks with visual feedback
- **Formatted Data**: Duration (2h 30m), Box Office ($500M), Date formatting

### 3. Bookmark Feature
- **Room Database**: Local persistence with TypeConverters for complex types
- **Reactive Updates**: Flow-based bookmark status across screens
- **Offline Access**: View bookmarked movies without network

### 4. State Management
- **StateFlow**: Reactive UI state updates
- **Error Handling**: Proper error states with retry functionality
- **Loading States**: Progress indicators during data fetch
- **Empty States**: User-friendly messages for no data scenarios

##  Testing Approach

### Unit Tests (Mandatory)
- **ViewModel Tests**: State management and business logic
- **Use Case Tests**: Business rules validation
- **Repository Tests**: Data operations with mocked dependencies
- **Mapper Tests**: Data transformation accuracy

### Test Structure
```kotlin
// Example ViewModel Test
@Test
fun `when getMovies succeeds, state should contain movies`() = runTest {
    // Given
    val movies = listOf(testMovie1, testMovie2)
    coEvery { getMoviesUseCase() } returns flowOf(Result.success(movies))
    
    // When
    viewModel.loadMovies()
    
    // Then
    assertEquals(movies, viewModel.uiState.value.movies)
    assertFalse(viewModel.uiState.value.isLoading)
}
```

##  Dependencies

```gradle
dependencies {
    // Compose
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.material3:material3")
    
    // Dagger
    implementation("com.google.dagger:dagger:2.48")
    kapt("com.google.dagger:dagger-compiler:2.48")
    
    // Coroutines & Flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    
    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
}
```

##  Setup & Installation

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Android SDK 34
- Minimum SDK 24

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/movie-app.git
   cd movie-app
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Gradle**
   - Wait for Gradle sync to complete
   - Download all dependencies

4. **Build and Run**
   ```bash
   ./gradlew clean build
   ./gradlew installDebug
   ```

##  API Configuration

The app uses the following API endpoint:
```
Base URL: https://68cc08ab716562cf507620db.mockapi.io/
Endpoints:
  - GET /movies          # Get all movies
  - GET /movies/{id}     # Get movie details
```

##  Screens

### Movie List Screen
- Displays all movies in a scrollable list
- Search bar for filtering
- Sort button with dialog options
- Bookmark button on each movie card
- Navigation to bookmarks screen

### Movie Detail Screen
- Large movie poster
- Title, rating, and duration
- Release date and genres
- Director and cast information
- Box office earnings
- Full description/overview
- Bookmark toggle button

### Bookmarks Screen
- List of all bookmarked movies
- Same card design as movie list
- Remove bookmark functionality
- Empty state when no bookmarks

##  Design Decisions

### UI/UX
- **Material 3 Design**: Modern, clean interface following Material Design guidelines
- **Circular Images**: Assignment requirement for movie poster thumbnails
- **Card-based Layout**: Better visual hierarchy and touch targets
- **Color Scheme**: Primary color for bookmarked items, golden for ratings
- **Typography**: Clear hierarchy with bold titles and readable body text

### Architecture
- **Multi-Module**: Better separation, faster builds, reusability
- **Clean Architecture**: Testable, maintainable, independent of frameworks
- **Repository Pattern**: Single source of truth, easy to swap data sources
- **Use Cases**: Testable business logic, follows SRP (Single Responsibility)

### Performance
- **Lazy Loading**: Efficient list rendering with `LazyColumn`
- **Image Caching**: Coil handles caching automatically
- **Flow**: Reactive updates without unnecessary recompositions
- **Database Indexing**: Room entity with indexed primary keys

##  Code Quality

- **Kotlin Conventions**: Following official Kotlin coding conventions
- **Clean Code**: Meaningful names, single responsibility, DRY principle
- **Documentation**: Comprehensive comments and KDoc
- **Error Handling**: Proper try-catch blocks and Result wrappers
- **Null Safety**: Leveraging Kotlin's null safety features

##  Known Issues & Future Improvements

### Future Enhancements
- [ ] Pagination for large movie lists
- [ ] Pull-to-refresh functionality
- [ ] Movie trailers integration
- [ ] User reviews and ratings
- [ ] Advanced filters (genre, year, rating range)
- [ ] Dark mode support
- [ ] Animations and transitions
- [ ] Offline-first architecture with sync

## 👨‍💻 Author

**Your Name**
- GitHub: [@SureendraPaatel](https://github.com/SureendraPaatel)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/SureendraPaatel)
- Email: Sureendra.Paatel@gmail.com

## 📄 License

This project is created for the WSA Android Coding Challenge.

##  Acknowledgments

- API provided by MockAPI
- Images from Picsum Photos
- Material Design guidelines by Google
- Android Jetpack libraries

---

**Built with ❤️ using modern Android development practices**
