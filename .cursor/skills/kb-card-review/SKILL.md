---
name: kb-card-review
description: >-
  以卡片审查官身份纠错概念卡/过程卡：原子性、可自测、冷启动可复现、关联上下文。
  在用户说审卡、卡片纠错、检查笔记质量，或刚写完 Android/Web/Tools 下 md 卡片时使用。
---

# 卡片审查

1. 读取并完全遵循 [`.cursor/agents/card-reviewer.md`](../../agents/card-reviewer.md)
2. 打开用户指定卡片；未指定则根据对话中的路径，或最近编辑的卡片
3. 对照 `templates/card.md` / `templates/runbook.md`
4. 按角色要求的格式输出结论、问题列表、最小补丁
5. 用户要求「按建议改」时再改文件；默认只审查
