# Fragment事务

**问：**
`add` / `replace` / `hide·show` 差在哪？`commit` 和 `commitNow` 呢？为啥回退栈事务不能配 `runOnCommit`？

**答：**
事务是交给 `FragmentManager` 的**一笔工单**：先记下要加、换、藏，再提交。隔断不会自己换。  
**replace** 把容器里旧的拿走，旧隔断 **view 常被拆掉**（`onDestroyView`，没入栈还会 `onDestroy`）。**add** 不拿走旧的，再叠一块。**hide / show** 实例和 view 都还在，只走 `onHiddenChanged`，不一定再 `onResume`。  
`commit()` 异步，写完立刻 `findFragment` 可能还是旧的；`commitNow()` 马上执行，立刻能 find 到新的，但**不能**和 `addToBackStack` 写在同一笔。`setReorderingAllowed(true)` 允许这一笔被合并优化，官方建议打开。

一句话：replace 拆房间，hide 只关灯；commit 排队，commitNow 当场干完。

**例 / 类比：**
- 例子（`fg-d3-fragment-transaction`）：`replace·commit` 立刻 find 仍是 Home，随后才 `onDestroyView`；`commitNow` 立刻 find 已是 Detail。hide/show 第二次只有 `onHiddenChanged`，Home 不 Destroy。add Extra，Home 不 Destroy。
- 类比：工单交前台（commit）可能还没换牌；盯着师傅当场换完（commitNow）牌已经是新的。

**易错：**
- hide 当成 `onPause`。被藏着的仍可能 `onResume`；切 Tab 刷新常靠 `onHiddenChanged`。
- `addToBackStack` 再 `runOnCommit` 或 `commitNow` → `IllegalStateException`（F1 / fg-d1 栽过）。
- `commit()` 之后立刻当新 Fragment 已经在，读到的还是旧实例。
- 容器用普通 `FrameLayout` 凑，不用 `FragmentContainerView`。

**相关：**
- Demo：[fg-d3-fragment-transaction](../demos/fragment/fg-d3-fragment-transaction/)
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 容器用 `FragmentContainerView`
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — hide 不走完整销毁；人 vs view
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察绑谁、view 何时空
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — replace 才能入栈；`runOnCommit` 不能配回退栈
- [FG面试题库](./FG面试题库.md) — F7 F9
- [FG错题库](./FG错题库.md)

**参考：**
- [Fragment 事务](https://developer.android.com/guide/fragments/transactions?hl=zh-cn)
