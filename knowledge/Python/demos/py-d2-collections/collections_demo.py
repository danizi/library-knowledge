text = "cold cold warm cold hot warm"
words = text.split()
counts: dict[str, int] = {}
for w in words:
    counts[w] = counts.get(w, 0) + 1

top = sorted(counts.items(), key=lambda kv: kv[1], reverse=True)
print(top)
print([w for w, n in top if n >= 2])
