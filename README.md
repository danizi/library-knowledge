# 个人知识库 · 迷你 ECC（Cursor）

用卢曼卡片沉淀可复用知识；用 **`.cursor/` 里的多角色 Agent** 做纠错、审查与督促。  
灵感来自 [ECC](https://github.com/affaan-m/ECC) 的分层思想，但只保留个人学习需要的薄层。

> Optimize the context window. Persist everything else.

---

## 两层结构

| 层 | 在哪 | 干什么 |
|----|------|--------|
| **编排层** | [`.cursor/`](.cursor/README.md) | 规则、角色、Skill、未审核 memory、**空白模板** |
| **知识层** | `Android/` `Web/` `Tools/` | 已晋升卡片 + **各领域自己的 `assets/`** + 练习代码 |

---

## 资源放哪

| 东西 | 位置 | 说明 |
|------|------|------|
| 截图 / 过程证据 | `Android/assets/<编码>/` 等 | 跟领域走，卡片用 `./assets/...` 引用 |
| 练习代码 | `Web/JavascriptDemo/` 等 | 过程卡写清路径 |
| 新建卡模板 | [`.cursor/templates/`](.cursor/templates/README.md) | 复制后填，不是自动跑的代码 |
| 未审核闪念 | `.cursor/memory/` | 晋升前暂存 |

---

## 多角色

| 角色 | 你怎么喊 |
|------|----------|
| 卡片审查官 | 「审卡」 |
| 代码审查官 | 「审 demo」 |
| 复现验收官 | 「验收过程卡」 |
| 督学教练 | 「督促我」 |
| 图书管理员 | 「整理 memory」 |
| 讲解导师 | 「教我」 |

总入口：[`AGENTS.md`](AGENTS.md) · 索引：[`INDEX.md`](INDEX.md)

---

## 晋升闭环

```text
.cursor/memory/*     未审核
        ↓ 角色审查
领域目录正式卡 + 领域 assets/
        ↓ 复跑成功（可选）
.cursor/skills/      可复用工作流
```

新建卡：复制 `.cursor/templates/card.md` 或 `runbook.md` → 改名放入领域目录。
