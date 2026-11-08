import json
from pathlib import Path

src = Path(__file__).with_name("input.txt")
out = Path(__file__).with_name("out.json")

text = src.read_text(encoding="utf-8")
counts: dict[str, int] = {}
for w in text.split():
    counts[w] = counts.get(w, 0) + 1

with out.open("w", encoding="utf-8") as f:
    json.dump(counts, f, ensure_ascii=False, indent=2)

print(out.read_text(encoding="utf-8"))
