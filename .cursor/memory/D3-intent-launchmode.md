# D3 Demo · 2026-08-27

工程：`knowledge/Android/demos/activity/act-d3-intent-extra/`

Logcat 搜 `INTENT`。

- 正向 Extra：第二屏 `Extra = xiaomin`
- 回传：第一屏 `回传：收到了 xiaomin`（Activity Result API）
- singleTop：连点「再开一次自己」只走 `onNewIntent`，栈里仍只有 1 个 MainActivity；`ping=2`
