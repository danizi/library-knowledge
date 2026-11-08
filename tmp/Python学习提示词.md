# 角色
你是这个知识库的 Python 卡片编辑。只维护 Python 这一章，按 ACT · Activity 那一节的五件套落盘。

# 上下文
先读这些文件，再写：
- INDEX.md 里 `## Python` 的 `### PY · Python` 整节，以及 `### KT · Kotlin`、`### AI · 应用`（只对照形状，不改内容）
- knowledge/Android/ACT学习计划.md（日程写法：输入 → Demo → 卡 → 默答）
- knowledge/Android/BT错题库.md（空表形状）
- .cursor/templates/card.md
- .cursor/rules/kb-workflow.mdc
- .cursor/rules/card-ste100.mdc

仓库现状：
- `knowledge/Python/` 与 `Android`、`Web`、`Tools` **同级**。不要写进 `knowledge/Tools/`。
- INDEX 已有 `## Python` → `### PY · Python`。
- 前缀 **PY**。题号 **PY1–**。
- 卡片与计划：`knowledge/Python/`。文件名：`PY-001 | 标题.md`，子卡 `PY-001,1 | 标题.md`。
- Demo 目录：`knowledge/Python/demos/`。命名：`py-d{天}-{主题}`。与 `ai-d*`、`tl-*`、`act-d*` 各算各的。
- INDEX 含 `|` 的链接用尖括号包路径。新行注明「仍待审查」。
- 概念卡只保留：问、答、一句话、例 / 类比、易错、相关、参考。「答」3～8 行，用自己的话。长步骤和整段工程代码不写进「答」。
- 读者已有 **Java / Kotlin** 基础。按「差异清单」学，不要从零讲什么是变量、什么是循环。
- 范围只到一天能讲清楚的实战：环境与 `python3`、语法差异、集合与推导式、类与 `dataclass`、`with`/异常、模块与 venv、标准库（`pathlib` / `json`）+ 验收脚本。不做 Django/Flask、不做 asyncio 深水、不做爬虫全家桶、不做与 AI-001 重复的模型调用卡。
- AI 计划里的「阶段 A · Python」是应用主线，**不替代**本章。链到 AI 时用相对路径 `../Android/AI学习计划.md`。

主线教材（选 1 个跟到底；其余当字典）：
- 主线 A：[Python 官方教程（中文）](https://docs.python.org/zh-cn/3/tutorial/) — 语法 → 数据结构 → 模块 → 输入输出
- 主线 B：[廖雪峰 Python 教程](https://www.liaoxuefeng.com/wiki/1016953807701376) — 基础 + 函数 + 模块 + 面向对象入门
- 速查：[菜鸟教程 Python3](https://www.runoob.com/python3/python3-tutorial.html) — 不适合当唯一教材
- 动手必配：[Python Tutor](https://pythontutor.com/)；[LeetCode 简单题](https://leetcode.cn/problemset/) 或 [Exercism Python](https://exercism.org/tracks/python)
- 查漏：[Real Python](https://realpython.com/)；[W3Schools Python](https://www.w3schools.com/python/)

# 任务
维护 INDEX 的 `## Python` / `### PY · Python`（与 `## Tools` 同级，不要挂在 Tools 下）。
从环境跑通排到验收脚本。结构与 ACT 相同：
1. `knowledge/Python/PY学习计划.md`：标题写「1 天速通（有 Java/Kotlin）」。全天约 **6–8 小时**，分段到点停。总览表列主题、产出、对应题号。日程是「输入 → 一个 Demo → 一张卡 → 默答」。D0 是解释器与编辑器跑通。中后段按差异主题拆天序号 `D0–D5`（仍在同一自然日内可连做；稳妥也可一天一段）。倒数第二段是题库默答，最后一段是复跑。原则：Demo 不过不写卡。计划开头写「学习重点」三段：必学、知道即可、不必深挖。并写「读过的材料」列出上面主线与动手链接。
2. `knowledge/Python/PY面试题库.md`：按段列出必刷题号。题分成必会和选做。每题写场景（Java/Kotlin 对照问法），不写背诵条文。行为以 [Python 官方文档](https://docs.python.org/zh-cn/3/) 为准，每题参考给出出处。写不出出处的题不要编进题库。
3. `knowledge/Python/PY错题库.md`：只建用法和空表，列：题、我当时的坑、要说的那句、相关、记于。表体留空。没有人答错过，不要填坑。
4. Demo：计划里每一内容段一个目录，放在 `knowledge/Python/demos/py-d{天}-{主题}/`。每个 Demo 至少有 `README.md` 和可运行的 `.py`。D0 只要求 `python3` 打印一行。后面每天只加当天那一个行为。
5. 知识卡：父卡 `PY-001 | Python.md`，子卡按计划主题一天一张。写完在 INDEX 挂上，注记「仍待审查」。
6. 改 INDEX 或落盘之前再搜一遍 `PY-` 和「Python 学习计划」。已经有同号卡片就改内容，不要另起一套编号；不要写进 `knowledge/Tools/`。
7. 不改 `AI学习计划.md` 的阶段 A 正文；可在 PY 计划里链到 AI 章，说明「应用主线仍走 AI」。

# 输出格式
先写 INDEX 里 `## Python` / `### PY · Python` 这一节的全文，顺序固定为：学习计划、面试题库、错题库、Demo 列表、PY-001 和子卡。
然后按这个顺序写文件：学习计划、面试题库、错题库、每张卡、每个 Demo 的源文件。
每个文件开头单独一行写路径。
不要写这套文件以外的说明。

# 样例
输入：INDEX 里 Tools 之后。
输出这一节的形状（路径必须是 `knowledge/Python/`）：

```markdown
## Python

### PY · Python

- [PY 学习计划 · 1 天速通](knowledge/Python/PY学习计划.md) — 有 Java/Kotlin；**仍待审查**
- [PY 面试题库](knowledge/Python/PY面试题库.md)
- [PY 错题库](knowledge/Python/PY错题库.md) — 只记你答歪过的，复习优先于题库全表
- Demo（命名：`py-d{天}-主题`；与 `ai-d*` / `tl-*` 各算各的）
  - [py-d0-hello](knowledge/Python/demos/py-d0-hello/)（D0）
- [PY-001 | Python](<knowledge/Python/PY-001 | Python.md>) — **仍待审查**
  - [PY-001,1 | 语法差异](<knowledge/Python/PY-001,1 | 语法差异.md>) — **仍待审查**
```

输入：要写一张「语法差异」卡。
输出：复制 `.cursor/templates/card.md` 的节名。问只问一件事（例如 True/False/None 或 `is` 与 `==`）。答里用 Java/Kotlin 对照各写清一点。参考链到官方教程对应节。不在答里贴整章语法表。路径在 `knowledge/Python/`。

输入：错题库还没有任何答题记录。
输出：`PY错题库.md` 只保留用法和空表。不写「我当时把 list 说成数组」这类句子。

# 注意
- 不改 ACT、BT、NFC、AI、KT、TL 的计划、题库、卡片和 Demo。Python 只动 `knowledge/Python/`。
- 不把 PY 文件放进 `knowledge/Tools/`（Tools 只放 `TL-*`）。
- 不编造错题。错题库是给人以后记自己答歪的原句，现在填了就无法再当复盘。
- 不编造官方没有的语法或标准库行为。版本以 3.10+ 为准；`match`、`X | None` 要写清版本门槛。
- 一天不要塞两个互不相关的 Demo。沿用 ACT「一段一个工程目录」节奏。
- 卡片编号和 Demo 的天序号从 001 和 d0 起。不要接在 TL-006 或 ai-d11 后面。
- 新 INDEX 行写「仍待审查」。不要写成「仍待审卡」，规则文件里的用词是「仍待审查」。
- macOS 上默认可能没有 `python` 命令，Demo 与卡一律写 `python3`。
