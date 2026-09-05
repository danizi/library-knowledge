# 嵌套 Fragment

**问：**
`childFragmentManager`、`parentFragmentManager`、`supportFragmentManager` 各管哪一层？嵌套时系统返回为啥先弹子页？

**答：**
Activity 用 **`supportFragmentManager`** 装第一层隔断（本 Demo 的 Parent）。父隔断里再塞子隔断，必须用父的 **`childFragmentManager`**——那是父自己的小册子，不是 Activity 那本。  
子隔断上的 **`parentFragmentManager`**，指的就是「管我的那本」，嵌套时它等于父的 childFM；别和 Activity 的 support 混叫「parent」。  
子页入栈（`addToBackStack`）后，还要把父设成 **`setPrimaryNavigationFragment`**，系统返回才先问这本主导航里的**子栈**；子栈空了，才轮到 Activity / 退出。

一句话：嵌套用 child；和宿主同级用 support；返回先弹子栈要靠入栈 + primaryNavigation。

**例 / 类比：**
- 例子（`fg-d6-nested-fragment`）：Activity 装 Parent 并设 primary；Parent 用 childFM 装 ChildA（根，不入子栈），再 replace ChildB + `addToBackStack`。系统返回回到 A；Parent / MainActivity hash 不变。
- 类比：大楼前台一本总册（support）；套房前台一本分册（child）；返回先撤套房里的凳，再才关大楼。

**易错：**
- 嵌套子页却用 Activity 的 `supportFragmentManager` → 子页挂错容器，返回顺序乱。
- 只入子栈、不设 `setPrimaryNavigationFragment` → 系统返回可能直接 finish Activity，跳过子栈。
- 把 `parentFragmentManager` 当成「Activity 那本」；嵌套时它是父的 childFM。
- 根子页也 `addToBackStack` → 弹出后父容器空了。
- 和 fg-d1 的「Activity 册上 Home↔Detail」混成一件事：规则一样，只是册子换到 Parent 上。

**相关：**
- Demo：[fg-d6-nested-fragment](../demos/fragment/fg-d6-nested-fragment/)
- [FG-001,3 | Fragment回退栈](<./FG-001,3 | Fragment回退栈.md>) — 入栈 / 返回先弹隔断；本卡是嵌套那一层
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 宿主用 support 装第一层
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — replace + commit 写法相同
- [FG面试题库](./FG面试题库.md) — F11
- [FG错题库](./FG错题库.md)

**参考：**
- [FragmentManager 与嵌套](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn#child-fragmentmanager)
