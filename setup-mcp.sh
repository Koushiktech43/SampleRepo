#!/bin/bash

# MCP Setup Script for SampleTestApplication
# This script helps configure Model Context Protocol (MCP) for the project

set -e

echo "🚀 SampleTestApplication - MCP Setup"
echo "===================================="
echo ""

# Check if .env already exists
if [ -f ".env" ]; then
    echo "✅ .env file already exists"
else
    echo "📝 Creating .env file from .env.example..."
    cp .env.example .env
    echo "✅ .env file created"
fi

echo ""
echo "📋 Next Steps:"
echo "1. Edit .env file and add your Figma API token:"
echo "   nano .env"
echo ""
echo "2. Get your Figma API token:"
echo "   - Visit: https://www.figma.com/settings/tokens"
echo "   - Create a new personal access token"
echo "   - Copy the token to .env file"
echo ""
echo "3. Source the environment variables:"
echo "   source .env"
echo ""
echo "4. Verify token is loaded:"
echo "   echo \$FIGMA_API_TOKEN"
echo ""
echo "5. Restart your JetBrains IDE to activate MCP"
echo ""

echo "📚 Read the setup guide for more details:"
echo "   Open: instructions/MCP_SETUP.md"
echo ""

echo "===================================="
echo "✨ MCP setup complete!"

