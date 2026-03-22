# Week04：DOM 基础与交互（指导版）

## 本周目标（预计 3–5 小时）
- 学会 DOM 查询与修改（文本、样式、class、属性）。
- 掌握事件委托；理解事件冒泡/捕获的基本概念。
- 体验防抖/节流的场景（输入、滚动）作为加分项。
- 练习小组件拆分与可访问性基础（aria-label、键盘操作）。
- 产出：3 个小页面 demo（点击计数、输入字数统计、列表高亮/键盘导航）。

## 学习节奏与任务
按 2–3 次学习时段完成，每次 60–90 分钟。每个环节：先阅读要点 → 你亲手改/写 → 运行并截图/记录 → 我帮你答疑纠错。

### Session A：DOM 查询与基础交互
1) 阅读：`querySelector/All`、`textContent` vs `innerHTML`、`classList.add/remove/toggle`、`style` 基本用法。
2) 你做：实现“点击计数”小页面：按钮 + 计数文本，点击递增，按钮禁用状态可选。
3) 回报：运行结果或关键代码片段（HTML+JS），若有报错贴错误信息。

### Session B：事件委托 + 输入字数统计
1) 讲解要点：事件冒泡、`event.target`/`currentTarget` 差异；委托的好处（少绑监听、动态元素）。
2) 你做：
   - 列表高亮：用事件委托给 UL 绑定一次点击，切换选中项的样式。
   - 输入字数统计：输入框 + 剩余/已输入字数显示；尝试防抖（可选，延迟 200ms）。
3) 回报：控制台无报错的截图/输出，或遇到的问题。

### Session C：可访问性 + 键盘操作 + 小结
1) 讲解要点：
   - aria-label/aria-selected 的用途；可聚焦元素的 `tabindex`；键盘事件 `keydown`。
   - 防抖 vs 节流：适用场景（输入/resize 用防抖，滚动/拖拽用节流）。
2) 你做：
   - 给列表高亮组件加键盘上下键导航，回车确认选中；适当设置 `tabindex` 和 aria 属性。
   - 总结 5 点收获 + 1 个踩坑，写进 `note.md`。
3) 回报：键盘操作效果描述/问题，`note.md` 的收获与踩坑要点。

## 验收清单
- 点击计数、字数统计、列表高亮三个 demo 均可在浏览器直接运行，无控制台报错。
- 列表高亮使用事件委托（监听绑定在容器上）。
- 至少尝试一次防抖或节流，并能说出其适用场景。
- 列表支持键盘上下键移动焦点/选中；关键元素带 aria-label 或 aria-selected。
- `note.md` 记录 5 收获 + 1 踩坑。

## 参考提示（不直接给代码）
- 查询：`document.querySelector('button')`；集合可用 `document.querySelectorAll` 返回 NodeList，可用 for...of 或展开。
- 修改：文本用 `textContent`，类名用 `classList`，样式用 `style` 或切换 class。
- 事件委托：`container.addEventListener('click', (e) => { const item = e.target.closest('li'); ... })`。
- 防抖思路：用定时器延迟执行，重复触发先清除旧定时器；节流则是固定间隔内只执行一次。
- 键盘：监听 `keydown`，判断 `e.key === 'ArrowDown' | 'ArrowUp' | 'Enter'`，更新选中索引。

## 你接下来要做
1) 打开 `week04/exercises` 里为这三项练习创建或更新文件（HTML/JS/CSS 自选结构）。
2) 先完成 Session A 的“点击计数”并运行，贴出结果或问题，我再给你下一步指导。
