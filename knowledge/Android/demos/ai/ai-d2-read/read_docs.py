from pathlib import Path

docs = Path("docs")
for path in docs.iterdir():
    print(path.name)

print("---")
print(Path("docs/cold.md").read_text(encoding="utf-8"))
