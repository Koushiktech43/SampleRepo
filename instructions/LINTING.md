# Kotlin Code Quality & Linting

KtLint is used to enforce consistent Kotlin code style and formatting in this project.

## What is KtLint?

KtLint is a static code linter and formatter for Kotlin. It enforces coding standards, catches potential bugs, and ensures code consistency across the project.

## Available Gradle Tasks

### 1. Check Code Style (Read-only)

```bash
./gradlew ktlintCheck
```

This task checks if your Kotlin code follows the style guide **without modifying files**. It will report any style violations found.

**Use this when:**
- You want to verify code quality before committing
- CI/CD pipeline validation
- Pre-commit checks

### 2. Auto-format Code

```bash
./gradlew ktlintFormat
```

This task automatically fixes all automatically-fixable style violations. Some issues may still require manual fixes.

**Use this when:**
- Setting up a new project
- Bulk formatting existing code
- Fixing all auto-fixable issues at once

## Code Style Rules

The code style is configured in `.editorconfig` file and includes:

- **Indentation:** 4 spaces
- **Max line length:** 120 characters
- **Naming conventions:**
  - Classes: `PascalCase` (e.g., `UserViewModel`)
  - Functions/Variables: `camelCase` (e.g., `getUserData()`)
  - Constants: `UPPER_SNAKE_CASE` (e.g., `MAX_RETRY_COUNT`)

## Integration with Development Workflow

### Pre-commit Hook (Recommended)

Create `.git/hooks/pre-commit`:

```bash
#!/bin/bash
./gradlew ktlintCheck
if [ $? -ne 0 ]; then
  echo "KtLint check failed. Run './gradlew ktlintFormat' to auto-fix issues."
  exit 1
fi
```

Then make it executable:

```bash
chmod +x .git/hooks/pre-commit
```

### IDE Integration

Most modern IDEs support ktlint:

- **Android Studio/IntelliJ IDEA**: Plugins available for real-time linting
- **VS Code**: ktlint extension available
- **Install IDE support**: Follow the [ktlint documentation](https://pinterest.github.io/ktlint/)

## Configuration Files

- **`.editorconfig`** - Defines code style rules
- **`app/build.gradle.kts`** - Contains gradle task definitions
- **`gradle/libs.versions.toml`** - KtLint version management

## Troubleshooting

### Task fails with "cannot find symbol"

Ensure you've synced Gradle:

```bash
./gradlew sync
```

### Some files aren't being checked

The tasks check `src/main/java/**/*.kt`. To include other directories, modify the task in `app/build.gradle.kts`:

```kotlin
"src/main/java/**/*.kt",
"src/test/java/**/*.kt"  // Add this line to check tests too
```

### Want to ignore certain rules?

Temporarily disable rules with:

```kotlin
@Suppress("ktlint:rule-name")
// or
// ktlint-disable rule-name
```

## CI/CD Integration

Add to your CI pipeline:

```yaml
- name: Check Kotlin code style
  run: ./gradlew ktlintCheck
```

## Common Violations & Fixes

| Issue | Fix |
|-------|-----|
| Line too long (>120 chars) | Break into multiple lines |
| Trailing whitespace | Remove extra spaces |
| Unused imports | ktlint auto-fixes |
| Inconsistent indentation | Run `ktlintFormat` |
| Function naming | Use `camelCase` for functions |

## More Information

- [KtLint Official Documentation](https://pinterest.github.io/ktlint/)
- [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [EditorConfig Format](https://editorconfig.org/)

