# AGENTS.md - Codebase Guidance for AI Agents
## Read me for AI File Agents
## Project Overview
**SampleTestApplication** is a single-module Android application built with Jetpack Compose and Material3. This is a modern Android template project using declarative UI with Kotlin.

**Key Tech Stack:**
- Kotlin 2.0.21 with Compose
- Android AGP 9.0.0-rc03
- Target SDK: 36, Min SDK: 24
- Java 11 compatibility
- Gradle with Kotlin DSL and version catalogs

---

## Architecture & Components

### Single Activity Architecture
- **Entry Point**: `MainActivity.kt` (single activity using Compose for all UI)
- **UI Layer**: Built entirely with Jetpack Compose composables
- **Theme**: Material3 with dynamic colors support (Android 12+)
- **Main Components**:
  - `MainActivity`: ComponentActivity extending entry point
  - `Greeting()`: Example composable demonstrating basic UI pattern
  - `SampleTestApplicationTheme`: Material3 theme composable with dynamic color support

### Project Structure
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/android/sampletestapplication/
│   │   │   ├── MainActivity.kt          # Entry activity & main UI
│   │   │   └── ui/theme/                # Material3 theme setup
│   │   └── res/                         # Resources: strings, colors, icons
│   ├── test/                            # Local JUnit tests (host-side)
│   └── androidTest/                     # Instrumented tests (device/emulator)
└── build.gradle.kts                     # App configuration
```

### Dependency Management
- Uses **version catalogs** (`gradle/libs.versions.toml`) - single source of truth for dependency versions
- All libraries referenced via aliases (e.g., `libs.androidx.compose.ui`)
- Centralized in `libs.versions.toml`; never hardcode versions in build files
- Key dependencies: androidx.compose.*, androidx.activity.*, androidx.lifecycle.*

---

## Build & Test Commands

### Building
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK (no ProGuard enabled currently)
./gradlew assembleRelease

# Build and run on connected device/emulator
./gradlew installDebug
```

### Testing
```bash
# Run all local unit tests (JUnit, on host JVM)
./gradlew test

# Run instrumented tests on device/emulator (Espresso framework)
./gradlew connectedAndroidTest

# Run specific test
./gradlew test -k ExampleUnitTest
```

### Gradle Properties
- Gradle JVM args: `-Xmx2048m -Dfile.encoding=UTF-8`
- AndroidX enabled: `android.useAndroidX=true`
- Kotlin code style: `official`
- Non-transitive R class: `android.nonTransitiveRClass=true`

---

## Key Development Patterns

### 1. Compose UI Pattern
The project follows standard Jetpack Compose patterns:
```kotlin
// MainActivity structure: ComponentActivity with setContent {}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Edge-to-edge rendering (Android 5.0+)
        setContent {
            SampleTestApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(name = "Android", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
```

### 2. Theme System
- **Material3 Theme**: Dynamic color support on Android 12+ via `dynamicLightColorScheme()` / `dynamicDarkColorScheme()`
- **Color Schemes**: Defined in `Color.kt` (Purple40/80, PurpleGrey40/80, Pink40/80)
- All screen content wrapped in `SampleTestApplicationTheme`
- Theme respects system dark mode preference via `isSystemInDarkTheme()`

### 3. Composable Previews
Use `@Preview` annotation for design-time preview of composables:
```kotlin
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleTestApplicationTheme {
        Greeting("Android")
    }
}
```

### 4. Testing Patterns
- **Local Tests** (`app/src/test/`): Use JUnit 4 assertions (`assertEquals()`)
- **Instrumented Tests** (`app/src/androidTest/`): Use AndroidJUnit4 runner with Espresso
- Test examples follow Android documentation patterns

---

## Critical Configuration Files

| File | Purpose | Edit When |
|------|---------|-----------|
| `app/build.gradle.kts` | App build config, SDK versions, dependencies | Adding features, updating SDKs, new libraries |
| `gradle/libs.versions.toml` | Centralized dependency versions | Updating library versions |
| `app/src/main/AndroidManifest.xml` | App permissions, activities, intent filters | Adding new activities, permissions |
| `app/src/main/java/...` | All Kotlin source code | Regular development |
| `gradle.properties` | Gradle runtime configuration | Memory/encoding issues |

---

## Integration Points & Dependencies

### External/Third-Party
- **androidx.compose**: UI framework
- **androidx.activity.compose**: Activity integration with Compose
- **androidx.lifecycle.runtime.ktx**: Lifecycle management
- **androidx.test.espresso**: Testing framework for instrumented tests
- **JUnit 4**: Unit testing framework

### AndroidX Dependencies
- Core KTX extensions
- Material3 design system
- Compose UI tooling (preview, debugging)
- Activity Compose integration

### Important Gradle Plugins
```gradle
id = "com.android.application"     // Android app build plugin
org.jetbrains.kotlin.plugin.compose // Kotlin Compose compiler plugin
```

---

## Common Development Tasks

### Add a New Composable Screen
1. Create `.kt` file in `ui/theme/` or new package
2. Define composable function with `@Composable` annotation
3. Add `@Preview` for design-time preview
4. Wrap in `SampleTestApplicationTheme` for proper theming
5. Call from `MainActivity` via `setContent {}`

### Add a New Dependency
1. Find latest version in Maven Central / Google Maven
2. Add entry to `gradle/libs.versions.toml` (versions section)
3. Add library entry referencing the version
4. Reference in `app/build.gradle.kts` using `libs.` alias
5. Sync Gradle

### Update Android SDK Target
Edit `app/build.gradle.kts`:
- `compileSdk` → For Compose, target latest available
- `targetSdk` → Supports current Android version features
- `minSdk` → Backward compatibility (currently 24 = Android 4.0)

### Add Permissions
Edit `app/src/main/AndroidManifest.xml`:
```xml
<uses-permission android:name="android.permission.PERMISSION_NAME" />
```

---

## Notes for AI Agents

1. **Always use version catalogs**: Reference dependencies via `libs.` aliases, never hardcode versions
2. **Compose is declarative**: No imperative UI updates; state drives composition
3. **One activity pattern**: This project uses single-activity with Compose navigation (no fragments)
4. **EdgeToEdge rendering**: MainActivity calls `enableEdgeToEdge()` - be aware when adding UI chrome
5. **Material3 first**: All UI should follow Material3 design system (no Material2)
6. **Dynamic colors**: Theme responds to system colors on Android 12+ - test on multiple Android versions
7. **Test structure**: Unit tests on host JVM, instrumented tests require emulator/device
8. **SDK compatibility**: Min SDK 24, Target SDK 36 - avoid newer APIs without proper version checks

---

## Build System Notes

- Project uses **multi-level Gradle**: root `build.gradle.kts` applies common config
- **Version pinning**: AGP and Kotlin versions fixed in `libs.versions.toml`
- **Repository resolution**: Strict mode enforces centralized dependency management
- **Parallel builds**: Disabled by default (suitable for this single-module project)

