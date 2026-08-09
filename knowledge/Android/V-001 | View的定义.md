# View 的定义

**问：**
界面上看得见、点得着的东西，到底是谁构成的？View 和 ViewGroup 啥关系？

**答：**
能看见、能交互的控件，基本都从 `View` 长出来。  
`ViewGroup` 多半自己不画内容，是装一堆子 View 的盒子（容器）。  
整屏界面可以想成：盒子套盒子，最外层叶子才是具体控件。

一句话：界面 = ViewGroup（盒子）嵌套 + View（叶子控件）。

**例 / 类比：**
- 例子：`TextView`、`Button` 是 View；`LinearLayout`、`RecyclerView` 是 ViewGroup。
- 类比：ViewGroup 像收纳盒，View 像盒里的物件；盒子本身也可以再被更大的盒子装走。

**易错：**
- 别因为 ViewGroup「常常不直接画内容」就忽略它——量尺寸、摆子控件、分发触摸都靠它。
- Compose 不是这套继承树，对照见 V-005。

**相关：**
- [V-002 | 盒子模型](./V-002%20%7C%20盒子模型.md) — 单个 View 怎么占地方
- [V-003 | 坐标系](./V-003%20%7C%20坐标系.md) — 点落在屏幕还是控件里
- [V-004 | Widget](./V-004%20%7C%20Widget.md) — 常用叶子控件先认谁
- [V-005 | Jetpack Compose](./V-005%20%7C%20Jetpack%20Compose.md) — 声明式 UI 另一条路

**参考：**
- [View 官方文档](https://developer.android.com/reference/android/view/View)
