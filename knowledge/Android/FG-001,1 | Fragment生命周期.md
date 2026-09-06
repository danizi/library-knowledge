# Fragment 生命周期

**问：**
Fragment 生命周期和 Activity 比，多记哪几段？`hide/show` 会不会每次 `onResume`？人还在时 view 会不会没了？

**答：**
Fragment 除了熟悉的创建/启动/暂停/停止/销毁，还多了和「装进界面、显隐、拆界面」相关的回调：尤其是 **view 的创建/销毁**（`onCreateView` / `onDestroyView`）和 **`onHiddenChanged`**。  
因为它可以在同一个 Activity 里被反复装卸、隐藏显示，不能只套 Activity 的 onCreate/onDestroy。  

两层寿命要分开记：**Fragment 实例**（人）和 **view**（房间）。`hide/show`：人和房间往往都在，只关灯，走 `onHiddenChanged`，**不一定**再走你期望的 `onResume` 刷新。`replace` + `addToBackStack`：人可以还在回退栈里，但 **view 已拆**（`onDestroyView`，没有 `onDestroy`）。

一句话：多出来的生命周期是为了描述「同一宿主里装卸/显隐」；人活着 ≠ 房间还在。

**例 / 类比：**
- 例子（`fg-d4-view-lifecycle`）：hide/show 第二次只有 `onHiddenChanged`；replace+入栈后 Home 有 `onDestroyView`、没有 `onDestroy`。切 Tab 刷新写在 `onHiddenChanged(hidden == false)` 往往比死靠 `onResume` 靠谱（ERR-001）。
- 类比：租客（实例）还在户籍上，房子（view）可能已拆掉重建——别往旧门牌塞信。

**易错：**
- 以为每次切 Tab 都会 `onResume` 到你期望的刷新点。
- view 已销毁后还 `requireView()` / 改控件会崩（空安全见 FG-001,7）。
- 把 hide 当成 `onPause`：被藏着的仍可能已经 `onResume` 过。

**相关：**
- Demo：[fg-d4-view-lifecycle](../demos/fragment/fg-d4-view-lifecycle/)
- [FG-001 | Fragment](<./FG-001 | Fragment.md>)
- [FG-001,2 | DialogFragment](<./FG-001,2 | DialogFragment.md>) — 旋转后 `onCreate restored=true` 再 `onCreateDialog`
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 弹出 Detail 时走销毁
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — replace 拆 view；hide 只关灯
- [FG-001,7 | viewLifecycleOwner](<./FG-001,7 | viewLifecycleOwner.md>) — 观察绑谁、view 何时空
- [FG-001,11 | ViewPager2与Fragment](<./FG-001,11 | ViewPager2与Fragment.md>) — 离屏拆 view 的滑动多页
- [ERR-001 | 开发踩坑记录](<./ERR-001 | 开发踩坑记录.md>)
- [FG面试题库](./FG面试题库.md) — F2

**参考：**
- [Fragment 生命周期](https://developer.android.com/guide/fragments/lifecycle?hl=zh-cn)
