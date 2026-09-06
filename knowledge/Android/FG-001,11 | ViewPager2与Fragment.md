# ViewPager2与Fragment

**问：**
ViewPager2 为啥用 `FragmentStateAdapter`？离屏页会怎样？为啥别自己 `replace` 一堆 Tab？

**答：**
左右滑 / Tab 多页，用 **ViewPager2** 当容器，用 **`FragmentStateAdapter`** 按位置交出对应 Fragment。  
离屏页（滑远了的）常会 **拆掉 view，甚至销毁实例** 来省内存；滑回来再重建（可能是新实例，`restored=true`）。  
自己对每个 Tab 手写 `replace` 硬撑：页一多，生命周期和内存都难管，也拿不到这套回收策略。

一句话：ViewPager2 配 FragmentStateAdapter；离屏拆 view 省内存，别手写一堆 Tab replace。

**例 / 类比：**
- 例子（`fg-d9-viewpager2`）：三页 + `offscreenPageLimit=1`。跳到 2：Page0 `onDestroyView`/`onDestroy`；跳回 0：新 hash、`restored=true`。
- 类比：书架只留眼前和旁边两本摊开的，远处的合上收回去；翻回来再打开（可能是重新取的一本）。

**易错：**
- 以为滑走的页 view 永远还在，继续改控件或绑 forever 观察。
- 两页时两侧都在 limit 内，往往拆不到——要看「跨远」才拆（本 Demo 用三页跳转演示）。
- 用旧 `FragmentPagerAdapter`（几乎不销毁）当现代方案讲。
- 和 Navigation 混成一件事：Nav 管路由栈；ViewPager2 管同级滑动页。

**相关：**
- Demo：[fg-d9-viewpager2](../demos/fragment/fg-d9-viewpager2/)
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 人 vs view
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 拆 view 后观察绑谁
- [FG-001,9 | Fragment传参与状态](<./FG-001,9 | Fragment传参与状态.md>) — 页码用 arguments
- [FG-001,10 | Navigation与Fragment](<./FG-001,10 | Navigation与Fragment.md>) — 路由 ≠ 滑动多页
- [FG面试题库](./FG面试题库.md) — F14
- [FG错题库](./FG错题库.md)

**参考：**
- [ViewPager2 与 Fragment](https://developer.android.com/guide/navigation/navigation-swipe-view-2?hl=zh-cn)
