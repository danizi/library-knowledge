# View 的定义

**问：** View 和 ViewGroup 在界面里各做什么？

> **一句话：** ViewGroup 是容器。View 是容器里的控件。界面由容器嵌套控件组成。

**例：** `TextView` 和 `Button` 是 View。`LinearLayout` 和 `RecyclerView` 是 ViewGroup。

<details>
<summary>答</summary>

能看见、能交互的控件，基本都继承 `View`。
ViewGroup 通常自己不绘制内容。
ViewGroup 装入一组子 View。
ViewGroup 是容器。
一整屏界面是容器套容器。
最里面的叶子才是具体控件。

</details>

<details>
<summary>类比</summary>

ViewGroup 像收纳盒。View 像盒里的物件。一个盒子还可以放进更大的盒子。

</details>

<details>
<summary>易错</summary>

- 不要因为 ViewGroup 常常不直接绘制内容就忽略它。测量尺寸、摆放子控件和分发触摸都由它完成。
- Compose 不使用这套继承树。对照见 V-005。

</details>

<details>
<summary>相关</summary>

- [V-002 | 盒子模型](<./V-002 | 盒子模型.md>) — 一个 View 怎样占地方
- [V-003 | 坐标系](<./V-003 | 坐标系.md>) — 点相对屏幕，还是相对控件
- [V-004 | Widget](<./V-004 | Widget.md>) — 先认识常用控件
- [V-005 | Jetpack Compose](<./V-005 | Jetpack Compose.md>) — 另一条声明式界面路径

</details>

<details>
<summary>参考</summary>

- [View 官方文档](https://developer.android.com/reference/android/view/View)

</details>
