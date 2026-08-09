# Jetpack Compose

**问：** Jetpack Compose 是啥？和传统 View 体系怎么对照着学？

**答：**  
Compose 是 Android 的**声明式 UI**工具包：你描述「界面现在应该长什么样」，系统根据状态去更新；不像传统 View 那样到处 `findViewById` 再命令式改控件。  
本卡是入口；细节拆到 Preview、范式对比、API 分类、Modifier 子卡。

**例：** 状态变了 → 相关 `@Composable` 自动重组，不必手写「把 TextView 设成 xxx」。

**易错：**
- 别在 Compose 里用命令式思维「找到控件再改」；要想「状态 → UI」。
- 和 XML View 可以共存（`AndroidView` 等），但先把声明式心态立住。

**相关：**
- [V-005,1 | Preview注解](./V-005,1%20%7C%20Preview注解.md) — 不装模拟器也能看 UI
- [V-005,2 | 声明式与命令式对比](./V-005,2%20%7C%20声明式与命令式对比.md) — 两种写法差在哪
- [V-005,4 | Compose 核心 API 分类](./V-005,4%20%7C%20Compose%20核心%20API%20分类.md) — API 先按用途分桶
- [V-005,4,a | Modifier](./V-005,4,a%20%7C%20Modifier.md) — 大小/间距/点击怎么挂上去
- [V-001 | View的定义](./V-001%20%7C%20View的定义.md) — 传统 View 对照

**参考：**
- [Compose 组件官方文档](https://developer.android.com/develop/ui/compose/components?hl=zh-cn)
