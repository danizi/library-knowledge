# 迷你 ECC · Cursor 工作台

> Optimize the context window. Persist everything else.  
> 知识在领域目录；角色、规则、模板、未审核记忆在 `.cursor/`。

---

## 怎么用（对话里直接说）

| 你说 | 触发角色 | Skill |
|------|----------|--------|
| 「审一下这张卡」/「卡片纠错」 | 卡片审查官 | `kb-card-review` |
| 「审一下这个 demo」/「代码审查」 | 代码审查官 | `kb-code-review` |
| 「督促我」/「本周学习检查」 | 督学教练 | `kb-coach` |
| 「这条记忆能晋升吗」/「整理 memory」 | 图书管理员 | `kb-promote` |
| 「按过程卡验收」/「冷启动复跑检查」 | 复现验收官 | `kb-verify` |
| 「教我 / 讲清楚」 | 讲解导师 | `kb-tutor` |
| 「按模板新建一张过程卡」 | 图书管理员 / 任意 | 复制 [`templates/`](templates/README.md) |

也可以：`按 .cursor/agents/<角色>.md 的角色来做`。

---

## 目录

```text
.cursor/
├── rules/kb-workflow.mdc     # 始终生效的晋升闸门与搜库规则
├── agents/                   # 角色说明书
├── skills/                   # Cursor Skill（kb-* + 领域 skill）
├── templates/                # 空白表单：card / runbook / memory
└── memory/                   # 未审核记忆（≠ 正式知识）
```

知识层证据不在这里：`Android/assets/` `Web/assets/` `Tools/assets/`。

---

## templates 是干什么的（会「用到」）

模板**不会自动执行**，是新建内容时的复制源：

1. 晋升或手建概念卡 → 复制 `templates/card.md`
2. 手建过程卡 → 复制 `templates/runbook.md`
3. 丢闪念 → 复制 `templates/memory.md` 到 `memory/...`
4. 审查官对照模板检查结构是否合格

详见 [`templates/README.md`](templates/README.md)。

---

## 晋升闭环

```text
.cursor/memory/*     未审核
        │ 角色审查 + 你确认
        ▼
Android|Web|Tools/   正式卡 + 各领域 assets/<编码>/
        │ 复跑成功 ≥2 次（可选）
        ▼
.cursor/skills/      可复用工作流
```
