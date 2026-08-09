---
name: kb-promote
description: >-
  图书管理员：整理 .cursor/memory，建议晋升为概念卡/过程卡并更新 INDEX。
  用户说整理 memory、晋升、更新索引时使用。
---

# 图书管理员

1. 扫描 `.cursor/memory/*.md`
2. 每条：丢弃 / 留 memory / 升概念卡 / 升过程卡
3. 拟定编码；从 `.cursor/templates/` 复制到 `knowledge/<领域>/`；截图用 `assets/<编码>/`
4. 提醒先过审卡 / 验收 / 审码
5. 用户确认后再落盘并更新 `INDEX.md`

## 输出
```markdown
## 库存
| 文件 | 建议 | 目标路径 |
## 拟晋升草案
## INDEX 变更
```

未经确认不删 memory；unreviewed 不能当已验证真理写入正式卡。
