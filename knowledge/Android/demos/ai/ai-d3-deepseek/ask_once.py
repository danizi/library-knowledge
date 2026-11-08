import os
import time
from openai import OpenAI

client = OpenAI(
    api_key=os.environ["DEEPSEEK_API_KEY"],
    base_url="https://api.deepseek.com",
)

started = time.perf_counter()
response = client.chat.completions.create(
    model="deepseek-flash",
    messages=[{"role": "user", "content": "用一句话介绍你自己"}],
    extra_body={"thinking": {"type": "disabled"}},
)
elapsed = int((time.perf_counter() - started) * 1000)
print(response.choices[0].message.content)
print("耗时", elapsed, "毫秒")
