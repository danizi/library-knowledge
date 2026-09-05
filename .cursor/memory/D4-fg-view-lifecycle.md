# D4 Demo · 2026-09-05

工程：`knowledge/Android/demos/fragment/fg-d4-view-lifecycle/`

Logcat 滤 `HOST`：

- hide/show：第二次只有 `onHiddenChanged`，没有 `onDestroyView`
- replace+入栈：Home `onDestroyView`，没有 `onDestroy`（人还在栈里）
- 安全观察 `viewLifecycleOwner`：拆 view 后发信箱，SAFE 不再回调
- 反例 `observeForever`：拆 view 后仍收到，`requireView()` → `IllegalStateException`（已 catch 打 HOST:E）

面试答 F10：观察绑 `viewLifecycleOwner`，别绑 Fragment / forever 乱碰 view。

View 层：Activity / Fragment 已改 **ViewBinding**（`inflate` / `bind`），不再 `findViewById`。`act-d5` + `fg-d1`–`fg-d4` 同改；`act-d0`–`act-d4` 仍是 Compose。
