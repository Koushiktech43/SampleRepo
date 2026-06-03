# MCP (Model Context Protocol) Setup Guide

This guide explains how to configure and use MCP servers (like Figma) with your Android development environment.

## What is MCP?

**Model Context Protocol (MCP)** allows AI models to interact with external tools and services. In this project, we've integrated Figma MCP to enable AI-assisted UI design integration.

## Configuration Files

### 1. `.mcp.json` (Project Root)
- **Purpose**: General MCP configuration for any MCP-compatible tool
- **Location**: `/Users/koushik/Desktop/AndroidProjects/SampleTestApplication/.mcp.json`
- **Use**: IDE-agnostic MCP server definitions

### 2. `.jetbrains-ai/mcp-config.json` (JetBrains-specific)
- **Purpose**: JetBrains IDE (IntelliJ IDEA, Android Studio) specific configuration
- **Location**: `/Users/koushik/Desktop/AndroidProjects/SampleTestApplication/.jetbrains-ai/mcp-config.json`
- **Use**: Optimized for GitHub Copilot and JetBrains AI features

---

## Setup Instructions

### Step 1: Set Figma API Token

You need to add your Figma API token as an environment variable:

**On macOS/Linux:**
```bash
export FIGMA_API_TOKEN="your_figma_api_token_here"
```

**In `.env` file (recommended for projects):**
```bash
# Create .env file in project root
FIGMA_API_TOKEN=your_figma_api_token_here
```

**In Android Studio:**
1. Go to **Run → Edit Configurations**
2. Add to **Environment variables**:
   ```
   FIGMA_API_TOKEN=your_figma_api_token_here
   ```

### Step 2: Get Your Figma API Token

1. Visit [Figma Settings](https://www.figma.com/settings)
2. Go to **Personal access tokens**
3. Create a new token with these scopes:
   - `file:read` - Read access to files
   - `file:write` - Write access (optional)
   - `team:read` - Read team projects
4. Copy the token and add it to your environment

### Step 3: Enable MCP in JetBrains IDE

1. **Open IDE Settings**: Preferences → Tools → Figma (or AI/Copilot settings)
2. **Enable MCP**: Toggle "Use Model Context Protocol"
3. **Select Configuration**: Point to `.jetbrains-ai/mcp-config.json`
4. **Restart IDE** to apply changes

---

## Available Figma MCP Capabilities

Our configuration enables the following Figma operations:

| Capability | Use Case |
|-----------|----------|
| `readDesigns` | View and analyze Figma designs in AI context |
| `readComponents` | Reference design components and patterns |
| `readVariables` | Access design tokens and variables |
| `readPrototypes` | Understand interactive prototypes |

---

## Usage Examples

### Example 1: Import Figma Design to Compose UI

In your IDE, with MCP enabled, you can ask:

```
"Convert this Figma design to Jetpack Compose:
 https://www.figma.com/file/xxx/MyProject"
```

The AI will fetch the design via MCP and generate Compose code.

### Example 2: Generate UI Variables

```
"Read the Figma file and generate Kotlin data classes 
 for all design tokens/variables"
```

### Example 3: Analyze Component Structure

```
"List all components in the Figma file and suggest 
 how to structure them as Compose composables"
```

---

## MCP Server Configuration Details

### Figma MCP Server

```json
{
  "figma": {
    "enabled": true,
    "command": "npx",
    "args": ["-y", "@figma/figma-mcp"],
    "env": {
      "FIGMA_API_TOKEN": "${FIGMA_API_TOKEN}"
    },
    "timeout": 30000
  }
}
```

- **Command**: `npx -y @figma/figma-mcp` (downloads and runs latest Figma MCP)
- **Timeout**: 30 seconds (for API calls)
- **Token**: Retrieved from environment variable

---

## Troubleshooting

### Issue: "MCP server not responding"
**Solution:**
```bash
# Verify Figma MCP is installed
npx @figma/figma-mcp --version

# Check token validity
curl -H "X-Figma-Token: $FIGMA_API_TOKEN" \
  "https://api.figma.com/v1/me"
```

### Issue: "FIGMA_API_TOKEN not found"
**Solution:**
```bash
# Verify environment variable is set
echo $FIGMA_API_TOKEN

# If empty, add to shell profile
echo 'export FIGMA_API_TOKEN="your_token"' >> ~/.zshrc
source ~/.zshrc
```

### Issue: "MCP not recognized in IDE"
**Solution:**
1. Restart the IDE completely
2. Check Settings → Tools → Figma (or AI settings)
3. Ensure `.jetbrains-ai/mcp-config.json` file exists
4. Try reloading project: **File → Invalidate Caches → Restart**

---

## Security Best Practices

1. **Never commit tokens**: Add to `.gitignore`:
   ```
   .env
   .env.local
   FIGMA_API_TOKEN
   ```

2. **Use environment variables**: Don't hardcode tokens in `.mcp.json`

3. **Rotate tokens regularly**: Change your Figma API token monthly

4. **Limit token scope**: Only enable required permissions

---

## Additional Resources

- [Figma MCP Documentation](https://github.com/figma/mcp)
- [Model Context Protocol Spec](https://modelcontextprotocol.io/)
- [JetBrains AI Documentation](https://www.jetbrains.com/help/idea/ai-assistant.html)
- [Figma API Reference](https://www.figma.com/developers/api)

---

## Next Steps

1. ✅ Create Figma account (if needed)
2. ✅ Get Figma API token
3. ✅ Set environment variable `FIGMA_API_TOKEN`
4. ✅ Restart your IDE
5. ✅ Start using AI with Figma design context!
