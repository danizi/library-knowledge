# 嵌套 Fragment

**问：** `childFragmentManager`、`parentFragmentManager` 和 `supportFragmentManager` 各管理哪一层 Fragment？

> **一句话：** 子 Fragment 使用父 Fragment 的 `childFragmentManager`。Activity 的第一层使用 `supportFragmentManager`。返回先弹子回退栈时，要入栈并调用 `setPrimaryNavigationFragment`。

**例：** `fg-d6-nested-fragment` 里，Activity 装入 Parent，并把 Parent 设为 primary navigation fragment。Parent 用 `childFragmentManager` 装入 ChildA。ChildA 是根页面，不进入子回退栈。然后再 `replace` 为 ChildB，并调用 `addToBackStack`。系统返回后回到 ChildA。Parent 的 hash 不变。MainActivity 的 hash 不变。

<details>
<summary>答</summary>

Activity 用 `supportFragmentManager` 装第一层 Fragment。
在这个 Demo 里，第一层是 Parent。
父 Fragment 里再放入子 Fragment 时，使用父 Fragment 的 `childFragmentManager`。
`childFragmentManager` 属于父 Fragment。它不是 Activity 的 `supportFragmentManager`。
子 Fragment 的 `parentFragmentManager` 是管理这个子 Fragment 的 FragmentManager。
嵌套时，`parentFragmentManager` 等于父 Fragment 的 `childFragmentManager`。
不要把 `parentFragmentManager` 叫成 Activity 的 `supportFragmentManager`。
子页面调用 `addToBackStack` 之后，还要把父 Fragment 设为 `setPrimaryNavigationFragment`。
这时系统返回先检查这个主导航里的子回退栈。
子回退栈空了，才轮到 Activity，或者退出应用。

</details>

<details>
<summary>类比</summary>

大楼前台有一本总册。套房前台有一本分册。返回时先撤套房里的隔板，然后再关大楼。

</details>

<details>
<summary>易错</summary>

- 嵌套子页面时，不要使用 Activity 的 `supportFragmentManager`。子页面会挂到错误的容器。返回顺序会乱。
- 只把子事务入栈、不调用 `setPrimaryNavigationFragment` 时，系统返回可能直接结束 Activity。子回退栈会被跳过。
- 不要把 `parentFragmentManager` 当成 Activity 的 FragmentManager。嵌套时它是父 Fragment 的 `childFragmentManager`。
- 不要给根子页面调用 `addToBackStack`。弹出之后，父容器是空的。
- 不要把这张卡和 `fg-d1` 记成两个规则。`fg-d1` 的 Home 和 Detail 在 Activity 的 FragmentManager 上。这张卡的册子在 Parent 上。规则相同。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d6-nested-fragment](../demos/fragment/fg-d6-nested-fragment/)
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 入栈后，返回先弹出 Fragment。这张卡讲嵌套的那一层
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 宿主用 `supportFragmentManager` 装第一层
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — `replace` 和 `commit` 的写法相同
- [FG面试题库](./FG面试题库.md) — F11
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [FragmentManager 与嵌套](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn#child-fragmentmanager)

</details>
