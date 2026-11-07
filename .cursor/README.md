# 工作台

本目录是 Cursor 工作台：约束、能力、空表、固定说明、未定稿。个人知识库只是其中一条支线，正式卡片在仓库的 `knowledge/`。

## 知识库支线 · 喊角色

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
.cursor/            # 工作台。按下面五层分工，不按知识领域分
├── rules/          # 每次对话都在的短约束。长文不放这里
├── skills/         # 喊到才加载的能力。一个子目录一份 SKILL.md
├── templates/      # 要反复新建的空表。复制出去再填
├── docs/           # 留着看的固定说明，不进每次对话
└── memory/         # 还没定稿的笔记
    └── absorbed/   # 已收进别处，不再当未定稿
```

| 目录 | 作用 |
|------|------|
| `rules/` | 工作台的硬约束。现在只有知识库支线的 `kb-workflow.mdc` |
| `skills/` | 一项能力一个目录。上表六个是知识库角色；`android-studio-autosetup/` 是环境搭建，不是知识库 |
| `templates/` | 空表。现在有概念卡、过程卡、闪念三份，都服务于知识库支线 |
| `docs/` | 固定说明和图的源码。 |
| `memory/` | 未定稿。知识库支线里，这是还不能当正式卡的闪念 |
| `memory/absorbed/` | 定稿后的归档。知识库支线里，结论已经写进正式卡 |

知识库支线的成品不放在本目录：正式卡在 `knowledge/Android`、`Web`、`Tools`，入口是仓库根上的 `INDEX.md`。`Web/JavascriptDemo/` 是练习场。截图按需建在 `knowledge/<领域>/assets/<编码>/`。

## 知识库支线 · 用法

- 闪念 → `.cursor/memory/*.md`（已吸收的 Demo 笔记在 `memory/absorbed/`，不再当未审核）
- 概念卡 → 复制 `templates/card.md`（费曼：问/答/例/易错）到 `knowledge/<领域>/`
- 学习法、固定项目文档 → `docs/`（不占领域编号）
- 截图 → 按需 `knowledge/<领域>/assets/<编码>/`
- 晋升后更新根目录 `INDEX.md`
