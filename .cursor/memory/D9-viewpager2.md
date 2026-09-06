# D9 Demo · 2026-09-05

工程：`knowledge/Android/demos/fragment/fg-d9-viewpager2/`

`ViewPager2` + `FragmentStateAdapter`，三页，`offscreenPageLimit=1`。点「跳到 2」：Page0 `onDestroyView` + `onDestroy`；点「跳到 0」：Page0 新实例 `onCreate restored=true`（hash 变了）。别自己 replace 一堆 Tab。
