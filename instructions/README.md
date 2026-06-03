# Instructions

This folder contains general instructions and guidelines for the SampleTestApplication project.

## Contents

- **[Copilot Instructions](../github/copilot-instructions.md)** - AI and code generation guidelines
- **[Development Guidelines](./DEVELOPMENT.md)** - General development practices
- **[Architecture Guide](./ARCHITECTURE.md)** - MVVM architecture patterns
- **[Security Guidelines](./SECURITY.md)** - Security best practices
- **[Kotlin Linting & Code Quality](./LINTING.md)** - KtLint setup and usage
- **[MCP Setup Guide](./MCP_SETUP.md)** - Model Context Protocol & Figma integration

## Adding New Instructions

1. Create a new `.md` file in this directory
2. Use clear, descriptive filenames (e.g., `TESTING.md`, `DEPLOYMENT.md`)
3. Follow the markdown structure for consistency
4. Add a link to this README for easy reference

## Quick Navigation

-  General development patterns → `DEVELOPMENT.md`
- ️ Project architecture → `ARCHITECTURE.md`
-  Security practices → `SECURITY.md`
-  Code quality & linting → `LINTING.md`
-  MCP & Figma integration → `MCP_SETUP.md`
-  AI/Copilot guidelines → `../github/copilot-instructions.md`

## Quick Commands

```bash
# Check code style
./gradlew ktlintCheck

# Auto-format code
./gradlew ktlintFormat

# Run tests
./gradlew test

# Build debug APK
./gradlew assembleDebug
```