# Week04 知识点速查（DOM/事件/可访问性）

## DOM 查询与修改
- 选择：`document.querySelector(selector)` 返回单个元素；`querySelectorAll` 返回 NodeList，可用 `for...of` 遍历。
- 文本 vs HTML：`textContent` 设置纯文本；`innerHTML` 会解析为 HTML，防止 XSS 时用 textContent。
- 属性：`getAttribute/setAttribute`；布尔属性可用 `element.checked = true/false`。
- 样式/类名：
  - 优先用 `classList.add/remove/toggle/contains` 控制样式。
  - 内联样式可用 `element.style.backgroundColor = 'red'`，但推荐通过 class 切换。

## 事件基础
- 注册：`element.addEventListener('click', handler)`；常见类型：click、input、keydown、keyup、submit。
- 冒泡/捕获：默认监听在冒泡阶段；捕获可在第三参传 `{ capture: true }`。
- `event.target` 是实际触发元素，`event.currentTarget` 是绑定监听的元素。

## 事件委托
- 场景：列表/表格等大量子元素，或子元素动态生成。
- 做法：把监听绑定在父容器上，使用 `event.target`（结合 `closest`）找到实际子元素。
- 示例思路：
  ```js
  list.addEventListener('click', (e) => {
    const item = e.target.closest('li');
    if (!item) return; // 点击了空白处
    // 在这里切换选中样式
  });
  ```

## 键盘与可访问性
- 可聚焦：按钮、输入框等天然可聚焦；其他元素可加 `tabindex="0"` 让其可用键盘选中。
- aria：
  - `aria-label` 提供无视觉文本的说明。
  - 列表当前选中项可标记 `aria-selected="true"`。
- 键盘事件：监听 `keydown`，关注 `e.key`（如 `ArrowUp`、`ArrowDown`、`Enter`）；必要时 `e.preventDefault()` 阻止默认滚动。

## 防抖与节流（加分项概念）
- 防抖（debounce）：高频触发只在“停止触发一段时间后”执行一次，适合输入框实时搜索。思路：每次触发先清除旧定时器，再设新定时器。
- 节流（throttle）：固定时间间隔内只执行一次，适合滚动、窗口 resize。思路：记录上次执行时间或使用定时器锁。

## 小组件拆分提示
- 结构分层：容器（list/wrapper） + 项（item） + 状态 class（active/selected/disabled）。
- 把数据状态（当前计数、选中索引）放在 JS 变量，视图通过 class/text 更新。
- 避免在事件里直接依赖全局 DOM 查找，优先缓存需要的节点。

## 练习最小骨架示例（不直接给完整代码）
### 点击计数页面
- HTML 结构：`<h1>` 标题 + `<p>次数：<span id="count">0</span></p>` + `<button id="btn">点击我</button>`；可加提示 `<p id="tip"></p>`。
- JS 放置：写在页面底部 `<script>`，位置在 `</body>` 前。
- JS 步骤：
  1) `const btn = document.querySelector('#btn'); const countEl = document.querySelector('#count');`
  2) `let count = 0;`
  3) `btn.addEventListener('click', () => { count += 1; countEl.textContent = count; });`

### 列表高亮（事件委托）
- HTML 结构：`<ul id="city-list">` 下若干 `<li class="item" tabindex="0">文本</li>`。
- CSS 提示：`.active { background:#eef; }`
- JS 思路：
  - 缓存容器：`const list = document.querySelector('#city-list');`
  - 监听一次点击：`list.addEventListener('click', (e) => { const item = e.target.closest('.item'); if (!item) return; ... })`
  - 在回调里先移除所有 `.active`，再给当前 `item` 加 `.active`。

### 输入字数统计（可选防抖）
- HTML 结构：`<textarea id="msg"></textarea>` + `<p>已输入：<span id="used">0</span> / 50</p>`。
- JS 思路：
  - 取元素：`const textarea = document.querySelector('#msg'); const used = document.querySelector('#used');`
  - 监听 `input` 事件，读取 `textarea.value.length` 更新 `used.textContent`。
  - 想试防抖：定义 `let timer;`，在 `input` 回调里 `clearTimeout(timer); timer = setTimeout(updateCount, 200);`

### 键盘操作（加分）
- 前提：列表项有 `tabindex="0"` 可聚焦。
- 监听：在容器上 `keydown`，判断 `e.key` 为 `ArrowUp/ArrowDown/Enter`。
- 状态：维护一个当前索引变量 `currentIndex`；按键时计算新索引，先移除旧 `.active` 再给新项加 `.active` 并设置 `aria-selected="true"`。

### 可访问性附加项
- 给按钮/输入加 `aria-label="描述动作"`。
- 高亮项同步 `aria-selected`，容器可加 `role="listbox"`，子项 `role="option"`（可选）。
