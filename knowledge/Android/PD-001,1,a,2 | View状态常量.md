# View 状态常量

**问：** `selector` 和代码里的 `addState` 使用哪些常见状态常量？

> **一句话：** 状态常量是 `selector` 用来匹配 View 状态的名称。

**例：** 按钮按下使用 `state_pressed`。CheckBox 勾选使用 `state_checked`。

<details>
<summary>答</summary>

`state_pressed` 表示按下。
`state_focused` 表示有焦点。
`state_selected` 表示选中。
`state_enabled` 表示启用。
`state_checked` 表示勾选。
`state_activated` 表示激活。
`state_window_focused` 表示窗口有焦点。
在代码里表示某个状态为假时，常常在属性前加负号。
`-state_enabled` 表示禁用。

</details>

<details>
<summary>类比</summary>

门禁卡上有多位权限。对上哪一位，就走哪一扇门。

</details>

<details>
<summary>易错</summary>

- 不要把 `state_selected` 和 `state_activated` 当成同一个状态。它们用于不同场景。
- XML 里写 `false`。代码里用属性前的负号。这两种写法不相同。

</details>

<details>
<summary>相关</summary>

- [PD-001,1,a,1 | 状态列表的使用](<./PD-001,1,a,1 | 状态列表的使用.md>)

</details>

<details>
<summary>参考</summary>

- [StateList 文档](https://developer.android.com/guide/topics/resources/drawable-resource?hl=zh-cn#StateList)

</details>
