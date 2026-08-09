# 个人知识库 · 迷你 ECC（Cursor）

用卢曼卡片沉淀可复用知识；用 **`.cursor/` 里的多角色 Agent** 做纠错、审查与督促。  
灵感来自 [ECC](https://github.com/affaan-m/ECC) 的分层思想，但只保留个人学习需要的薄层。

> Optimize the context window. Persist everything else.

---

## 两层结构

| 层 | 在哪 | 干什么 |
|----|------|--------|
| **编排层** | [`.cursor/`](.cursor/README.md) | 规则、角色、Skill、未审核 memory |
| **知识层** | `Android/` `Web/` `Tools/` `assets/` | 已晋升概念卡 / 过程卡 / 截图 / 练习 |

当前使用 Cursor → 编排放 `.cursor/`。若换 IDE，可把同结构迁到对应目录（思路不变）。

---

## 多角色（补上「学完没人纠错」）

| 角色 | 职责 | 你怎么喊 |
|------|------|----------|
| **卡片审查官** | 卡写得对不对、能不能复习/复现 | 「审卡」 |
| **代码审查官** | demo 有没有 bug、和卡是否一致 | 「审 demo」 |
| **复现验收官** | 只按过程卡能否做完 | 「验收过程卡」 |
| **督学教练** | 周检、≤3 项任务、抽查默答 | 「督促我」 |
| **图书管理员** | memory 晋升、改 INDEX | 「整理 memory」 |
| **讲解导师** | 讲清楚 + 逼你落盘一项 | 「教我」 |

角色说明书：`.cursor/agents/` · Skill：`.cursor/skills/` · 总入口：[`AGENTS.md`](AGENTS.md)

---

## 晋升闭环

```text
学 / 做 / 踩坑
    ↓
.cursor/memory/*          （未审核，2 分钟一条）
    ↓ 审查角色纠错
概念卡 / 过程卡           （Android|Web|Tools）
    ↓ 复跑成功 ≥2 次（可选）
.cursor/skills/           （可复用工作流，含领域 skill）
```

正式卡模板：[`templates/card.md`](templates/card.md) · [`templates/runbook.md`](templates/runbook.md)

---

## 知识层约定（摘要）

- 编号：`前缀-序号 | 标题.md`（FG/V/PD/KT/R/TL/ERR…）
- 过程卡必须冷启动可复现；图在 `assets/<编码>/`
- 练习代码在 `Web/JavascriptDemo/` 等，过程卡写清路径
- 索引：[`INDEX.md`](INDEX.md)

前缀表与维护细则见历史卡片规范；**角色怎么协作以 `.cursor/README.md` 为准**。

---

## 建议你怎么用

1. 打开本仓库（或把 Agent 根目录指到这里），确保加载 `.cursor/rules`
2. 学完先丢 `.cursor/memory/notes/`，再喊「审卡」或「审 demo」
3. 每周五说一次「督促我」
4. 过程卡写完说「验收过程卡」——过了再信任它能救忘记的自己
