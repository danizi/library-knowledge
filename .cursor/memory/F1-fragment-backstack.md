# D1 Demo · 2026-08-29

工程：`knowledge/Android/demos/fragment/fg-d1-backstack/`

Home → Detail → 系统返回：隔断回到 Home，宿主 hash 不变。Logcat `HOST`：`DetailFragment onDestroy`，没有 `MainActivity onDestroy`。

第一次 Home 不入栈；进 Detail 才 `addToBackStack`。`runOnCommit` 不能和 `addToBackStack` 同事务（会崩，已删）。
