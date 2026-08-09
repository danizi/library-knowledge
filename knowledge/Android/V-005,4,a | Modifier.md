# Modifier

**问：**
Compose 里的 `Modifier` 是干啥的？为啥到处链式调用？

**答：**
Modifier 是挂在可组合项上的**修饰链**：管大小、间距、外观、点击、对齐、滚动等，不必为每件事造子类。  
从左到右叠加，**顺序会影响结果**。

一句话：Modifier = 给 UI 节点挂属性的链条，顺序有意义。

**例 / 类比：**
```kotlin
Text("Hello", modifier = Modifier.padding(8.dp).clickable { })
```
- 类比：像给快递贴标签——先贴「易碎」再贴「勿压」和反过来观感不同。

**易错：**
- 先 padding 再背景 vs 先背景再 padding，长得不一样。
- 改视觉时别忘了点击热区。

**相关：**
- [V-005,4 | Compose 核心 API 分类](./V-005,4%20%7C%20Compose%20核心%20API%20分类.md)
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md)

**参考：**
- [修饰符列表](https://developer.android.com/develop/ui/compose/modifiers-list?hl=zh-cn)
