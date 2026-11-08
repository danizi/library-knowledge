# D6

教程整页默认用别的密钥。本目录是同一件事的可运行版本：找出笔记里的一段，再交给 DeepSeek。

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d6-keyword
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install openai
export DEEPSEEK_API_KEY="你的密钥"
python3 keyword_ask.py
```

做完标准：第一题先打印 `cold.md`，回答里有「进程被杀掉」。第二题只有 `不知道`。

出处：https://docs.langchain.com/oss/python/langgraph/agentic-rag
