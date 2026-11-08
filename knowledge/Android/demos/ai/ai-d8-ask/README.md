# D8 · 智能问答

这是第 1 个 Demo。

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d8-ask
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install "fastapi[standard]" openai
export DEEPSEEK_API_KEY="你的密钥"
fastapi dev main.py
```

浏览器打开 http://127.0.0.1:8000/docs 。`POST /ask` 的请求体：

```json
{"text": "冷启动是什么？"}
```

做完标准：响应里有 `answer`、`filename` 为 `cold.md`、`elapsed_ms`。再发 `{"text":"天气"}`，`answer` 是 `不知道`。

出处：https://fastapi.tiangolo.com/tutorial/first-steps/
