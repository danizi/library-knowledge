# D11 · Android 业务问答

这是第 3 个 Demo。先在另一个终端跑 D8：

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d8-ask
source .venv/bin/activate
export DEEPSEEK_API_KEY="你的密钥"
fastapi dev main.py
```

用 Android Studio 打开本目录 `ai-d11-android`。模拟器运行，点「提问」。

做完标准：界面上出现回答、`cold.md` 和一个数字。真机把 `MainActivity.kt` 里的 `10.0.2.2` 改成电脑的局域网 IP。

出处：https://developer.android.com/training/basics/network-ops/connecting
