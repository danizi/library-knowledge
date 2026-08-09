**第 3 周学习路线（异步思维）**

- 设备与时间：Node 18+、现代浏览器控制台、VS Code；本周建议 2–3 次学习，每次 60–90 分钟。
- 目标：搞懂事件循环/任务队列模型，熟练 Promise 与 async/await 的用法及错误处理。
- 产出：两段实验代码（宏/微任务顺序、fetch 公共 API）+ 一份说明记录。

**学习步骤（按顺序，含时间）**
1) 事件循环与任务队列复盘（5–7 分钟）：梳理调用栈、宏任务、微任务概念；准备 `setTimeout`、`Promise.resolve().then`、`queueMicrotask` 的对照实验。
2) 宏/微任务顺序实验（8–10 分钟）：编写脚本打印同步日志 + 计时器 + Promise 回调的执行顺序，记录观察结论。
3) Promise 基础与错误处理（8–10 分钟）：用 `then/catch/finally` 处理 resolve/reject；故意触发 reject，确认错误会沿链传递并被 `catch` 捕获。
4) async/await 实践（5–8 分钟）：将上一步逻辑改写为 async/await，配合 `try/catch` 捕获错误，对比串行执行与 `Promise.all` 并行的差异。
5) fetch 公共 API（8–10 分钟）：调用公开 JSON API（如 jsonplaceholder），只打印部分字段；模拟错误 URL 或断网，验证网络/解析错误能被捕获。
6) 小结与记录（3–5 分钟）：把执行顺序输出、错误截图或代码片段与心得写入 `week03/note.md`。

**实战提示**
- Node 18+ 自带 `fetch`；若偶发错误可尝试 `node --experimental-fetch` 或改用 `axios`。
- 观察任务顺序时务必加一条同步 `console.log('sync start')` 作为基线。
- `Promise.all` 中任意一个 reject 会让整体 reject，外层需 `try/catch`。
- 错误处理应覆盖：网络失败、JSON 解析失败、自定义业务错误。

**测试与验证**
- 运行宏/微任务脚本，日志顺序应符合“同步 → 微任务 → 宏任务”预期。
- 运行 fetch 脚本能成功打印 JSON 片段；错误场景进入 catch 分支并输出友好提示。
- async/await 版与 Promise 链版输出一致，且错误被成功捕获。

**假设与默认值**
- 使用 Node 18+ 或现代浏览器；可正常访问公共 JSON API（如 jsonplaceholder.typicode.com）。
- 文件之前为空，无需保留旧内容；排版风格与 week02/read.md 保持一致（粗体小节 + 列表）。
- 文档语言保持中文。
