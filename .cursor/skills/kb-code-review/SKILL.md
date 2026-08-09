---
name: kb-code-review
description: >-
  以代码审查官身份审查知识库练习 demo（尤其 Web/JavascriptDemo）：可运行性、bug、与卡片一致性。
  在用户说代码审查、demo 纠错、看看练习代码时使用。
---

# Demo 代码审查

1. 遵循 [`.cursor/agents/code-reviewer.md`](../../agents/code-reviewer.md)
2. 定位练习入口（`read.md` / `app.js` / 用户指定文件）
3. 能跑则指出如何跑；不能跑则先列阻断项
4. 输出缺陷列表与「应补进过程卡」的验收项
5. 默认给补丁建议；用户确认后再改代码
