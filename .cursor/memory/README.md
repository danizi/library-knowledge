# memory · 未审核记忆（ECC Memory 简化版）

这里的内容是 **context，不是 policy**。  
可以乱、可以短；**不能**未经审查就当作正式卡片真理。

## 子目录

| 目录 | 用途 | 示例 |
|------|------|------|
| `notes/` | 闪念、链接、半成品 | 今天看到的 API |
| `lessons/` | 踩坑草稿 | 报错原文 + 猜测原因 |
| `handoffs/` | 跨会话交接 | 做到哪、下一步、阻塞 |
| `facts/` | 待核实事实 | 「某版本已废弃？」 |

## 记忆条模板

```markdown
---
trust: unreviewed
kind: note   # note | lesson | handoff | fact
created: YYYY-MM-DD
tags: []
promote_to:   # 拟晋升目标，如 R-001,b 或待定
---

# 标题

正文…

## 证据（可选）
- 路径 / 截图 / 链接
```

## 晋升

对图书管理员说：「整理 memory」或「这条能晋升吗」。  
通过卡片/代码/复现审查后，再写入 `Android|Web|Tools/` 并更新 `INDEX.md`。
