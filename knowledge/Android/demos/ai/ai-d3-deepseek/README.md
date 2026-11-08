# D3

密钥从 https://platform.deepseek.com/api_keys 申请。不要写进文件。

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d3-deepseek
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install openai
export DEEPSEEK_API_KEY="你的密钥"
python3 ask_once.py
```

模型名以 https://api-docs.deepseek.com/zh-cn/ 当前「首次调用」页为准。不是 `deepseek-flash` 时，只改 `ask_once.py` 里的 `model=`。

做完标准：一句回答，再一行 `耗时 … 毫秒`。
