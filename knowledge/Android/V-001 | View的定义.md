# View 的定义

**问：** 界面上看得见、点得着的东西，到底是谁构成的？View 和 ViewGroup 啥关系？

**答：**  
能看见、能交互的控件，基本都从 `View` 长出来。  
`ViewGroup` 多半自己不画内容，是装一堆子 View 的盒子（容器）。界面就是「盒子套盒子 + 叶子控件」。

**例：** `TextView`、`Button` 是 View；`LinearLayout`、`RecyclerView` 是 ViewGroup。

**易错：**
- 别以为 ViewGroup「看不见」就不重要——量尺寸、摆子 View、分发触摸都靠它。
- Compose 里没有这套继承树，对照见 V-005。

**相关：**
- [V-002 | 盒子模型](./V-002%20%7C%20盒子模型.md) — 宽高、padding、margin 怎么占地方
- [V-003 | 坐标系](./V-003%20%7C%20坐标系.md) — 屏幕坐标 vs 视图坐标
- [V-004 | Widget](./V-004%20%7C%20Widget.md) — 常用系统控件先认谁
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md) — 声明式 UI 另一条路

**参考：**
- [View 官方文档](https://developer.android.com/reference/android/view/View)
