import json,uuid,urllib.request
b=uuid.uuid4().hex;v="0.0.0-beta.36";rt="beta"
m={"displayName":f"Teleport Animation ({v})","changelog":"<h1>v0.0.0-beta.36</h1><h2>Fix</h2><ul><li><strong>Chunk rendering</strong> — dynamic ViewArea method search finds repositionCamera regardless of runtime name/signature.</li><li>Removed verbose diagnostic logs.</li></ul>","changelogType":"html","gameVersionNames":["Client","Server","26.1.2","NeoForge"],"releaseType":rt}
with open(f"build/libs/teleport_animation-26.1.2-neoforge-{v}.jar","rb") as f:j=f.read()
mb=json.dumps(m,ensure_ascii=False).encode("utf-8")
bd=b"--%b\r\n"%b.encode()+b'Content-Disposition: form-data; name="metadata"\r\nContent-Type: application/json\r\n\r\n'+mb+b"\r\n"
bd+=b"--%b\r\n"%b.encode()+b'Content-Disposition: form-data; name="file"; filename="teleport_animation-26.1.2-neoforge-%s.jar"\r\nContent-Type: application/java-archive\r\n\r\n'%v.encode()+j+b"\r\n"
bd+=b"--%b--\r\n"%b.encode()
r=urllib.request.Request("https://minecraft.curseforge.com/api/projects/1608291/upload-file",data=bd,headers={"X-Api-Token":"ee776b0a-ee95-4850-b554-06be02a8657f","Content-Type":f"multipart/form-data; boundary={b}"},method="POST")
print(urllib.request.urlopen(r).read().decode())
