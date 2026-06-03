# Development Guidelines

General development practices and standards for SampleTestApplication.

## Code Style

- Use Kotlin as the primary language
- Follow [Kotlin Official Code Style](https://kotlinlang.org/docs/coding-conventions.html)
- Keep functions and classes focused and below 300 lines
- Use meaningful variable and function names

## Project Structure

- UI components in `ui/` package
- Business logic in ViewModels
- Data access in Repository layer
- Domain logic in UseCases

## Dependencies

- Reference all dependencies via version catalogs (`libs.` aliases)
- Update versions only in `gradle/libs.versions.toml`
- Never hardcode library versions in build files

## Git Workflow

- Create feature branches: `feature/description`
- Create bugfix branches: `bugfix/description`
- Commit messages should be descriptive
- Keep commits atomic and focused

## Documentation

- Document public functions and classes with KDoc
- Update README when adding major features
- Add comments for complex logic

