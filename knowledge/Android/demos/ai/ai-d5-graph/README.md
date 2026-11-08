# D5

官方页默认用 Anthropic。这里只改地址和模型名。

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d5-graph
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install -U langgraph langchain langchain-openai
export DEEPSEEK_API_KEY="你的密钥"
python3 agent.py
```

做完标准：输出里有工具 `add`，以及数字 `7`。

出处：https://docs.langchain.com/oss/python/langgraph/quickstart
