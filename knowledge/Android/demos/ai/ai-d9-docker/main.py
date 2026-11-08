import os
import time
from pathlib import Path

from fastapi import FastAPI
from openai import OpenAI
from pydantic import BaseModel

app = FastAPI()
client = OpenAI(
    api_key=os.environ["DEEPSEEK_API_KEY"],
    base_url="https://api.deepseek.com",
)


class Question(BaseModel):
    text: str


def find(question):
    best_name = ""
    best_text = ""
    best_score = 0
    for path in Path("docs").glob("*.md"):
        text = path.read_text(encoding="utf-8").strip()
        score = sum(1 for word in question if word in text)
        if score > best_score:
            best_score = score
            best_name = path.name
            best_text = text
    if best_score < 2:
        return "", ""
    return best_name, best_text


@app.post("/ask")
def ask(body: Question):
    name, text = find(body.text)
    if not text:
        return {"answer": "不知道", "filename": "", "elapsed_ms": 0}
    started = time.perf_counter()
    response = client.chat.completions.create(
        model="deepseek-flash",
        messages=[
            {"role": "system", "content": "只根据笔记回答。"},
            {"role": "user", "content": text + "\n\n问题：" + body.text},
        ],
        extra_body={"thinking": {"type": "disabled"}},
    )
    elapsed = int((time.perf_counter() - started) * 1000)
    return {
        "answer": response.choices[0].message.content,
        "filename": name,
        "elapsed_ms": elapsed,
    }
