# Compose 核心 API 分类

**问：**
Compose API 那么多，怎么先分桶，免得记成一锅粥？

**答：**
先按用途分 7 桶，每桶先记 2～3 个代表：状态、资源、布局、组件、协程与生命周期、动画与副作用、窗口适配。  
`Modifier` 横切很多桶，单独记子卡。

一句话：先认「这 API 属于哪一桶」，再记具体名字。

**例 / 类比：**
- 列表页 ≈ 状态 + `LazyColumn` + `Text`/`Image` + 必要时 `LaunchedEffect`。
- 类比：工具箱分区——锤子钉子分开放，比一袋混装好找。

**易错：**
- 副作用（`LaunchedEffect`）别当普通函数乱调。
- 别把 Modifier 糊进「组件」桶里了事。

**相关：**
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md)
- [V-005,4,a | Modifier](./V-005,4,a%20%7C%20Modifier.md)

**参考：**
- [Compose 总览](https://developer.android.com/develop/ui/compose?hl=zh-cn)
