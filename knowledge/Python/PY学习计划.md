# Python 学习计划 · 1 天速通（有 Java/Kotlin）

目标：**1 个自然日**内能按差异清单写脚本、读别人的 Python、卡住会查官方文档。全天约 **6–8 h**，分段到点停。  
原则：Demo 不过不写卡；卡写完必须 **15 分钟默答**。  
读者前提：已有 Java 或 Kotlin。不从零讲「什么是变量」。

配套：[PY面试题库](PY面试题库.md) · [PY错题库](PY错题库.md)  
环境：`python3`（建议 3.12）+ 编辑器选好解释器  
Demo 目录：`knowledge/Python/demos/`  
命名：`py-d{段}-{主题}`。与 `ai-d*` 各算各的。  
应用主线仍走 [AI 学习计划](../Android/AI学习计划.md)；本章只补语言差异。

## 学习重点

必学：`python3` 与 venv（D0）、语法表层差异（D1）、`list`/`dict`/推导式（D2）、`dataclass` 与 `self`（D3）、`with` 与异常（D3）、模块与 `if __name__`（D4）、`Path`+`json` 验收（D5）。

知道即可：`match`（3.10+）、type hint、`*args/**kwargs`、LeetCode 简单题手感。

不必深挖：元类、描述符、asyncio、Django/Flask、C 扩展、与 AI-001 重复的模型调用。

## 读过的材料

主线（选 1 个跟到底）：

- https://docs.python.org/zh-cn/3/tutorial/
- https://www.liaoxuefeng.com/wiki/1016953807701376

速查与动手：

- https://www.runoob.com/python3/python3-tutorial.html（字典，不当唯一教材）
- https://pythontutor.com/
- https://leetcode.cn/problemset/ 或 https://exercism.org/tracks/python
- https://realpython.com/
- https://www.w3schools.com/python/

## 常见坑

| 现象 | 说明 |
|------|------|
| `python: command not found` | macOS 常用 `python3`，没有 `python` |
| 编辑器点运行无反应 | 未装 Python 扩展或未选解释器 |
| `is` 与 `==` 混用 | `None` 用 `is None`；内容比较用 `==` |
| 空 list 当「有对象」 | 空容器在 `if` 里为假，不是 Kotlin null 安全 |
| 在错误目录跑脚本 | 相对路径找不到文件 |

---

## 1 天总览

| 段 | 主题 | 产出 | 面试覆盖 |
| --- | --- | --- | --- |
| D0 | 解释器 + 编辑器跑通 | py-d0-hello | — |
| D1 | 语法差异（对标 Java/Kotlin） | `PY-001` + `PY-001,1` + py-d1-syntax | PY1 PY2 |
| D2 | 集合 + 推导式 + 切片 | `PY-001,2` + py-d2-collections | PY3 PY4 PY5 |
| D3 | 类 / dataclass / with / 异常 | `PY-001,3` + `PY-001,4` + py-d3-oop | PY6 PY7 PY8 |
| D4 | 模块 + venv + 依赖 | `PY-001,5` + py-d4-module | PY9 PY10 |
| D5 | Path + json 验收 + 默答 | `PY-001,6` + py-d5-files | PY11 全段 |
| D6 | 弱项复跑（可选晚间） | 复跑 1 个 Demo + 错题表 | 选做 |

---

## 每日日程（每段到点停）

> 格式：`输入 → Demo → 输出卡 → 默答`。  
> 主线教程只开一个。卡住用菜鸟 / Real Python / W3Schools 当字典。  
> 列表引用看不懂时，打开 Python Tutor。

### D0 · 能跑（40–60 min）

| 时段 | 任务 | 完成标准 |
| --- | --- | --- |
| 输入 | `python3 --version`；按 [TL-008,1](<../Tools/TL-008,1 | 配置 VS Code Python.md>) 选解释器 | 出现 `Python 3.`；状态栏有版本 |
| Demo | `py-d0-hello/` 跑 `hello.py` | 终端打印一行 |
| 卡 | 环境卡在 Tools：`TL-008`；此处不重复写卡 | 能打开 TL-008 |
| 默答 | 为什么本机可能没有 `python` 命令；▶ 下长命令算不算配错 | 能说清用 `python3`；长命令正常 |

**D0 验收**：`[ ]` python3 OK `[ ]` 脚本跑通 `[ ]` 编辑器能 Run

### D1 · 语法差异（70–90 min）

| 时段 | 任务 | 完成标准 |
| --- | --- | --- |
| 输入 | 官方教程「入门」或廖雪峰基础；对照 Kotlin 模板字符串 | 能默写 `f"..."`、`True/False/None`、`and/or/not` |
| Demo | `py-d1-syntax/`：函数 + 默认参数 + 三元表达式 | 跑通 |
| 卡 | `PY-001` + `PY-001,1` | 仍待审查 |
| 默答 | PY1 PY2 | 遮住答能说 |

### D2 · 集合（70–90 min）

| 时段 | 任务 | 完成标准 |
| --- | --- | --- |
| 输入 | 官方教程数据结构；Python Tutor 看一遍 list 引用 | 会切片、`enumerate`、推导式 |
| Demo | `py-d2-collections/`：词频用 `dict` | Top 打印出来 |
| 卡 | `PY-001,2` | 仍待审查 |
| 默答 | PY3 PY4 PY5 | 通过 |

### D3 · 类与资源（70–90 min）

| 时段 | 任务 | 完成标准 |
| --- | --- | --- |
| 输入 | 类、`dataclass`、`with`、异常 | 能写出 `__init__` 与 `@dataclass` |
| Demo | `py-d3-oop/`：dataclass 列表排序 + 故意抛错再接住 | 跑通 |
| 卡 | `PY-001,3` + `PY-001,4` | 仍待审查 |
| 默答 | PY6 PY7 PY8 | 通过 |

### D4 · 模块与环境（50–70 min）

| 时段 | 任务 | 完成标准 |
| --- | --- | --- |
| 输入 | 模块、包、venv、`requirements.txt` | 能说清文件即模块 |
| Demo | `py-d4-module/`：`import` 本地模块 + `if __name__` | 直接跑与被 import 行为不同 |
| 卡 | `PY-001,5` | 仍待审查 |
| 默答 | PY9 PY10 | 通过 |

### D5 · 验收 + 默答（60–90 min）

| 时段 | 任务 | 完成标准 |
| --- | --- | --- |
| 输入 | `pathlib`、`json` 文档页 | 会读文件、写 JSON |
| Demo | `py-d5-files/`：读文本 → 词频 → 写 JSON | 产出文件存在 |
| 卡 | `PY-001,6` | 仍待审查 |
| 默答 | 题库必会全过；可选 2 道 LeetCode Easy | 30 min 内说完 |

### D6 · 复跑（可选，40 min）

复跑 D2 或 D5 其中一个 Demo。错题进 [PY错题库](PY错题库.md)。不新建工程。

---

## 当天不要碰

元类、复杂装饰器链、asyncio 深水、Web 框架全家桶、与 AI Demo 抢时间的大工程。
