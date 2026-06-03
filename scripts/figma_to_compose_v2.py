#!/usr/bin/env python3
"""
Figma to Jetpack Compose Generator - Enhanced Version
Converts Figma designs to Kotlin Compose code with better node handling
"""

import requests
import json
import sys
import os
from pathlib import Path
from typing import Dict, List, Any, Optional

# Load environment variables
from dotenv import load_dotenv

# Load .env file
env_path = Path(__file__).parent.parent / ".env"
load_dotenv(env_path)

FIGMA_API_TOKEN = os.getenv("FIGMA_API_TOKEN")
FIGMA_API_URL = "https://api.figma.com/v1"


class FigmaToCompose:
    def __init__(self, token: str):
        self.token = token
        self.headers = {
            "X-Figma-Token": token,
            "Content-Type": "application/json"
        }

    def get_file(self, file_id: str) -> Dict[str, Any]:
        """Fetch Figma file data"""
        url = f"{FIGMA_API_URL}/files/{file_id}"
        response = requests.get(url, headers=self.headers)
        if response.status_code != 200:
            print(f"❌ Error fetching file: {response.status_code}")
            print(response.text)
            sys.exit(1)
        return response.json()

    def extract_color(self, fill: Dict[str, Any]) -> str:
        """Extract color from Figma fill object"""
        if fill.get("type") == "SOLID":
            color = fill.get("color", {})
            r = int(color.get("r", 0) * 255)
            g = int(color.get("g", 0) * 255)
            b = int(color.get("b", 0) * 255)
            return f"Color(0xFF{r:02X}{g:02X}{b:02X})"
        return "Color.Gray"

    def parse_node(self, node: Dict[str, Any], depth: int = 0) -> str:
        """Parse a Figma node and convert to Compose code"""
        indent = "    " * depth
        compose_code = ""

        node_type = node.get("type", "")
        node_name = node.get("name", "Unknown")
        visible = node.get("visible", True)

        if not visible:
            return ""

        # Extract properties
        bbox = node.get("absoluteBoundingBox", {})
        width = bbox.get("width", 100)
        height = bbox.get("height", 100)

        try:
            if node_type == "TEXT":
                content = node.get("characters", "Text")
                font_size = node.get("fontSize", 16)
                style = node.get("fontStyle", "")
                weight = node.get("fontWeight", 400)

                font_weight = "FontWeight.Bold" if weight >= 700 else "FontWeight.Normal"

                compose_code += f'{indent}Text(\n'
                compose_code += f'{indent}    text = "{content}",\n'
                compose_code += f'{indent}    fontSize = {int(font_size)}.sp,\n'
                compose_code += f'{indent}    fontWeight = {font_weight},\n'
                compose_code += f'{indent}    modifier = Modifier.padding(8.dp)\n'
                compose_code += f'{indent})\n'

            elif node_type == "RECTANGLE":
                fills = node.get("fills", [])
                background_color = self.extract_color(fills[0]) if fills else "Color.Gray"

                compose_code += f'{indent}Box(\n'
                compose_code += f'{indent}    modifier = Modifier\n'
                compose_code += f'{indent}        .size({int(width)}.dp, {int(height)}.dp)\n'
                compose_code += f'{indent}        .background({background_color})\n'
                compose_code += f'{indent})\n'

            elif node_type == "FRAME" or node_type == "GROUP" or node_type == "COMPONENT":
                children = node.get("children", [])

                if children:
                    layout_mode = node.get("layoutMode", "NONE")

                    if layout_mode == "HORIZONTAL":
                        compose_code += f'{indent}Row(\n'
                    else:
                        compose_code += f'{indent}Column(\n'

                    compose_code += f'{indent}    modifier = Modifier\n'
                    compose_code += f'{indent}        .size({int(width)}.dp, {int(height)}.dp)\n'
                    compose_code += f'{indent}        .padding(8.dp)\n'
                    compose_code += f'{indent})\n{indent}{{\n'

                    for child in children:
                        compose_code += self.parse_node(child, depth + 1)

                    compose_code += f'{indent}}}\n'

            elif node_type == "ELLIPSE":
                compose_code += f'{indent}Surface(\n'
                compose_code += f'{indent}    modifier = Modifier\n'
                compose_code += f'{indent}        .size({int(width)}.dp, {int(height)}.dp)\n'
                compose_code += f'{indent}        .background(Color.Blue, RoundedCornerShape(50.dp)),\n'
                compose_code += f'{indent}    color = Color.Blue\n'
                compose_code += f'{indent})\n'

            # Recursively process children
            if "children" in node and node_type not in ["FRAME", "GROUP", "COMPONENT"]:
                for child in node.get("children", []):
                    compose_code += self.parse_node(child, depth)

        except Exception as e:
            print(f"⚠️  Skipping node '{node_name}': {str(e)}")

        return compose_code

    def generate_compose(self, file_id: str) -> str:
        """Generate Compose code from Figma file"""
        print(f"🎨 Fetching Figma design ({file_id})...")
        file_data = self.get_file(file_id)

        # Debug: Print file structure
        pages = file_data.get("document", {}).get("children", [])
        print(f"📄 Found {len(pages)} page(s)")

        compose_code = '// Auto-generated from Figma design\n'
        compose_code += '// Generated by figma_to_compose.py\n'
        compose_code += '// Add required imports to MainActivity\n\n'
        compose_code += 'import androidx.compose.foundation.background\n'
        compose_code += 'import androidx.compose.foundation.layout.*\n'
        compose_code += 'import androidx.compose.material3.*\n'
        compose_code += 'import androidx.compose.runtime.Composable\n'
        compose_code += 'import androidx.compose.ui.Modifier\n'
        compose_code += 'import androidx.compose.ui.graphics.Color\n'
        compose_code += 'import androidx.compose.ui.text.font.FontWeight\n'
        compose_code += 'import androidx.compose.ui.unit.dp\n'
        compose_code += 'import androidx.compose.ui.unit.sp\n\n'

        compose_code += '@Composable\n'
        compose_code += 'fun FigmaDesign() {\n'
        compose_code += '    Column(\n'
        compose_code += '        modifier = Modifier\n'
        compose_code += '            .fillMaxSize()\n'
        compose_code += '            .padding(16.dp),\n'
        compose_code += '        verticalArrangement = Arrangement.spacedBy(8.dp)\n'
        compose_code += '    ) {\n'

        # Parse all pages
        for page in pages:
            if page.get("type") == "CANVAS":
                page_children = page.get("children", [])
                for node in page_children:
                    compose_code += self.parse_node(node, depth=2)

        compose_code += '    }\n'
        compose_code += '}\n'

        return compose_code


def main():
    if not FIGMA_API_TOKEN:
        print("❌ Error: FIGMA_API_TOKEN not found in .env file")
        print("Run: ./setup-mcp.sh to configure")
        sys.exit(1)

    # Accept file ID from command line or use default
    file_id = sys.argv[1] if len(sys.argv) > 1 else "UEIdN36bGDTo6rUyfCerLK"

    print("━" * 70)
    print("🎨 FIGMA TO JETPACK COMPOSE CONVERTER - ENHANCED")
    print("━" * 70)

    converter = FigmaToCompose(FIGMA_API_TOKEN)

    try:
        compose_code = converter.generate_compose(file_id)

        print("\n✅ Generated Compose Code:\n")
        print(compose_code)

        # Save to file
        output_file = Path(__file__).parent.parent / "generated_figma_compose.kt"
        with open(output_file, "w") as f:
            f.write(compose_code)

        print(f"\n💾 Saved to: {output_file}")
        print("\n📝 Next Steps:")
        print("1. Review the generated code in generated_figma_compose.kt")
        print("2. Copy the FigmaDesign() function to MainActivity.kt")
        print("3. Call FigmaDesign() in your Scaffold composable")
        print("4. Add required imports from the generated file")

    except Exception as e:
        print(f"\n❌ Error: {str(e)}")
        import traceback
        traceback.print_exc()
        sys.exit(1)


if __name__ == "__main__":
    main()


