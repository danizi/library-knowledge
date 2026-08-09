**第 5 周：表单与本地存储（表单校验 / 阻止默认 / localStorage 序列化与恢复）**

- 准备与时间：Node 18+、现代浏览器、VS Code；建议 2 次 60–90 分钟学习。
- 目标：掌握原生表单校验（HTML5 + JS）、`event.preventDefault` 使用、`localStorage` 的 JSON 存取与恢复。
- 产出：Todo MVP 页面（添加/删除/切换完成、计数、持久化）。

**学习步骤（按顺序，含时长）**
1) 表单事件与阻止默认（10–15 分钟）：监听 `submit/input/blur`，区分提交按钮 vs Enter，理解默认行为。
2) HTML5 原生校验属性与提示（20–30 分钟）：`required/minlength/pattern`、validity 状态、`reportValidity` / `checkValidity`。
3) JS 自定义校验与错误聚合（20–30 分钟）：按需组合规则，失焦与提交时校验，集中渲染错误提示。
4) localStorage 基础（15–20 分钟）：`getItem/setItem/removeItem/clear`，JSON 序列化；解析失败兜底空数组。
5) Todo MVP 结构与交互（40–60 分钟）：渲染列表；添加、删除、切换完成；统计 `active/complete/total`；输入框清空并聚焦。
6) 持久化集成（20 分钟）：初次加载从 storage 恢复；每次操作后写入；设计键名/版本；损坏数据回退空列表。
7) 收尾与记录（5–10 分钟）：整理踩坑/收获写入 `week05/note.md`。

**实战提示**
- 输入文本先 `trim`；只有校验失败时才 `preventDefault` 阻止提交。
- 校验信息尽量聚合显示，避免多处弹跳。
- 存储前后保持同构数据结构，字段最少化（`id/text/done`）。
- 处理空/损坏 storage：`try/catch JSON.parse`，失败则重置空列表并覆盖写回。
- 可选：监听 `storage` 事件，支持多标签同步。

**测试与验证**
- 无效输入提交被阻止并出现提示；有效提交后输入被清空并重新聚焦。
- 添加/删除/切换完成后计数（`total/active/complete`）正确。
- 刷新后列表与状态从 localStorage 恢复；损坏存储时不崩溃而重置为空。
- localStorage 仅包含必要字段，无多余敏感信息。

**假设与默认**
- 仅用原生 HTML/CSS/JS，不引入框架。
- 目标环境为现代浏览器。
- 文档语言为中文。
- 允许在 `week05` 目录新增 `note.md` 记录笔记（如不存在可新建）。
