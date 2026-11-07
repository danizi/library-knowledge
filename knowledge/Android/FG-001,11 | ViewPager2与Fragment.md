# ViewPager2与Fragment

**问：** ViewPager2 为什么使用 `FragmentStateAdapter`？

> **一句话：** ViewPager2 用 `FragmentStateAdapter` 按位置提供 Fragment。离开屏幕的页面会拆掉 view，用来减少内存。不要为每个 Tab 手写 `replace`。

**例：** `fg-d9-viewpager2` 有三页。`offscreenPageLimit` 是 1。跳到第 2 页时，Page0 执行 `onDestroyView` 和 `onDestroy`。跳回第 0 页时，实例 hash 是新的。`restored` 是 true。

<details>
<summary>答</summary>

左右滑动或多 Tab 页面使用 ViewPager2 作为容器。
`FragmentStateAdapter` 按位置交出对应的 Fragment。
滑远的页面常常拆掉 view。
滑远的页面有时销毁实例。
这样做是为了减少内存。
滑回来时，系统再创建页面。
新页面可能是新实例。`restored` 可以是 true。
不要对每个 Tab 手写 `replace` 来维持全部页面。
页面变多时，手写替换难管理生命周期。
页面变多时，手写替换难管理内存。
手写替换也得不到这套回收策略。

</details>

<details>
<summary>类比</summary>

书架只摊开眼前和旁边的书。远处的书合上并收回。再翻到它时，再打开一本。这本可能是重新取的。

</details>

<details>
<summary>易错</summary>

- 不要认为滑走的页面的 view 永远还在。不要继续修改那些控件。不要用 `observeForever` 观察它们。
- 只有两页、并且两页都在 `offscreenPageLimit` 内时，常常拆不掉 view。要滑过更远的页才会拆。这个 Demo 用三页跳转来演示。
- 不要把旧的 `FragmentPagerAdapter` 当作现在的方案。它几乎不销毁页面。
- 不要把 Navigation 和 ViewPager2 当成同一件事。Navigation 管理路由栈。ViewPager2 管理同一级的滑动页。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d9-viewpager2](../demos/fragment/fg-d9-viewpager2/)
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 实例和 view 的寿命不同
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — view 拆掉之后，观察绑在谁身上
- [FG-001,9 | Fragment传参与状态](<./FG-001,9 | Fragment传参与状态.md>) — 页码放在 `arguments` 里
- [FG-001,10 | Navigation与Fragment](<./FG-001,10 | Navigation与Fragment.md>) — 路由和滑动多页不是同一件事
- [FG面试题库](./FG面试题库.md) — F14
- [FG错题库](./FG错题库.md)

</details>

<details>
<summary>参考</summary>

- [ViewPager2 与 Fragment](https://developer.android.com/guide/navigation/navigation-swipe-view-2?hl=zh-cn)

</details>
