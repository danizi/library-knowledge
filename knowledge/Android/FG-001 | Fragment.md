# Fragment

**问：** Fragment 依附在哪里，它自己能不能单独存在？

> **一句话：** Fragment 是 Activity 里可以装上和拆下的子界面。它不能单独存在。

**例：** 底部有三个 Tab。每个 Tab 是一个 Fragment。宿主是一个 Activity。

<details>
<summary>答</summary>

Fragment 是 Activity 中的一块子界面。
Fragment 有自己的布局。
Fragment 有自己的生命周期。
Activity 仍然存在时，系统可以添加 Fragment。
Activity 仍然存在时，系统可以替换 Fragment。
使用 Fragment 拆分过大的 Activity。
使用 Fragment 做底部导航、多个 Tab 或平板上的多栏。
Fragment 不能单独存在。
Fragment 必须挂在 Activity 上。
Fragment 也可以挂在已经挂上的父 Fragment 上。
容器优先使用 `FragmentContainerView`。

</details>

<details>
<summary>类比</summary>

Activity 是一栋楼。Fragment 是楼里可以更换的隔断。Fragment 不是另一栋楼。

</details>

<details>
<summary>易错</summary>

- 不要用普通 `FrameLayout` 代替 `FragmentContainerView`。
- 不要把生命周期、通信和回退栈记成同一件事。

</details>

<details>
<summary>相关</summary>

- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>)
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察 UI 时使用 `viewLifecycleOwner`
- [FG-001,2 | DialogFragment](<./FG-001,2 | DialogFragment.md>) — 对话框也进入 FragmentManager
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 系统返回先弹出 Fragment
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — `add`、`replace`、`hide`；`commit` 是异步的
- [FG-001,4 | Fragment通信](<./FG-001,4 | Fragment通信.md>)
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 宿主管理窗口，Fragment 放在容器里
- [ERR-001 | 开发踩坑记录](<./ERR-001 | 开发踩坑记录.md>)
- [V-001 | View的定义](<./V-001 | View的定义.md>)

</details>

<details>
<summary>参考</summary>

- [Fragments 指南](https://developer.android.com/guide/fragments?hl=zh-cn)

</details>
