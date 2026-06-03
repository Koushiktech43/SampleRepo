# Architecture Guide

MVVM (Model-View-ViewModel) architecture for SampleTestApplication.

## Architecture Layers

### 1. UI Layer (Presentation)
- **Location**: `ui/` package with Compose composables
- **Responsibility**: Display UI, handle user interactions
- **Rules**:
  - No business logic
  - Reactive to ViewModel state
  - Display data as-is from ViewModel

### 2. ViewModel Layer
- **Location**: `viewmodel/` package
- **Responsibility**: State management, business logic orchestration
- **Rules**:
  - No Android UI class references
  - Expose UI state as Flow/StateFlow
  - Communicate with Domain layer via UseCases

### 3. Domain Layer
- **Location**: `domain/` package with UseCase classes
- **Responsibility**: Business logic and rules
- **Rules**:
  - Platform-independent (pure Kotlin)
  - Encapsulate single business workflows
  - Return domain models

### 4. Data Layer
- **Location**: `data/` package with Repository and DataSources
- **Responsibility**: Data access and persistence
- **Rules**:
  - Single source of truth
  - Abstract data sources
  - Handle caching and offline support

## Data Flow

```
User Interaction → UI → ViewModel → UseCase → Repository → Data Source
                                 ↓
                            State Update
                                 ↓
                        UI Recomposition
```

## Dependency Injection

- Use **Hilt** for dependency injection
- Create modules in `di/` package
- Inject dependencies at ViewModel and Repository levels
- Avoid Service Locator pattern

## State Management

Use sealed classes for UI states:

```kotlin
sealed class UiState {
    object Loading : UiState()
    data class Success<T>(val data: T) : UiState()
    data class Error(val message: String) : UiState()
}
```

