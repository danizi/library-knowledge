# AI 练习册 · 14 天 Demo（可选）

主线见 [AI学习计划](AI学习计划.md)。本文件只保留按天 Demo 命令，不当主课表。

目标：没有 Python 基础的人，按天运行 Demo，做出三个项目。每天约 **3 h**，到点停。  
原则：当天屏幕上的字没出现，不做下一天。不要先读英文文档。命令在当天 Demo 的 README。

D12 默答，D13 复跑，不新建目录。  
题号 **AI1–**。卡片 **AI-001**。Demo：`knowledge/Android/demos/ai/ai-d{天}-{主题}`。

三个项目：

1. 智能问答在 `ai-d8-ask`。
2. 头像短视频在 `ai-d10-avatar`。
3. Android 业务问答在 `ai-d11-android`。

简历年限仍写实际工作年限。本机 Demo 不是已上线给真实用户。

## 学习重点

必学：打出一行字（D0）、读出笔记（D2）、DeepSeek 的回答和耗时（D3）、工具由自己的代码执行（D4）、没有依据就回答「不知道」（D6）、浏览器里的 `/ask`（D8）。

知道即可：LangGraph 计算器（D5）、Chroma（D7）、Docker 再问一次（D9）、头像视频文件（D10）、Android 界面只显示三项（D11）。

不必深挖：英文教程整章、换脸进影视画面、自动发抖音、Kubernetes、模型微调。

## 读过的材料

官方：

- https://docs.python.org/3/tutorial/
- https://developers.openai.com/api/docs/quickstart
- https://api-docs.deepseek.com/zh-cn/
- https://api-docs.deepseek.com/zh-cn/guides/tool_calls
- https://developers.openai.com/api/docs/guides/function-calling
- https://docs.langchain.com/oss/python/langgraph/quickstart
- https://docs.langchain.com/oss/python/langgraph/agentic-rag
- https://fastapi.tiangolo.com/tutorial/first-steps/
- https://docs.docker.com/reference/dockerfile/
- https://help.aliyun.com/zh/model-studio/image-to-video-general-api-reference
- https://developer.android.com/training/basics/network-ops/connecting

启事：

- [慧灵科技](https://www.zhaopin.com/jobdetail/CC409009630J40849644801.htm) 写了 Chromdb，因此 D7 单独一天
- [北京赛迪](https://www.zhaopin.com/jobdetail/CC000441110J40857983309.htm)
- [外企德科](https://www.zhaopin.com/jobdetail/CC138117190J40867740405.htm)
- [蓝犀牛](https://www.quanzhi.com/job/6a1f89481433e769ad622a5f)

## 14 天总览

| 天 | 主题 | Demo | 卡片 | 题号 |
|----|------|------|------|------|
| D0 | 打出一行字 | ai-d0-hello | AI-001,5 | AI1 |
| D1 | 列表和循环 | ai-d1-loop | AI-001,6 | |
| D2 | 读出笔记 | ai-d2-read | AI-001,7 | AI2 |
| D3 | DeepSeek 第一次调用 | ai-d3-deepseek | AI-001,8 | AI3 |
| D4 | 工具调用 | ai-d4-tool | AI-001,1 | AI4 |
| D5 | 计算器图 | ai-d5-graph | AI-001,9 | |
| D6 | 没有依据就说不知道 | ai-d6-keyword | AI-001,2 | AI5 |
| D7 | Chroma | ai-d7-chroma | AI-001,10 | |
| D8 | 智能问答 `/ask` | ai-d8-ask | AI-001 与 AI-001,11 | AI6 |
| D9 | Docker | ai-d9-docker | AI-001,12 | |
| D10 | 头像短视频 | ai-d10-avatar | AI-001,3 | AI7 |
| D11 | Android 业务问答 | ai-d11-android | AI-001,4 | AI8 |
| D12 | 默答 | 不新建 | 上面全部遮住「答」 | AI1–AI8 |
| D13 | 复跑 | 不新建 | 只看错题库 | 错题库 |

配套：[AI面试题库](AI面试题库.md) · [AI错题库](AI错题库.md)  
Demo 目录：`knowledge/Android/demos/ai/`  
每天先进入当天目录再运行。代码已经在该目录里，不要另建 `note-qa`。

---

## 每日日程

格式：`输入 → Demo → 默答`。命令在当天目录执行。屏幕上的字没出现，不做下一天。

### D0 · 打出一行字（2 h）

目录：`knowledge/Android/demos/ai/ai-d0-hello/`  
对照卡：[AI-001,5 | 运行脚本](<AI-001,5 | 运行脚本.md>)。跑完后遮住「答」，默答这一问。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 终端执行 `python3 --version` | 出现 `Python 3.`。没有就安装 https://www.python.org/downloads/macos/ 后重开终端 |
| 0:20–0:40 | 20 min | `cd` 到本目录，执行 `python3 hello.py` | 屏幕是 `你好，这是第一行` |
| 0:40–1:20 | 40 min | 打开 `hello.py`，只看 `print` 这一行 | 能指出打印的是这一句 |
| 1:20–2:00 | 40 min | 默答 AI1 | 不看题库 |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d0-hello
python3 hello.py
```

**D0 验收**：`[ ]` 屏幕是那一行中文 `[ ]` 没有改 `hello.py`

### D1 · 列表和循环（3 h）

目录：`knowledge/Android/demos/ai/ai-d1-loop/`  
对照卡：[AI-001,6 | 列表循环](<AI-001,6 | 列表循环.md>)。跑完后遮住「答」。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 打开 `loop.py`，指出 `names`、`def`、`for` | 能说出列表里有三项 |
| 0:20–0:50 | 30 min | 执行 `python3 loop.py` | 三行依次是冷启动、热启动、ANR |
| 0:50–2:00 | 70 min | 只改列表里的第三个词，再运行 | 第三行变成你改的词 |
| 2:00–3:00 | 60 min | 把第三个词改回 `ANR` | 再跑一次，仍是原来的三行 |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d1-loop
python3 loop.py
```

**D1 验收**：`[ ]` 三行都在 `[ ]` 改回后与原来一致

### D2 · 读出笔记（3 h）

目录：`knowledge/Android/demos/ai/ai-d2-read/`  
对照卡：[AI-001,7 | 读文件](<AI-001,7 | 读文件.md>)。跑完后遮住「答」，默答 AI2。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 打开 `docs/cold.md` | 能读出冷启动、热启动两句 |
| 0:30–1:10 | 40 min | 执行 `python3 read_docs.py` | 先有 `cold.md`，再有 `---`，再有那两句 |
| 1:10–2:10 | 60 min | 在 `cold.md` 末尾加一句「ANR 是主线程卡住。」再运行 | 新句子出现在输出里 |
| 2:10–3:00 | 50 min | 默答 AI2 | 不看题库 |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d2-read
python3 read_docs.py
```

**D2 验收**：`[ ]` 正文来自文件 `[ ]` AI2 不看卡能说

### D3 · DeepSeek 第一次调用（3 h）

目录：`knowledge/Android/demos/ai/ai-d3-deepseek/`  
对照卡：[AI-001,8 | 模型调用](<AI-001,8 | 模型调用.md>)。跑完后遮住「答」，默答 AI3。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 在 https://platform.deepseek.com/api_keys 申请密钥。终端执行 `export DEEPSEEK_API_KEY="你的密钥"` | `echo $DEEPSEEK_API_KEY` 能看到密钥。不要写进文件 |
| 0:40–1:20 | 40 min | `python3 -m venv .venv`，`source .venv/bin/activate`，`python3 -m pip install openai` | 行首出现 `(.venv)` |
| 1:20–2:20 | 60 min | `python3 ask_once.py` | 一句回答，再一行 `耗时 … 毫秒` |
| 2:20–3:00 | 40 min | 默答 AI3 | 能说出 `base_url` 是 `https://api.deepseek.com` |

模型名以 https://api-docs.deepseek.com/zh-cn/ 当前写的为准。不是 `deepseek-flash` 时，只改 `ask_once.py` 的 `model=`。

**D3 验收**：`[ ]` 有回答和毫秒 `[ ]` 密钥不在文件里

### D4 · 工具调用（3 h）

目录：`knowledge/Android/demos/ai/ai-d4-tool/`  
对照卡：[AI-001,1 | 工具调用](<AI-001,1 | 工具调用.md>)。跑完后遮住「答」，默答 AI4。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 确认本目录有 `docs/cold.md` | 能打开并看到两句笔记 |
| 0:30–1:00 | 30 min | 新建 `.venv`，安装 `openai`，再次 `export` 密钥 | `(.venv)` 出现 |
| 1:00–2:10 | 70 min | `python3 read_tool.py` | 先有 `read_note`，再有笔记原文，再有最终回答 |
| 2:10–3:00 | 50 min | 默答 AI4 | 能说出读文件的是 Python |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d4-tool
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install openai
export DEEPSEEK_API_KEY="你的密钥"
python3 read_tool.py
```

**D4 验收**：`[ ]` 三段输出都在 `[ ]` AI4 不看卡能说

### D5 · 计算器图（3 h）

目录：`knowledge/Android/demos/ai/ai-d5-graph/`  
对照卡：[AI-001,9 | 计算器图](<AI-001,9 | 计算器图.md>)。跑完后遮住「答」。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 建 `.venv`，安装 `langgraph langchain langchain-openai` | 安装结束没有报错 |
| 0:40–1:40 | 60 min | `export` 密钥后执行 `python3 agent.py` | 输出里有 `add` 和数字 `7` |
| 1:40–2:20 | 40 min | 打开 `agent.py`，指出 `add` 函数 | 能说出 3 和 4 是在这个函数里相加 |
| 2:20–3:00 | 40 min | 若 `init_chat_model` 报错，把报错留在终端 | 今天不改成另一道题 |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d5-graph
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install -U langgraph langchain langchain-openai
export DEEPSEEK_API_KEY="你的密钥"
python3 agent.py
```

**D5 验收**：`[ ]` 看到 `add` 和 `7` `[ ]` 报错时没有跳天

### D6 · 没有依据就说不知道（3 h）

目录：`knowledge/Android/demos/ai/ai-d6-keyword/`  
对照卡：[AI-001,2 | 检索](<AI-001,2 | 检索.md>)。跑完后遮住「答」，默答 AI5。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 打开 `docs/cold.md` | 确认仍是冷启动、热启动两句 |
| 0:30–1:00 | 30 min | 建 `.venv`，安装 `openai`，`export` 密钥 | `(.venv)` 出现 |
| 1:00–2:10 | 70 min | `python3 keyword_ask.py` | 第一题有 `cold.md` 和「进程被杀掉」。第二题只有 `不知道` |
| 2:10–3:00 | 50 min | 默答 AI5 | 不看题库 |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d6-keyword
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install openai
export DEEPSEEK_API_KEY="你的密钥"
python3 keyword_ask.py
```

**D6 验收**：`[ ]` 两题结果都对 `[ ]` AI5 能说出「不知道」

### D7 · Chroma（3 h）

目录：`knowledge/Android/demos/ai/ai-d7-chroma/`  
对照卡：[AI-001,10 | Chroma](<AI-001,10 | Chroma.md>)。跑完后遮住「答」。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 建 `.venv`，`python3 -m pip install chromadb` | 安装结束。第一次会下载小模型 |
| 0:40–1:40 | 60 min | `python3 chroma_ask.py` | 打印 `cold.md` 和笔记正文 |
| 1:40–2:20 | 40 min | 看本目录是否出现 `chroma_data` | 文件夹在 |
| 2:20–3:00 | 40 min | 下载失败就停在这一天 | 不把 D6 的结果说成 Chroma |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d7-chroma
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install chromadb
python3 chroma_ask.py
```

**D7 验收**：`[ ]` 打印笔记 `[ ]` `chroma_data` 存在

### D8 · 智能问答（3 h）

目录：`knowledge/Android/demos/ai/ai-d8-ask/`。这是第 1 个 Demo。  
对照卡：[AI-001 | 问答](<AI-001 | 问答.md>) 和 [AI-001,11 | 三项返回](<AI-001,11 | 三项返回.md>)。跑完后遮住「答」，默答 AI6。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 建 `.venv`，安装 `fastapi[standard]` 和 `openai`，`export` 密钥 | 安装结束 |
| 0:40–1:20 | 40 min | `fastapi dev main.py` | 终端留着，不要关 |
| 1:20–2:20 | 60 min | 浏览器打开 http://127.0.0.1:8000/docs ，`POST /ask` 发送 `{"text":"冷启动是什么？"}` | 有 `answer`、`filename` 为 `cold.md`、`elapsed_ms` |
| 2:20–3:00 | 40 min | 再发送 `{"text":"天气"}`，默答 AI6 | `answer` 是 `不知道` |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d8-ask
python3 -m venv .venv
source .venv/bin/activate
python3 -m pip install "fastapi[standard]" openai
export DEEPSEEK_API_KEY="你的密钥"
fastapi dev main.py
```

**D8 验收**：`[ ]` 两题响应都对 `[ ]` 服务没有关，留给 D11

### D9 · Docker（3 h）

目录：`knowledge/Android/demos/ai/ai-d9-docker/`  
对照卡：[AI-001,12 | 容器运行](<AI-001,12 | 容器运行.md>)。跑完后遮住「答」。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:20 | 20 min | 打开 Docker Desktop | 它正在运行 |
| 0:20–1:20 | 60 min | `docker build -t ai-d9-ask .` | 构建结束没有报错 |
| 1:20–2:20 | 60 min | `docker run --rm -p 8000:8000 -e DEEPSEEK_API_KEY="你的密钥" ai-d9-ask` | 容器在听 8000 |
| 2:20–3:00 | 40 min | 再开 http://127.0.0.1:8000/docs ，问冷启动 | 仍有回答、`cold.md`、毫秒 |

先停掉 D8 占着的 8000 端口，再跑上面的 `docker run`。

**D9 验收**：`[ ]` 容器里问到三项 `[ ]` 命令写在本目录 README 末尾

### D10 · 头像短视频（3 h）

目录：`knowledge/Android/demos/ai/ai-d10-avatar/`。这是第 2 个 Demo。  
对照卡：[AI-001,3 | 头像视频](<AI-001,3 | 头像视频.md>)。跑完后遮住「答」，默答 AI7。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:30 | 30 min | 本人照片存为该目录 `avatar.jpg`。改 `script.txt` 为你的三句话 | 稿子里没有片名、角色名、影视对白 |
| 0:30–1:00 | 30 min | 准备百炼密钥和业务空间 ID | `DASHSCOPE_API_KEY` 和 `WORKSPACE_ID` 已 export |
| 1:00–2:30 | 90 min | `python3 avatar.py` | 终端最后是 `已保存 avatar.mp4` |
| 2:30–3:00 | 30 min | 用播放器打开 `avatar.mp4`，默答 AI7 | 画面在动。程序没有打开抖音 |

```bash
cd /Users/a1234/xiaomin/library-knowledge/knowledge/Android/demos/ai/ai-d10-avatar
export DASHSCOPE_API_KEY="你的百炼密钥"
export WORKSPACE_ID="你的业务空间ID"
python3 avatar.py
```

**D10 验收**：`[ ]` 视频能播 `[ ]` 没有影视画面和角色名

### D11 · Android 业务问答（3 h）

目录：`knowledge/Android/demos/ai/ai-d11-android/`。这是第 3 个 Demo。  
对照卡：[AI-001,4 | Android调用](<AI-001,4 | Android调用.md>)。跑完后遮住「答」，默答 AI8。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–0:40 | 40 min | 另开终端，按 D8 的命令再跑 `fastapi dev main.py` | http://127.0.0.1:8000/docs 能打开 |
| 0:40–1:40 | 60 min | Android Studio 打开本目录，Run 到模拟器 | 应用打开 |
| 1:40–2:20 | 40 min | 点「提问」 | 界面有回答、`cold.md`、一个数字 |
| 2:20–3:00 | 40 min | 默答 AI8 | 能说出检索不在这个 App 里 |

真机把 `MainActivity.kt` 里的 `10.0.2.2` 改成电脑的局域网 IP。

**D11 验收**：`[ ]` 三项都显示 `[ ]` AI8 不看卡能说

### D12 · 默答（3 h）

不新建目录。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–2:20 | 140 min | 盖住题库答案，说 AI1–AI8 | 每题能说出「要说的那句」 |
| 2:20–3:00 | 40 min | 答歪的原句写入错题库 | 空着的题不要编 |

**D12 验收**：`[ ]` 八题都开口过 `[ ]` 错题库只记真的答歪

### D13 · 复跑（3 h）

不新建目录。

| 时段 | 时长 | 任务 | 完成标准 |
|------|------|------|----------|
| 0:00–2:00 | 120 min | 只复跑错题库里的题 | 每条能对上「要说的那句」 |
| 2:00–3:00 | 60 min | 错题库仍是空的，就再问一次 D8 的 `/ask` | 回答、`cold.md`、毫秒还在 |

**D13 验收**：`[ ]` 错题复跑完，或 `/ask` 再成功一次
