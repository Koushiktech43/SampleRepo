import os
import requests
import json
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

def update_confluence_page():
    # Configuration
    domain = os.getenv("CONFLUENCE_DOMAIN", "koushikradhakrishnan92.atlassian.net")
    email = os.getenv("CONFLUENCE_EMAIL", "koushikradhakrishnan92@gmail.com")
    api_token = os.getenv("CONFLUENCE_API_TOKEN")
    page_id = "21233665"
    
    if not api_token:
        print("Error: CONFLUENCE_API_TOKEN not found in .env file.")
        return

    base_url = f"https://{domain}/wiki/api/v2/pages/{page_id}"
    auth = (email, api_token)
    
    # 1. Get current page details (needed for version number and existing content)
    print(f"Fetching current version of page {page_id}...")
    response = requests.get(base_url, auth=auth)
    
    if response.status_code != 200:
        print(f"Failed to fetch page: {response.status_code}")
        print(response.text)
        return
    
    page_data = response.json()
    current_version = page_data['version']['number']
    
    # 2. Get current content (Body)
    body_response = requests.get(f"{base_url}?body-format=storage", auth=auth)
    current_body = body_response.json().get('body', {}).get('storage', {}).get('value', "")

    # 3. Prepare new content
    # Updating the title and content
    new_title = "Hello World from AI"
    new_content = f"<h1>{new_title}</h1>\n{current_body}"
    
    # 4. Update the page
    print(f"Updating page to version {current_version + 1}...")
    update_data = {
        "id": page_id,
        "status": "current",
        "title": new_title,
        "body": {
            "storage": {
                "value": new_content,
                "representation": "storage"
            }
        },
        "version": {
            "number": current_version + 1,
            "message": "Updated title and content via AI script"
        }
    }
    
    headers = {
        "Accept": "application/json",
        "Content-Type": "application/json"
    }
    
    put_response = requests.put(base_url, data=json.dumps(update_data), headers=headers, auth=auth)
    
    if put_response.status_code == 200:
        print("✅ Successfully updated Confluence page!")
        print(f"New Title: {new_title}")
        print(f"Link: https://{domain}/wiki/spaces/~63d1f16b16dfc2b1fbcadbc0/pages/{page_id}")
    else:
        print(f"❌ Failed to update page: {put_response.status_code}")
        print(put_response.text)

if __name__ == "__main__":
    update_confluence_page()
