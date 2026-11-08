import os
from pathlib import Path

from openai import OpenAI

client = OpenAI(
    api_key=os.environ["DEEPSEEK_API_KEY"],
    base_url="https://api.deepseek.com",
)

chunks = []
for path in Path("docs").glob("*.md"):
    for block in path.read_text(encoding="utf-8").split("\n\n"):
        text = block.strip()
        if text:
            chunks.append((path.name, text))


def find(question):
    best_name = ""
    best_text = ""
    best_score = 0
    for name, text in chunks:
        score = sum(1 for word in question if word in text)
        if score > best_score:
            best_score = score
            best_name = name
            best_text = text
    if best_score < 2:
        return "", ""
    return best_name, best_text


def ask(question):
    name, text = find(question)
    if not text:
        print("不知道")
        return
    response = client.chat.completions.create(
        model="deepseek-flash",
        messages=[
            {"role": "system", "content": "只根据笔记回答。笔记里没有就说不知道。"},
            {"role": "user", "content": f"文件 {name}\n{text}\n\n问题：{question}"},
        ],
        extra_body={"thinking": {"type": "disabled"}},
    )
    print(name)
    print(response.choices[0].message.content)


ask("冷启动是什么？")
ask("今天天气怎么样？")
