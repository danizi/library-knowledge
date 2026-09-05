# D6 Demo · 2026-09-05

工程：`knowledge/Android/demos/fragment/fg-d6-nested-fragment/`

Activity 用 `supportFragmentManager` 装 Parent，并 `setPrimaryNavigationFragment(parent)`。Parent 用 `childFragmentManager` 装 ChildA（根，不入子栈）；按钮 `replace` ChildB + `addToBackStack`。

真机/模拟器：ChildA → 子栈推进 ChildB → 系统返回 → 回到 ChildA。Logcat：ChildB `onDestroy`；Parent / MainActivity hash 不变，没有 `MainActivity onDestroy`。
