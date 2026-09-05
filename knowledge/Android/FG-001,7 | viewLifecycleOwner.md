# viewLifecycleOwner

**问：**
`getActivity()` / `view` 为啥会空？LiveData / Flow 观察该绑谁？为啥不能绑 Fragment 自己或 `observeForever` 乱碰 UI？

**答：**
`getActivity()` 在没 attach 或已 detach 时为空；`view` / `requireView()` 在 **`onDestroyView` 之后** 为空或抛异常——Fragment 实例还可能活着（尤其入了回退栈）。  
LiveData / Flow 观察 UI 时，绑 **`viewLifecycleOwner`**：它跟 **view** 走，`onDestroyView` 后自动停订阅，不会在房间拆了还改控件。绑 Fragment 自己（`this`）时，入栈后常是 STOPPED，LiveData 可能根本不回调；`observeForever` 更不管生命周期，view 拆了仍会收到，再 `requireView()` 就崩。

一句话：订阅绑房间（`viewLifecycleOwner`），别绑人，更别 forever 乱碰已拆的 view。

**例 / 类比：**
- 例子（`fg-d4-view-lifecycle`）：SAFE 用 `viewLifecycleOwner`，入栈拆 view 后再「宿主发信箱」不再回调；反例 `observeForever` 仍收到，`requireView()` → `IllegalStateException`（Logcat `HOST:E`）。
- 类比：快递写「住户」可能送到已拆的旧址；写「当前这套房」拆了就停送。

**易错：**
- 在 `onDestroyView` 之后还握着 ViewBinding / view 引用不放空。
- 用 `observeForever` 图省事却忘了 `removeObserver` → 泄漏 + 崩。
- 以为「Fragment 没 Destroy 就能碰 view」——入栈 replace 正好打脸。

**相关：**
- Demo：[fg-d4-view-lifecycle](../demos/fragment/fg-d4-view-lifecycle/)
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 人 vs 房间两层寿命
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — replace 拆 view；hide 不拆
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 状态仍放 ViewModel；观察绑 view
- [FG面试题库](./FG面试题库.md) — F10
- [FG错题库](./FG错题库.md)

**参考：**
- [Fragment 生命周期与 viewLifecycleOwner](https://developer.android.com/guide/fragments/lifecycle?hl=zh-cn)
