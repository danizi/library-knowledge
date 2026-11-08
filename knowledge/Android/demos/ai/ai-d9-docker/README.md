# D9

先确认 Docker Desktop 已打开。`main.py` 与 D8 相同，放在本目录。

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d9-docker
docker build -t ai-d9-ask .
docker run --rm -p 8000:8000 -e DEEPSEEK_API_KEY="你的密钥" ai-d9-ask
```

浏览器再开 http://127.0.0.1:8000/docs ，对 `/ask` 发送 `{"text":"冷启动是什么？"}`。

做完标准：响应里仍有回答、`cold.md` 和毫秒。

出处：https://docs.docker.com/reference/dockerfile/
