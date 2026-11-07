# Fragment 生命周期

**问：** Fragment 比 Activity 多出哪些生命周期回调？

> **一句话：** Fragment 增加 `onCreateView`、`onDestroyView` 和 `onHiddenChanged`。`hide` 和 `show` 不一定再次调用 `onResume`。

**例：** `fg-d4-view-lifecycle` 里，第二次 `hide` / `show` 只调用 `onHiddenChanged`。`replace` 并 `addToBackStack` 之后，Home 调用 `onDestroyView`。Home 不调用 `onDestroy`。

<details>
<summary>答</summary>

Fragment 有创建、启动、暂停、停止和销毁回调。
Fragment 还有 `onCreateView` 和 `onDestroyView`。
Fragment 还有 `onHiddenChanged`。
同一个 Activity 可以多次添加、替换、隐藏和显示 Fragment。
不要只用 Activity 的 `onCreate` 和 `onDestroy` 描述 Fragment。
Fragment 实例和 Fragment 的 view 寿命不同。
`hide` 和 `show` 通常保留 Fragment 实例。
`hide` 和 `show` 通常保留 view。
`hide` 和 `show` 调用 `onHiddenChanged`。
`hide` 和 `show` 不一定再次调用 `onResume`。
`replace` 加上 `addToBackStack` 时，Fragment 实例可以留在回退栈里。
这时系统调用 `onDestroyView`。
这时系统不调用 `onDestroy`。
切换 Tab 时，把刷新写在 `onHiddenChanged(hidden == false)`。
不要只靠 `onResume` 做这次刷新。见 ERR-001。

</details>

<details>
<summary>类比</summary>

租客还在户籍上。房子可能已经拆掉。不要把信送到旧门牌。

</details>

<details>
<summary>易错</summary>

- 不要认为每次切换 Tab 都会调用 `onResume`。
- view 销毁之后，不要调用 `requireView()`。不要修改已经销毁的控件。见 FG-001,7。
- 不要把 `hide` 当成 `onPause`。被隐藏的 Fragment 可能已经执行过 `onResume`。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d4-view-lifecycle](../demos/fragment/fg-d4-view-lifecycle/)
- [FG-001 | Fragment](<./FG-001 | Fragment.md>)
- [FG-001,2 | DialogFragment](<./FG-001,2 | DialogFragment.md>) — 旋转后 `onCreate restored=true`，然后再 `onCreateDialog`
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 弹出 Detail 时走销毁
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — `replace` 销毁 view。`hide` 调用 `onHiddenChanged`
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察绑在 view 的生命周期上
- [FG-001,11 | ViewPager2与Fragment](<./FG-001,11 | ViewPager2与Fragment.md>) — 离屏页会拆掉 view
- [ERR-001 | 开发踩坑记录](<./ERR-001 | 开发踩坑记录.md>)
- [FG面试题库](./FG面试题库.md) — F2

</details>

<details>
<summary>参考</summary>

- [Fragment 生命周期](https://developer.android.com/guide/fragments/lifecycle?hl=zh-cn)

</details>
