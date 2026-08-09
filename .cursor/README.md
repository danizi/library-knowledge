# 迷你 ECC · Cursor 工作台

> Optimize the context window. Persist everything else.  
> 知识在仓库卡片里；角色与规则在 `.cursor/` 里。

本目录是 **Agent 编排层**（规则 / 角色 / Skill / 未审核记忆）。  
正式知识（概念卡、过程卡、练习代码）仍在仓库根目录的 `Android/` `Web/` `Tools/` 等。

---

## 怎么用（对话里直接说）

| 你说 | 触发角色 | Skill |
|------|----------|--------|
| 「审一下这张卡」/「卡片纠错」 | 卡片审查官 | `kb-card-review` |
| 「审一下这个 demo」/「代码审查」 | 代码审查官 | `kb-code-review` |
| 「督促我」/「本周学习检查」 | 督学教练 | `kb-coach` |
| 「这条记忆能晋升吗」/「整理 inbox」 | 图书管理员 | `kb-promote` |
| 「按过程卡验收」/「冷启动复跑检查」 | 复现验收官 | `kb-verify` |
| 「教我 / 讲清楚」 | 讲解导师 | `kb-tutor` |

也可以：`按 .cursor/agents/<角色>.md 的角色来做`。

---

## 目录

```text
.cursor/
├── rules/kb-workflow.mdc     # 始终生效的晋升闸门与搜库规则
├── agents/                   # 角色说明书（人格 + 输出格式）
├── skills/                   # Cursor 可发现的 Skill（何时调用）
└── memory/                   # 未审核记忆（≠ 正式知识）
    ├── notes/                # 闪念
    ├── lessons/              # 踩坑草稿
    ├── handoffs/             # 跨会话交接
    └── facts/                # 待核实事实
```

---

## 晋升闭环（和 ECC 一致）

```text
.cursor/memory/*     未审核，可随便写
        │ 角色审查 + 你确认
        ▼
Android|Web|Tools/   概念卡 / 过程卡（已晋升）
        │ 同一流程成功 ≥2 次
        ▼
skills/ 或 .cursor/skills/   可复用工作流
```

**禁止**：Agent 自动把 memory 写成正式卡并当作真理。必须经「卡片审查官 / 复现验收官」或你本人勾选。

---

## 建议节奏

1. 学完 / 写完 demo → 丢一条 `memory/notes` 或 `handoffs`
2. 喊「卡片审查官」或「代码审查官」纠错
3. 通过后晋升到领域目录，更新 `INDEX.md`
4. 每周喊一次「督促我」做周检
