# Lumine Group Assignment

A modern Android news application built with Kotlin that displays articles from the NewsAPI. 
The app follows Clean Architecture principles and implements MVVM pattern for better code organization and maintainability.
![Screenshot 2025-12-10 at 1.11.44 AM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F3w%2F0j9dcy_x4vd19vwh_nlgtm_wgh255n%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_hxxliG%2FScreenshot%202025-12-10%20at%201.11.44%E2%80%AFAM.png)
![Screenshot 2025-12-10 at 1.12.00 AM.png](..%2F..%2F..%2F..%2Fvar%2Ffolders%2F3w%2F0j9dcy_x4vd19vwh_nlgtm_wgh255n%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_byKRYo%2FScreenshot%202025-12-10%20at%201.12.00%E2%80%AFAM.png)
## 📱 Features

- **Article Listing**: Display paginated list of news articles with images, titles, descriptions, and publication dates
- **Article Details**: View detailed information about selected articles
- **Pagination**: Load more articles as you scroll (supports up to 5 pages)
- **Image Loading**: Efficient image loading and caching using Glide
- **Error Handling**: Graceful error handling with user-friendly messages
- **Modern UI**: Clean and intuitive Material Design interface

## 🛠️ Technologies Used

### Core Technologies
- **Kotlin** - Primary programming language
- **Android SDK** - Target SDK 35, Min SDK 26
- **Gradle** - Build automation tool

### Architecture & Design Patterns
- **MVVM (Model-View-ViewModel)** - Separation of concerns
- **Clean Architecture** - Layered architecture with clear boundaries
- **Repository Pattern** - Data abstraction layer
- **Use Case Pattern** - Business logic encapsulation

### Android Jetpack Components
- **ViewModel** - Lifecycle-aware data management
- **LiveData** - Observable data holder
- **ViewBinding** - Type-safe view references
- **Fragment** - Modular UI components

### Networking
- **Retrofit 2.11.0** - Type-safe HTTP client for REST API
- **Gson Converter** - JSON serialization/deserialization
- **OkHttp 4.12.0** - HTTP client with interceptors
- **HttpLoggingInterceptor** - Network request/response logging

### Asynchronous Programming
- **Kotlin Coroutines** - Asynchronous programming
- **Kotlin Flow** - Reactive streams for data
- **Dispatchers.IO** - Background thread execution

### Image Loading
- **Glide 4.16.0** - Image loading and caching library
- **Rounded Corners** - Custom image transformations

### UI Components
- **RecyclerView** - Efficient list rendering
- **Material Design Components** - Modern UI components
- **ConstraintLayout** - Flexible layout system
- **LinearLayoutManager** - List layout management


## 🏗️ Architecture

The project follows **Clean Architecture** with three main layers:

### 1. Presentation Layer
- **Fragments**: `ArticleListFragment`, `ArticleDetailsFragment`
- **ViewModels**: `MainViewModel`
- **Adapters**: `ArticleAdapter`
- **Views**: UI components and layouts

### 2. Domain Layer
- **Repository Interface**: `Repo`
- **Use Cases**: `GetPostsUseCase`
- **Business Logic**: Domain-specific operations

### 3. Data Layer
- **Repository Implementation**: `PostRepositoryImpl`
- **Remote Data Source**: `ApiService`, `RetrofitInstance`
- **Data Models**: `ArticlesDao`, `ArticleDao`, `SourceDao`

## 📂 Project Structure

```
app/src/main/java/com/example/myapplication/
├── data/
│   ├── remote/
│   │   ├── ApiService.kt          # Retrofit API interface
│   │   ├── ArticlesDao.kt         # Data models
│   │   └── RetrofitInstance.kt    # Retrofit configuration
│   └── repository/
│       └── PostRepositoryImpl.kt  # Repository implementation
├── domain/
│   ├── repository/
│   │   └── Repo.kt                 # Repository interface
│   └── usecase/
│       └── GetPostsUseCase.kt      # Business logic use case
├── presentation/
│   ├── view/
│   │   ├── adapter/
│   │   │   └── ArticleAdapter.kt   # RecyclerView adapter
│   │   ├── fragments/
│   │   │   ├── ArticleListFragment.kt
│   │   │   └── ArticleDetailsFragment.kt
│   │   └── MainActivity.kt
│   └── viewmodel/
│       └── MainViewModel.kt        # ViewModel for UI logic
└── utility/
    └── CommonUtility.kt            # Utility functions

