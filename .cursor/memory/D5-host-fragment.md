# D5 Demo · 2026-08-28

切页时宿主 hash 不变（`MainActivity #249279740`），Logcat `HOST`：

- 切 Detail：`DetailFragment onCreate` → `HomeFragment onDestroy`（没有 `MainActivity onDestroy`）
- 切回 Home：反过来

Manifest 只有 `MainActivity`。公共信箱是 Activity 作用域 ViewModel。
系统返回会退出：本 Demo 没 `addToBackStack`。
