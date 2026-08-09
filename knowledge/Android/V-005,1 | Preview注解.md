# Preview 注解

**问：**
`@Preview` 是干啥的？常用参数记哪几个就够？

**答：**
让你在 Android Studio 里直接预览 Composable，少跑模拟器。  
先记：`name`/`group`、`widthDp`/`heightDp`、`showBackground`、`uiMode`、`device`、`locale`/`fontScale`。

一句话：`@Preview` = 编辑器里的「假设备截图」，常用尺寸/主题/设备参数即可。

**例 / 类比：**
```kotlin
@Preview(name = "登录-深色", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginPreview() { LoginScreen() }
```
- 类比：像服装店的假模特——不是真客流，但能看版型。

**易错：**
- 复杂预览常需 `showBackground = true`。
- Preview 别依赖只有真 Activity 才有的东西。

**相关：**
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md)
- [V-005,2 | 声明式与命令式对比](./V-005,2%20%7C%20声明式与命令式对比.md)

**参考：**
- [预览界面](https://developer.android.com/develop/ui/compose/tooling/previews?hl=zh-cn#ui-mode)
