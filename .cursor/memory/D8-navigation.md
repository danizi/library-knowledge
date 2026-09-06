# D8 Demo · 2026-09-05

工程：`knowledge/Android/demos/fragment/fg-d8-navigation/`

Activity 只挂 `NavHostFragment`（`defaultNavHost=true` + `nav_graph`）。Home 点按钮 `findNavController().navigate(action)` 进 Detail；系统返回：Detail destroy，Home 回来，没有 `MainActivity onDestroy`。不手写 `replace` / `addToBackStack`。
