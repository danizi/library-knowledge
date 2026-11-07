# Compose 核心 API 分类

**问：** 记 Compose API 时，先按什么分组？

> **一句话：** 先判断这个 API 属于哪一类。然后再记具体名称。

**例：** 一个列表页使用状态、`LazyColumn`、`Text` 或 `Image`。需要副作用时使用 `LaunchedEffect`。

<details>
<summary>答</summary>

先按用途分成 7 类。
这 7 类是状态、资源、布局、组件、协程与生命周期、动画与副作用、窗口适配。
每一类先记 2 到 3 个代表名称。
`Modifier` 横跨很多类。
`Modifier` 单独记在子卡里。

</details>

<details>
<summary>类比</summary>

锤子和钉子分开放。不要混在同一个袋子里。

</details>

<details>
<summary>易错</summary>

- 不要把 `LaunchedEffect` 当作普通函数随意调用。它是副作用。
- 不要把 `Modifier` 放进「组件」这一类就结束。

</details>

<details>
<summary>相关</summary>

- [V-005 | Jetpack Compose](<./V-005 | Jetpack Compose.md>)
- [V-005,4,a | Modifier](<./V-005,4,a | Modifier.md>)

</details>

<details>
<summary>参考</summary>

- [Compose 总览](https://developer.android.com/develop/ui/compose?hl=zh-cn)

</details>
