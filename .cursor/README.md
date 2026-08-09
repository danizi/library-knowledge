# 迷你 ECC（Cursor）

知识在 `knowledge/`；编排在本目录。

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
├── rules/
├── skills/
├── templates/      # card / runbook / memory
└── memory/

knowledge/
├── Android/
├── Web/            # 含 JavascriptDemo 练习
└── Tools/
```

## 用法摘要

- 闪念 → `.cursor/memory/*.md`
- 概念卡 → 复制 `templates/card.md`（费曼：问/答/例/易错）到 `knowledge/<领域>/`
- 截图 → 按需 `knowledge/<领域>/assets/<编码>/`
- 晋升后更新根目录 `INDEX.md`
