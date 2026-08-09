# Modifier

**问：** Compose 里的 `Modifier` 是干啥的？为啥到处都在链式调用？

**答：**  
`Modifier` 是挂在可组合项上的**修饰链**：管大小、间距、背景/形状、点击、对齐、滚动、无障碍等，而不用为每件事造子类。  
写法是从左到右（或按文档推荐顺序）叠上去：`Modifier.fillMaxWidth().padding(16.dp).clickable { … }`。

**例：**
```kotlin
Text(
    "Hello",
    modifier = Modifier
        .padding(8.dp)
        .clickable { /* … */ }
)
```

**易错：**
- **顺序有影响**（先 padding 再背景 vs 先背景再 padding，观感不同）。
- 点击热区、最小尺寸要想清楚，别只改视觉忘了可点范围。

**相关：**
- [V-005,4 | Compose 核心 API 分类](./V-005,4%20%7C%20Compose%20核心%20API%20分类.md) — Modifier 在整体中的位置
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md) — 总入口

**参考：**
- [修饰符列表（官方）](https://developer.android.com/develop/ui/compose/modifiers-list?hl=zh-cn)
