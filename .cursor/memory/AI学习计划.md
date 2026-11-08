---
trust: unreviewed
kind: note
created: 2026-11-08
tags: [ai, 学习计划]
promote_to:
---

# AI 学习计划 · 三个练习项目

> 状态：**仍待审查**。未审核，不能当正式卡。  
> 做完这三个项目，技术词可以对准国内大部分「AI 应用开发」启事里的技能清单。  
> 做完不能保证录用。年限、硕士、以及「已上线给真实用户」写在筛选条件里，练习项目填不进去。

## 对照过的要求

技能清单来自这些启事里反复出现的词：Python、FastAPI、模型接口、Function Calling、提示词、RAG、LangGraph、评测、向量库、Docker。  
出处：智联「慧灵科技」「北京赛迪」，Boss 直聘「蓝犀牛」（2026-06-03），海康威视杭州岗，外企德科杭州岗。

三个项目按顺序做。前一个能按「做完标准」演示，再做下一个。

## 项目 1 · 笔记问答 `note-qa`

资料：5 篇 Markdown，再加 1 个 PDF。  
接口用兼容 OpenAI 的国内模型（DeepSeek 或通义千问均可）。文档仍读 OpenAI 的调用说明，把 `base_url` 换成该服务的地址。

| 步 | 做完的样子 | 资料 |
|----|------------|------|
| 1 | 脚本列出文件并打印一篇正文 | [Python 教程第 3–7 节](https://docs.python.org/3/tutorial/) |
| 2 | 终端里提问，打出回答和耗时 | [OpenAI 快速开始](https://developers.openai.com/api/docs/quickstart) |
| 3 | 日志里先读文件，再根据文件回答 | [Function calling](https://developers.openai.com/api/docs/guides/function-calling) |
| 4 | 资料里没有的 5 题都回答「不知道」 | 系统提示词写死这条规矩 |
| 5 | 用 Chroma 存切块。5 个回答各带一段原文 | [LangGraph 快速开始](https://docs.langchain.com/oss/python/langgraph/quickstart)，然后 [RAG Agent](https://docs.langchain.com/oss/python/langgraph/agentic-rag) |
| 6 | 日志能看出查或不查。查空了就停 | 仍用上一篇里的图 |
| 7 | `eval.json` 20 题。改一次提示词或切块，对题数有前后两个数字。另写 5 条答错的题和你改了什么 | 不另找课 |
| 8 | `POST /ask` 返回回答、文件名、耗时。超时或检索为空时返回「不知道」 | [FastAPI 第一步](https://fastapi.tiangolo.com/tutorial/first-steps/) |

做完标准：浏览器打开 `http://127.0.0.1:8000/docs`，一题能看到回答、文件名、耗时。

## 项目 2 · 订单客服 `order-qa`

在项目 1 的服务上加一个工具，不要新开一套框架。  
准备 `orders.json`，里面至少 5 条假订单。工具按订单号读取状态。

做完标准：问「查 10086 的订单」时，日志里出现工具调用，回答里是文件中的状态。订单号不存在时回答「没有这个订单」，不再继续调用工具。

## 项目 3 · 用 Docker 跑起来 `note-qa` 镜像

给项目 1 写 `Dockerfile`。本机执行 `docker build` 和 `docker run` 之后，再用浏览器打一次 `/docs`。

做完标准：容器里的 `/ask` 仍返回回答、文件名、耗时。把这条命令记在 README 里。  
资料用 Docker 官方文档里的 [Dockerfile 说明](https://docs.docker.com/reference/dockerfile/)。

## 三个项目都做完时，简历上只写这三行

- 笔记问答：20 题改动前对几题、改动后对几题，一次请求的耗时。向量库用的是 Chroma。
- 订单客服：一个按订单号查询的工具，不存在的订单号如何停住。
- Docker：镜像能在本机启动，并完成一次提问。

讲不出数字的句子不要写。

## 练习补不上的筛选条件

这些句子出现在海康、外企德科、赛迪或蓝犀牛的启事里。三个项目做完，简历仍然不要写成已经满足：

- 至少 1 年大模型应用，并且上线给真实用户。本机 Demo 不是这一条。
- 1–3 年或 3–5 年 AI / 后端经验。Android 年限不计入这一条。
- 硕士。外企德科和海康写了。慧灵、赛迪、蓝犀牛写的是本科。
- Kubernetes、限流熔断的线上系统和模型微调的实战。启事里有。这三个项目不覆盖。
