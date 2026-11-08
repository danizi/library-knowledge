# AI 学习计划 · 长期胜任路线（约 6–12 个月）

目标：学透 **AI 应用开发** 主线上的可验收技能，达到「能独立做出并维护一个有人在用的问答 / Agent 服务」的水平。  
主业：继续做 Android，不要辞职学。每周固定 **8–12 h**，少了就拉长月份，不要压缩阶段。

## 不能保证的事

- **不能保证拿到 offer。** 启事里的「1–3 / 3–5 年 AI 经验」「至少 1 年已上线真实用户」「硕士」「K8s」「微调」不是本计划能练成的筛选项。
- **本机 Demo 不是企业项目经历。** 简历年限仍写真实工作年限。
- 本计划保证的是：**阶段验收标准写清楚**；你达标才进下一阶段。达不到就停在本阶段，直到达标。

## 「学透 / 胜任」的验收定义

同时满足才算本计划意义上的胜任（对「AI 应用」岗，不是算法岗）：

1. 不看卡片，能说清：模型调用、工具调用、检索、「不知道」、接口三项返回。
2. 有一个服务：别人（同事 / 好友 / 真实用户）用过 ≥ **4 周**；你处理过 ≥ **10 条**真实坏例子并留下记录。
3. 能当着别人的面，从空目录在 **90 分钟**内搭出「读本地笔记 → 调 DeepSeek → `/ask` 返回 answer / filename / elapsed_ms」。
4. 简历上只写你真实做过的：接口、数据来源、坏例子、谁在用；不写「相当于 3 年 AI 经验」。

## 总览

| 阶段 | 时长（大约） | 主题 | 通过才进入下一段 |
|------|-------------|------|------------------|
| A | 3–4 周 | Python 能自己写 | 空文件写出读 md + 改报错 |
| B | 3–4 周 | 模型 + 工具调用 | 口述「模型出 JSON，代码干活」 |
| C | 6–8 周 | 一个长期服务 | 自己连续用 ≥ 2 周 + 错题本 |
| D | 8–12 周 | 有人用 + 补招聘词 | 他人用 ≥ 4 周 + 10 条坏例子 |
| E | 持续 | 投递与证据 | 作品集页 + 口述演练；不保证录用 |

按天 Demo 命令见 [AI练习册](AI练习册.md)，只作 B/C/D 的可选练习，不当主课表。

## 阶段 A · Python（3–4 周）

每周 8–12 h。只做这些：

1. 变量、列表、字典、`if` / `for`、函数、虚拟环境  
2. 读本地 `.md` 并打印  
3. 用官方方式发一次 HTTP（先不必接大模型）

**通过标准**

- [ ] 不看教程，从空文件写出「读一个 md + 打印正文」  
- [ ] 故意改错路径，能根据报错改到再次成功  
- [ ] 密钥永不写进文件  

**材料**

- https://docs.python.org/3/tutorial/

**不要做**：LangGraph、Chroma、Docker、视频、Android 壳、同时开十个 GitHub 仓。

## 阶段 B · 模型与工具（3–4 周）

DeepSeek 一条线（OpenAI 兼容）：

1. 一次对话：打印 `content` 与耗时毫秒  
2. 工具调用：模型返回 `tool_calls` → **你的代码**执行 → `role=tool` 回传  
3. 无依据时固定输出「不知道」

**通过标准**

- [ ] 默答：工具是谁执行的？磁盘是谁打开的？  
- [ ] 关掉思考模式时，按 DeepSeek 文档带上所需参数  
- [ ] 能把同一流程的 `base_url` 说成 DeepSeek 的地址  

**材料**

- https://api-docs.deepseek.com/zh-cn/
- https://api-docs.deepseek.com/zh-cn/guides/tool_calls
- https://developers.openai.com/api/docs/guides/function-calling

**对照练习（可选）**：[AI练习册](AI练习册.md) 里 `ai-d3-deepseek`、`ai-d4-tool`；不会写就先抄通，再默写一遍。

## 阶段 C · 自己的长期服务（6–8 周）

做一个问答服务，数据用**你自己的**笔记 / 工作文档：

1. FastAPI：`POST /ask` → `answer`、`filename`、`elapsed_ms`  
2. 每天问 ≥ 5 个真问题；答歪的写入 [AI错题库](AI错题库.md)  
3. 进程挂着连续用 ≥ **2 周**（合上笔记本也要还能访问：本机常驻或一台小云主机）

**通过标准**

- [ ] 浏览器或 `curl` 两次提问结果正确（有依据 / 无依据）  
- [ ] 错题库至少 10 条「问题 → 错因 → 改法」  
- [ ] 能讲清检索规则（关键词或向量二选一先做透一个）  

**材料**

- https://fastapi.tiangolo.com/tutorial/first-steps/

**可选分支**（达标后再加，不要并行开）：关键词检索 → Chroma → Docker。练习册 D6–D9；卡片 AI-001,2 / AI-001,10 / AI-001,12。

## 阶段 D · 有人用 + 补招聘常见词（8–12 周）

1. 找 **至少 1 个真人**每周用（同事内测也算）  
2. 记录：谁、何时、问了什么、哪里坏、你怎么修  
3. 按缺口补词，**一次只补一个**：

| 缺口 | 何时补 | 参考 |
|------|--------|------|
| 向量检索不够用 | 关键词误杀多 | Chroma 文档 + 本仓 `ai-d7-chroma` |
| 要给别人部署 | 对方不肯装 Python | Docker 官方 Dockerfile 文档 |
| Agent 多步 | 单次工具不够 | LangGraph quickstart（先计算器，再接到你的检索） |
| Android 入口 | 服务已稳 | 本仓 `ai-d11-android`（改 `10.0.2.2` / 局域网 IP） |

**通过标准**

- [ ] 他人使用 ≥ 4 周  
- [ ] ≥ 10 条真实坏例子（含修复）  
- [ ] 90 分钟空目录复建演示成功一次  

头像视频、名场面类能力 **不是** 胜任主线；有余力再做，且不使用影视画面换脸。

## 阶段 E · 投递证据（持续）

准备一页作品说明（自己的仓库或文档即可）：

1. 解决什么问题、数据从哪来  
2. 架构：客户端 → 接口 → 模型 / 工具 / 检索  
3. 三个坏例子与修复  
4. 谁在用、用了多久  

投递时：Android 年限照实写；AI 部分只写阶段 C/D 的事实。启事要求硕士 / 已上线一年 / K8s 且你没有的，不要硬投装有。

## GitHub 仓库地址（查询用）

星标来自 GitHub API，查询时间 **2026-11-08 18:24 CST**。星多 ≠ 好上手。大产品仓不要当入门教材通读。

### 先跟着例子跑

| 仓库 | Stars（查询时） | 地址 | 怎么用 |
|------|----------------|------|--------|
| awesome-llm-apps | 144,146 | https://github.com/Shubhamsaboo/awesome-llm-apps | 挑一个 RAG / 工具小目录跑通，再改 DeepSeek |
| openai-cookbook | 76,998 | https://github.com/openai/openai-cookbook | 对照调用、工具、检索步骤；`base_url` 改 DeepSeek |
| smolagents | 30,266 | https://github.com/huggingface/smolagents | 看清短 Agent 循环 |
| openai-agents-python | 30,554 | https://github.com/openai/openai-agents-python | 对照阶段 B 的工具调用 |

### 和本计划技能直接相关（当说明书）

| 仓库 | Stars（查询时） | 地址 | 阶段 |
|------|----------------|------|------|
| langgraph | 44,642 | https://github.com/langchain-ai/langgraph | D，只看 examples / quickstart |
| chroma | 29,652 | https://github.com/chroma-core/chroma | C/D，只看 Getting Started |
| fastapi | 104,562 | https://github.com/fastapi/fastapi | C，Tutorial 第一个接口 |

### 星很高、现在不要通读源码

| 仓库 | Stars（查询时） | 地址 | 说明 |
|------|----------------|------|------|
| dify | 160,650 | https://github.com/langgenius/dify | 完整产品，以后可自行部署玩 |
| ragflow | 93,754 | https://github.com/infiniflow/ragflow | 完整引擎，不适合现在啃源码 |
| langchain | 149,191 | https://github.com/langchain-ai/langchain | 库太大；跟官方例子走即可 |

### 官方文档（常查）

- Python：https://docs.python.org/3/tutorial/
- DeepSeek：https://api-docs.deepseek.com/zh-cn/
- DeepSeek 工具调用：https://api-docs.deepseek.com/zh-cn/guides/tool_calls
- OpenAI function calling：https://developers.openai.com/api/docs/guides/function-calling
- LangGraph quickstart：https://docs.langchain.com/oss/python/langgraph/quickstart
- FastAPI：https://fastapi.tiangolo.com/tutorial/first-steps/
- Docker：https://docs.docker.com/reference/dockerfile/

需要更新星标时：对上表地址再查一次 `https://api.github.com/repos/{owner}/{repo}` 的 `stargazers_count`，改表格并改查询时间。

## 每周节奏

| 项 | 做法 |
|----|------|
| 主线 | 只推当前阶段的通过标准 |
| 默答 | 每周抽 2 题（见 [AI面试题库](AI面试题库.md)），答歪写入错题库 |
| GitHub | 每周最多跟 **一个** 小例子；不要收藏式开坑 |
| 停手规则 | 当天没达标现象（跑通 / 默答），不加新工具 |

## 配套

- 练习命令：[AI练习册](AI练习册.md)  
- 默答：[AI面试题库](AI面试题库.md)  
- 答歪：[AI错题库](AI错题库.md)  
- 卡片 AI-001…：默答用，审查前标「仍待审查」

## 你现在的下一步

阶段 A 第 1 课：新建空目录，写出「读一个 md + 打印正文」，今天跑通。跑通前不要进阶段 B。
