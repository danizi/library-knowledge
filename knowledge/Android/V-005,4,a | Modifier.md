# Modifier

**问：** `Modifier` 挂在可组合项上时做什么？

> **一句话：** `Modifier` 是挂在可组合项上的一条修饰链。链上的顺序会影响结果。

**例：** 下面的文字先加 8dp 的 padding，再接收点击。

```kotlin
Text(
    "Hello",
    modifier = Modifier.padding(8.dp).clickable { }
)
```

<details>
<summary>答</summary>

`Modifier` 是挂在可组合项上的修饰链。
这条链可以设置大小、间距、外观、点击、对齐和滚动。
不需要为每一项设置单独建一个子类。
修饰从左到右叠加。
顺序会影响结果。

</details>

<details>
<summary>类比</summary>

先贴「易碎」，再贴「勿压」。反过来贴，看到的顺序不同。

</details>

<details>
<summary>易错</summary>

- 先 `padding` 再背景，和先背景再 `padding`，外观不同。
- 修改外观时，不要忘记点击区域也会跟着变。

</details>

<details>
<summary>相关</summary>

- [V-005,4 | Compose 核心 API 分类](<./V-005,4 | Compose 核心 API 分类.md>)
- [V-005 | Jetpack Compose](<./V-005 | Jetpack Compose.md>)

</details>

<details>
<summary>参考</summary>

- [修饰符列表](https://developer.android.com/develop/ui/compose/modifiers-list?hl=zh-cn)

</details>
