# 迷你 ECC（Cursor）

知识在 `Android/` `Web/` `Tools/`；编排在本目录。

## 喊角色

| 你说 | Skill |
|------|--------|
| 审卡 | `kb-card-review` |
| 审 demo | `kb-code-review` |
| 督促我 | `kb-coach` |
| 整理 memory | `kb-promote` |
| 验收过程卡 | `kb-verify` |
| 教我 | `kb-tutor` |

## 目录

```text
.cursor/
├── rules/          # 始终生效规则
├── skills/         # 角色 + 领域 skill（唯一入口）
├── templates/      # 复制用：card / runbook / memory
└── memory/         # 未审核 md（扁平放置）
```

## 用法摘要

- 闪念 → `.cursor/memory/*.md`（可复制 `templates/memory.md`）
- 正式卡 → 复制 `templates/card.md` 或 `runbook.md` 到领域目录
- 截图 → 需要时再建 `Android|Web|Tools/assets/<编码>/`，卡片用 `./assets/...`
- 晋升：`memory` → 角色审查 → 领域正式卡 → 更新 `INDEX.md`
