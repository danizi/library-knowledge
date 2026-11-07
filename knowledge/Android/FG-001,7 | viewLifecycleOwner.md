# viewLifecycleOwner

**问：** 观察界面时，LiveData 或 Flow 应该绑在谁的生命周期上？

> **一句话：** 观察 UI 时使用 `viewLifecycleOwner`。不要绑在 Fragment 实例上。不要用 `observeForever` 更新已经销毁的 view。

**例：** `fg-d4-view-lifecycle` 里，SAFE 使用 `viewLifecycleOwner`。入栈并拆掉 view 之后，宿主再发数据时不再回调。反例使用 `observeForever`。view 拆掉后仍然收到数据。再调用 `requireView()` 会抛出 `IllegalStateException`。Logcat 标签是 `HOST:E`。

<details>
<summary>答</summary>

Fragment 还没有 attach，或者已经 detach 时，`getActivity()` 返回空。
`onDestroyView` 之后，`view` 为空。
`onDestroyView` 之后，`requireView()` 抛出异常。
这时 Fragment 实例可能还在。入了回退栈时就是这样。
观察 UI 的 LiveData 或 Flow 时，使用 `viewLifecycleOwner`。
`viewLifecycleOwner` 跟随 view。
`onDestroyView` 之后，这个观察自动停止。
观察不会在 view 销毁之后再改控件。
把观察绑在 Fragment 自己（`this`）上时，入栈后生命周期常常是 STOPPED。
这时 LiveData 可能不回调。
`observeForever` 不跟随生命周期。
view 拆掉之后，`observeForever` 仍会收到数据。
这时再调用 `requireView()` 会崩溃。

</details>

<details>
<summary>类比</summary>

快递写「住户」，可能送到已经拆掉的旧地址。快递写「当前这套房」，房子拆了就停止投递。

</details>

<details>
<summary>易错</summary>

- `onDestroyView` 之后，不要继续持有 ViewBinding 或 view 引用。把引用置空。
- 不要用 `observeForever` 省略生命周期。忘记 `removeObserver` 会造成泄漏，再碰 view 会崩溃。
- 不要认为 Fragment 还没有 `onDestroy` 就还能使用 view。`replace` 并入栈时，view 会先销毁。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d4-view-lifecycle](../demos/fragment/fg-d4-view-lifecycle/)
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — Fragment 实例和 view 的寿命不同
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — `replace` 销毁 view。`hide` 不销毁 view
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 状态仍放在 ViewModel。观察绑在 view 上
- [FG面试题库](./FG面试题库.md) — F10
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [Fragment 生命周期与 viewLifecycleOwner](https://developer.android.com/guide/fragments/lifecycle?hl=zh-cn)

</details>
