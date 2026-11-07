# Android 架构模式

**问：** 官方推荐的应用架构把界面、状态和数据放在哪几层？

> **一句话：** 界面只显示状态，并上报操作。ViewModel 保存界面状态。Repository 提供数据。数据向一个方向流动。

**例：** 列表数据来自 Repository。ViewModel 暴露界面状态。Fragment 只渲染状态，并上报点击。

<details>
<summary>答</summary>

把不同的职责分开。
界面要能度过配置变更。
界面由数据驱动。
同一份数据只有一个可信来源。
数据单向流动。
界面层是 Activity 和 Fragment。
中间层是 ViewModel。
数据层是 Repository。
MVC、MVP 和 MVVM 是常见的分工名称。
现在的官方文档强调原则和分层。

</details>

<details>
<summary>类比</summary>

前厅负责点菜。后厨负责出菜。仓库负责备货。

</details>

<details>
<summary>易错</summary>

- 不要把业务写在 Activity 里。不要把业务写在 Fragment 里。
- 不要在多处各自修改数据副本。数据要有一个可信来源。

</details>

<details>
<summary>相关</summary>

- [FG-001 | Fragment](<./FG-001 | Fragment.md>)
- [DP-001 | 设计模式](<./DP-001 | 设计模式.md>)
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 状态不要堆在 Activity 里

</details>

<details>
<summary>参考</summary>

- [应用架构指南](https://developer.android.com/topic/architecture?hl=zh-cn)

</details>
