# D5 Demo · 2026-09-05

工程：`knowledge/Android/demos/fragment/fg-d5-fragment-result/`

Home 在 `onCreate` 里 `setFragmentResultListener("city_pick")`。打开 Detail（入栈）→ 点「回传 Result 并返回」→ `setFragmentResult` + `popBackStack`。Logcat：`Home 收到 Result`，UI「签收单：选了「…」」。全程不互握 Fragment 引用。

和 `act-d5` 共享 ViewModel 对照：VM = 持续信箱；Result = 一次性签收单。
