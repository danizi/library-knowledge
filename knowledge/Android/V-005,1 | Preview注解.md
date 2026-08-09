# Preview 注解

**问：** `@Preview` 是干啥的？常用参数记哪几个就够用？

**答：**  
`@Preview` 让你在 Android Studio 里**直接预览 Composable**，不用每次跑模拟器。  
先记这些就够日常用：  
- `name` / `group`：预览标题和分组  
- `widthDp` / `heightDp`：预览尺寸  
- `showBackground` / `backgroundColor`：要不要背景  
- `uiMode`：浅色/深色等  
- `device`：模拟哪种设备框  
- `locale` / `fontScale`：语言和字体缩放  

**例：**
```kotlin
@Preview(name = "登录-深色", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginPreview() { LoginScreen() }
```

**易错：**
- 想看壁纸/复杂设备背景时，常需配合 `showBackground = true`（以官方说明为准）。
- Preview 函数应是无参或提供默认预览数据的 Composable，别依赖 Activity 才有的东西。

**相关：**
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md) — Compose 入口
- [V-005,2 | 声明式与命令式对比](./V-005,2%20%7C%20声明式与命令式对比.md) — 为啥能「改状态就刷新预览」

**参考：**
- [预览您的界面（官方）](https://developer.android.com/develop/ui/compose/tooling/previews?hl=zh-cn#ui-mode)
