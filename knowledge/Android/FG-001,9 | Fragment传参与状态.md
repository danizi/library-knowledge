# Fragment传参与状态

**问：** 给 Fragment 传入参数时，为什么使用空构造器和 `arguments`？

> **一句话：** 用空构造器创建 Fragment。把参数放进 `arguments`。旋转后从 Bundle 读回参数。不要使用 `setRetainInstance`。

**例：** `fg-d7-fragment-args` 用 `newInstance("book-42")` 打开 Detail。旋转后实例 hash 改变。`restored=true`。界面上的 id 仍是 `book-42`。

<details>
<summary>答</summary>

系统恢复 Fragment 时，先用空构造器创建实例。
然后系统把保存的 Bundle 填回去。
旋转会触发这次恢复。
进程被杀死之后再创建，也会触发这次恢复。
构造器 `DetailFragment(id)` 里的字段，系统恢复时找不到。
这个 id 会丢失。
正确做法是调用 `DetailFragment()`。
把参数放进 `arguments`。常用 `bundleOf`。
常常把这两步包成 `newInstance`。
`arguments` 和 Fragment 状态一起保存。
旋转后，新实例可以用 `requireArguments()` 读回参数。
身份这类轻量参数放在 `arguments` 里。
页面内持续的业务状态仍放在 ViewModel 里。
`setRetainInstance` 已经过时。不要使用它。

</details>

<details>
<summary>类比</summary>

旅馆按空房间重建。行李条贴在门上。不要指望上次提着行李进门的那个人还在。

</details>

<details>
<summary>易错</summary>

- 不要用带参数的构造器传递参数。配置变更后，字段是空的。
- 不要把大对象或列表放进 `arguments`。Bundle 会变大。大对象放在磁盘或 ViewModel 里。
- 不要把 `arguments`、Fragment Result API 和共享 ViewModel 当成同一件事。打开时的参数、回传的一次结果、持续状态是三件事。
- 不要把 `setRetainInstance` 当作保存状态的方案。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d7-fragment-args](../demos/fragment/fg-d7-fragment-args/)
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 旋转销毁实例。轻量数据靠 Bundle
- [FG-001,4 | Fragment通信](<./FG-001,4 | Fragment通信.md>) — 通信使用 ViewModel 或 Result。这张卡讲打开时的参数
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 空构造器是系统恢复的前提
- [FG面试题库](./FG面试题库.md) — F12
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [向 Fragment 传递数据](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn#pass-data)

</details>
