# AI 面试题库

> 只覆盖这条实战。题号 **AI1–**。每题都有出处。  
> 配合 [AI学习计划](AI学习计划.md)（练习命令见 [AI练习册](AI练习册.md)）。答歪的进 [AI错题库](AI错题库.md)，那一页现在是空的。

| 天 | 必刷 |
|----|------|
| D0 | AI1 |
| D2 | AI2 |
| D3 | AI3 |
| D4 | AI4 |
| D6 | AI5 |
| D8 | AI6 |
| D10 | AI7 |
| D11 | AI8 |
| D12 | AI1–AI8 |
| D13 | [错题库](AI错题库.md) |

## 必会

### AI1

场景：第一次运行自己的文件。

要说的那句：在 `ai-d0-hello` 里执行 `python3 hello.py`，屏幕是 `你好，这是第一行`。

参考：[Python 教程](https://docs.python.org/3/tutorial/)

### AI2

场景：笔记正文不写在代码里。

要说的那句：`Path("docs/cold.md").read_text` 读出文件里的两句。

参考：[Python 教程](https://docs.python.org/3/tutorial/)

### AI3

场景：第一次把问题发给模型。

要说的那句：`base_url` 是 `https://api.deepseek.com`。屏幕上要有回答和耗时。

参考：[DeepSeek 首次调用](https://api-docs.deepseek.com/zh-cn/)

### AI4

场景：模型要看 `cold.md`。

要说的那句：模型只返回 `read_note`。文件是 Python 读的。

参考：[DeepSeek 工具调用](https://api-docs.deepseek.com/zh-cn/guides/tool_calls)

### AI5

场景：问题在笔记里找不到。

要说的那句：程序打印 `不知道`。

参考：[RAG Agent](https://docs.langchain.com/oss/python/langgraph/agentic-rag)

### AI6

场景：用浏览器调用智能问答。这是第 1 个 Demo。

要说的那句：`POST /ask` 返回 `answer`、`filename`、`elapsed_ms`。

参考：[FastAPI 第一步](https://fastapi.tiangolo.com/tutorial/first-steps/)

### AI7

场景：用本人照片做一条短视频。这是第 2 个 Demo。

要说的那句：提交本人头像和自己写的口播稿，本目录得到 `avatar.mp4`。

参考：[万相图生视频](https://help.aliyun.com/zh/model-studio/image-to-video-general-api-reference)

### AI8

场景：在 Android 里问同一套资料。这是第 3 个 Demo。

要说的那句：界面只发一次 `/ask`，并显示回答、文件名和耗时。

参考：[连接到网络](https://developer.android.com/training/basics/network-ops/connecting)
