**表单事件与默认行为**
- 【结论】表单提交会触发浏览器默认刷新/跳转，需在 `submit` 监听中按需 `preventDefault`。【示例】输入框回车或点击提交按钮都会触发 `submit`。【提示】未声明类型的 `<button>` 默认是 `type="submit"`。
- 【结论】`input/blur/change` 触发时机不同：输入过程中用 `input`，失焦用 `blur`，值真正变更且失焦后才触发 `change`。【示例】单选框切换立刻触发 `change`。【提示】选择正确事件能减少无效校验。

**阻止默认 (preventDefault)**
- 【结论】只在校验失败时阻止默认提交，校验通过要放行。【示例】输入为空时 `event.preventDefault()` 并显示提示；有值则允许提交并清空+聚焦输入框。【提示】阻止默认不等于阻止冒泡，若需阻止冒泡要用 `stopPropagation`。

**HTML5 原生校验**
- 【结论】先用内置属性（`required/minlength/maxlength/pattern/type=email/url`）让浏览器兜底校验，减少 JS 工作量。【示例】`pattern="\\d{4}"` 只允许 4 位数字。【提示】原生提示只在用户交互后出现，别依赖脚本直接触发气泡。
- 【结论】`checkValidity()` 仅返回真假，`reportValidity()` 会顺便弹出原生提示。【示例】`if (!form.reportValidity()) return;` 阻止无效提交。【提示】无效字段可通过 `input.validity` 查看具体原因。

**JS 自定义校验**
- 【结论】在所有校验前先 `trim`，去掉无意义空格。【示例】输入 `"   "` 视作空并提示。【提示】展示错误时集中在一处，减少多点闪烁。
- 【结论】重复项应拦截，基于已存在的 `text` 去重。【示例】列表已有“买牛奶”时拒绝再次添加同名。【提示】比较时也应 `trim` 并可统一为小写。

**localStorage 基础**
- 【结论】localStorage 只存字符串，常见流程是 `JSON.stringify` 写入、`JSON.parse` 读出。【示例】`setItem('key', JSON.stringify(arr))`。【提示】容量约 5MB，操作是同步的，别在大循环里频繁写。
- 【结论】解析时务必防御：`try/catch` + 合理默认值。【示例】`const data = JSON.parse(raw || '[]');` 失败时改用空数组。【提示】坏数据要清理并写回，避免每次都报错。

**Todo 数据结构约定**
- 【结论】保持最小同构结构 `{id, text, done}` 便于存取和渲染。【示例】`[{id:1, text:'买牛奶', done:false}]`。【提示】`id` 可用时间戳或自增，保证唯一即可。
- 【结论】键名固定且带版本，如 `todo-list-v1`，未来升级改版本防冲突。【示例】新结构用 `todo-list-v2`，启动时可尝试迁移旧键。【提示】版本号有助于调试和清理旧数据。

**持久化与恢复策略**
- 【结论】加载时若 `parse` 失败直接回退空数组并覆盖写回，页面不应因坏数据崩溃。【示例】坏 JSON -> catch -> 写入 `[]`。【提示】统一用 `loadTodos`/`saveTodos` 封装。
- 【结论】每次增删改后立即写回 storage，刷新即可恢复最新状态。【示例】切换完成状态后立即调用 `saveTodos(list)`。【提示】减少批量写入遗漏，保持 UI 与存储一致。

**多标签同步（可选）**
- 【结论】`storage` 事件只在“其他标签页”修改时触发，用于跨标签同步。【示例】A 标签添加项，B 标签监听 `storage` 后重新渲染。【提示】当前标签写入不会触发此事件，需手动更新 UI。

**调试与排错清单**
- 【结论】刷新后列表丢失多因未写入或键名不一致。【示例】`getItem('todo-list')` 却 `setItem('todo-list-v1')`。【提示】在控制台打印 `localStorage` 查看实际键值。
- 【结论】表单重复提交常因未阻止默认或按钮类型错误。【示例】按钮省略 `type` 变成 `submit`，导致点击即提交刷新。【提示】显式写 `type="submit"` 或 `type="button"` 区分职责。
