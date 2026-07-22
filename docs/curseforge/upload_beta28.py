import json, uuid, urllib.request

boundary = uuid.uuid4().hex
version = "0.0.0-beta.28"
release_type = "beta"

metadata = {
    "displayName": f"Teleport Animation ({version})",
    "changelog": open(f"docs/curseforge/versions/{version}.md", "r", encoding="utf-8").read(),
    "changelogType": "html",
    "gameVersionNames": ["Client", "Server", "26.1.2", "NeoForge"],
    "releaseType": release_type
}

with open(f"build/libs/teleport_animation-26.1.2-neoforge-{version}.jar", "rb") as f:
    jar_data = f.read()

meta_bytes = json.dumps(metadata, ensure_ascii=False).encode("utf-8")

body = b""
body += f"--{boundary}\r\n".encode()
body += b'Content-Disposition: form-data; name="metadata"\r\n'
body += b"Content-Type: application/json\r\n\r\n"
body += meta_bytes + b"\r\n"
body += f"--{boundary}\r\n".encode()
body += f'Content-Disposition: form-data; name="file"; filename="teleport_animation-26.1.2-neoforge-{version}.jar"\r\n'.encode()
body += b"Content-Type: application/java-archive\r\n\r\n"
body += jar_data + b"\r\n"
body += f"--{boundary}--\r\n".encode()

req = urllib.request.Request(
    f"https://minecraft.curseforge.com/api/projects/1608291/upload-file",
    data=body,
    headers={
        "X-Api-Token": "ee776b0a-ee95-4850-b554-06be02a8657f",
        "Content-Type": f"multipart/form-data; boundary={boundary}"
    },
    method="POST"
)

resp = urllib.request.urlopen(req)
print(resp.read().decode())
