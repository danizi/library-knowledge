# Fragment事务

**问：** `add`、`replace`、`hide` 和 `show` 对已有 Fragment 各做什么？

> **一句话：** `replace` 移除容器里的旧 Fragment。`hide` 和 `show` 保留实例和 view。`commit` 异步提交。`commitNow` 立即执行。

**例：** `fg-d3-fragment-transaction` 里，`replace` 之后调用 `commit`。此时 `findFragment` 仍找到 Home。随后 Home 执行 `onDestroyView`。`commitNow` 返回后立刻找到 Detail。第二次 `hide` / `show` 只调用 `onHiddenChanged`。Home 不执行 `onDestroy`。`add` 一个 Extra 时，Home 不执行 `onDestroy`。

<details>
<summary>答</summary>

事务是交给 `FragmentManager` 的一组操作。
先记录添加、替换或隐藏。再提交。
Fragment 不会自己更换。
`replace` 拿走容器里的旧 Fragment。
旧 Fragment 的 view 通常被销毁。系统调用 `onDestroyView`。
这次事务没有入栈时，系统还会调用 `onDestroy`。
`add` 不拿走旧 Fragment。`add` 再放入一个 Fragment。
`hide` 和 `show` 保留实例。
`hide` 和 `show` 保留 view。
`hide` 和 `show` 调用 `onHiddenChanged`。
`hide` 和 `show` 不一定再次调用 `onResume`。
`commit()` 异步执行。
`commit()` 返回后立刻调用 `findFragment`，可能仍找到旧 Fragment。
`commitNow()` 立即执行。
`commitNow()` 返回后可以找到新 Fragment。
不要在同一笔事务里同时使用 `commitNow()` 和 `addToBackStack`。
`setReorderingAllowed(true)` 允许系统合并这一笔事务。官方建议打开它。

</details>

<details>
<summary>类比</summary>

把工单交给前台。前台可能还没有换牌。看着操作当场完成时，牌子已经是新的。

</details>

<details>
<summary>易错</summary>

- 不要把 `hide` 当成 `onPause`。被隐藏的 Fragment 可能已经执行 `onResume`。切换 Tab 的刷新常常写在 `onHiddenChanged`。
- 不要在带 `addToBackStack` 的事务里调用 `runOnCommit`。不要在这笔事务里调用 `commitNow`。系统抛出 `IllegalStateException`。见 F1 和 `fg-d1`。
- `commit()` 返回后，不要认为新 Fragment 已经在容器里。读到的可能仍是旧实例。
- 容器使用 `FragmentContainerView`。不要用普通 `FrameLayout` 代替。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d3-fragment-transaction](../demos/fragment/fg-d3-fragment-transaction/)
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 容器使用 `FragmentContainerView`
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — `hide` 不走完整销毁。实例和 view 的寿命不同
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察绑在 view 的生命周期上
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — `replace` 可以入栈。`runOnCommit` 不能和回退栈写在同一笔
- [FG面试题库](./FG面试题库.md) — F7 F9
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [Fragment 事务](https://developer.android.com/guide/fragments/transactions?hl=zh-cn)

</details>
