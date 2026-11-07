# Preview 注解

**问：** `@Preview` 在 Android Studio 里显示什么？

> **一句话：** `@Preview` 在编辑器里预览一个 Composable。它不是在模拟器里运行应用。

**例：** 下面这个预览的名称是「登录-深色」。它显示背景，并使用夜间模式。

```kotlin
@Preview(
    name = "登录-深色",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginPreview() {
    LoginScreen()
}
```

<details>
<summary>答</summary>

`@Preview` 让 Android Studio 直接预览 Composable。
这样可以少启动模拟器。
先记住这些参数：`name`、`group`、`widthDp`、`heightDp`。
再记住 `showBackground`、`uiMode`、`device`、`locale` 和 `fontScale`。

</details>

<details>
<summary>类比</summary>

服装店的假模特不是真的顾客。假模特用来看版型。

</details>

<details>
<summary>易错</summary>

- 复杂预览常常需要 `showBackground = true`。
- 不要让 Preview 依赖只有真实 Activity 才有的对象。

</details>

<details>
<summary>相关</summary>

- [V-005 | Jetpack Compose](<./V-005 | Jetpack Compose.md>)
- [V-005,2 | 声明式与命令式对比](<./V-005,2 | 声明式与命令式对比.md>)

</details>

<details>
<summary>参考</summary>

- [预览界面](https://developer.android.com/develop/ui/compose/tooling/previews?hl=zh-cn#ui-mode)

</details>
