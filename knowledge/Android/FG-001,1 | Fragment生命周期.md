# Fragment 生命周期

**问：** Fragment 生命周期和 Activity 比，多记哪几段？为啥要多？

**答：**  
（待写）用自己的话概括：Fragment 多了与「被加入/显示/隐藏/离开」相关的回调；因为它可以在同一个 Activity 里被反复装卸，不能只套 Activity 的 onCreate/onDestroy。

**例：** （待补：从添加到替换时关键回调顺序）

**易错：** （待补）

**相关：**
- [FG-001 | Fragment](./FG-001%20%7C%20Fragment.md) — 总入口

**参考：**
- [Fragment 生命周期（官方）](https://developer.android.com/guide/fragments/lifecycle?hl=zh-cn)
