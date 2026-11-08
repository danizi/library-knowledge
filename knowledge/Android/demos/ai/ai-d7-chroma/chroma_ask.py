from pathlib import Path

import chromadb

client = chromadb.PersistentClient(path="chroma_data")
collection = client.get_or_create_collection("notes")

ids = []
documents = []
for path in Path("docs").glob("*.md"):
    ids.append(path.name)
    documents.append(path.read_text(encoding="utf-8").strip())

collection.upsert(ids=ids, documents=documents)
found = collection.query(query_texts=["冷启动是什么"], n_results=1)
print(found["ids"][0][0])
print(found["documents"][0][0])
