import json
import os
from pathlib import Path

from openai import OpenAI

client = OpenAI(
    api_key=os.environ["DEEPSEEK_API_KEY"],
    base_url="https://api.deepseek.com",
)

tools = [
    {
        "type": "function",
        "function": {
            "name": "read_note",
            "description": "按文件名读取 docs 目录里的一篇笔记",
            "parameters": {
                "type": "object",
                "properties": {
                    "filename": {"type": "string", "description": "例如 cold.md"}
                },
                "required": ["filename"],
            },
        },
    }
]


def read_note(filename):
    path = Path("docs") / filename
    if not path.exists():
        return "没有这个文件"
    return path.read_text(encoding="utf-8")


def send(messages):
    response = client.chat.completions.create(
        model="deepseek-flash",
        messages=messages,
        tools=tools,
        extra_body={"thinking": {"type": "disabled"}},
    )
    return response.choices[0].message


messages = [{"role": "user", "content": "笔记 cold.md 里，冷启动是什么？请先读取文件。"}]
message = send(messages)
print("第一次返回", message.tool_calls)
messages.append(message)

tool = message.tool_calls[0]
args = json.loads(tool.function.arguments)
text = read_note(args["filename"])
print("读到的文件", args["filename"])
print(text)
messages.append({"role": "tool", "tool_call_id": tool.id, "content": text})

answer = send(messages)
print("最终回答", answer.content)
