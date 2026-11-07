# Fragment 通信

**问：** 两个 Fragment 传递数据时，优先使用哪两种方式？

> **一句话：** 持续状态使用同一个 ViewModel。一次性结果使用 Fragment Result API。

**例：** `act-d5-host-fragment` 里，Detail 写入宿主 ViewModel。Home 观察同一个 ViewModel。宿主切页时 `markSwitch` 会覆盖 Detail 写入的文字。ViewModel 没有丢失。见错题库 F4。`fg-d5-fragment-result` 里，Home 在 `onCreate` 监听 `city_pick`。Detail 调用 `setFragmentResult` 后弹出自己。Home 显示选中的城市。Home 不调用 `findFragment`。

<details>
<summary>答</summary>

不要让两个 Fragment 互相持有对方的实例。
持续状态使用共享 ViewModel。
共享 ViewModel 使用同一个作用域。常见作用域是 Activity。
选中项和草稿放在这个 ViewModel 里。
双方都要读取的状态也放在这个 ViewModel 里。
一次性结果使用 Fragment Result API。
接收方调用 `setFragmentResultListener(key)`。
发送方调用 `setFragmentResult(key, bundle)`。
发送方常常接着调用 `popBackStack`。
接口回调是旧写法。
配置变更之后，互相持有的引用容易失效。

</details>

<details>
<summary>类比</summary>

公共信箱存放持续状态。快递签收单传递一次性结果。不要把纸条塞进对方的口袋。

</details>

<details>
<summary>易错</summary>

- 不要在配置变更之后仍持有另一个 Fragment 的强引用。
- 不要用全局单例代替共享 ViewModel。不要用全局单例代替 Fragment Result API。
- 不要把一次性结果放进 ViewModel 并且不清除。一次性结果和持续状态不是同一种数据。
- 系统返回不会自动携带 Fragment Result。没有调用 `setFragmentResult`，就没有结果。

</details>

<details>
<summary>相关</summary>

- Demo：[act-d5-host-fragment](../demos/activity/act-d5-host-fragment/) — 共享 ViewModel
- Demo：[fg-d5-fragment-result](../demos/fragment/fg-d5-fragment-result/) — Fragment Result API
- [FG-001 | Fragment](<./FG-001 | Fragment.md>)
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 共享 ViewModel 挂在宿主上
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察 UI 时绑在 view 上
- [AAP-001 | Android 架构模式](<./AAP-001 | Android 架构模式.md>)
- [FG-001,9 | Fragment传参与状态](<./FG-001,9 | Fragment传参与状态.md>) — 那张卡讲打开时传入的参数。这张卡讲通信
- [FG面试题库](./FG面试题库.md) — F4
- [FG错题库](./FG错题库.md) — F4：切页覆盖了 ViewModel 里的文字

</details>

<details>
<summary>参考</summary>

- [Fragment 通信](https://developer.android.com/guide/fragments/communicate?hl=zh-cn)

</details>
