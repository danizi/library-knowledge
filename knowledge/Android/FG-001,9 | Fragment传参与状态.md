# Fragment传参与状态

**问：**
给 Fragment 传参为啥不用带参构造器？旋转后 id 靠什么还在？`setRetainInstance` 还能用吗？

**答：**
系统恢复（旋转、杀进程后再建）会 **空构造 new 一个 Fragment，再灌 Bundle**。你写 `DetailFragment(id)`，那次构造里的字段系统找不到，id 就丢了。  
正确做法：`DetailFragment()` + `arguments = bundleOf(...)`（常包成 `newInstance`）。`arguments` 跟隔断状态一起存，旋转后新实例还能 `requireArguments()` 读回。  
身份类轻量参数走 arguments；页内持续业务态仍放 ViewModel。**`setRetainInstance` 已过时**，别用、别深挖。

一句话：传参用空构造 + arguments；旋转靠 Bundle；别碰 setRetainInstance。

**例 / 类比：**
- 例子（`fg-d7-fragment-args`）：`newInstance("book-42")` 打开 Detail；旋转后实例 hash 变了，`restored=true`，界面上 id 仍是 `book-42`。
- 类比：旅馆按空房间重建，行李条（arguments）贴在门上；别指望「上次那个带行李进门的人」还在。

**易错：**
- 带参构造器当传参 → 配置变更后字段空。
- 大对象、列表塞进 arguments → Bundle 膨胀，该走磁盘 / ViewModel。
- 把 arguments 和 Result API、共享 ViewModel 混成一件事（打开带身份证 ≠ 回传签收单 ≠ 持续信箱）。
- 还提 `setRetainInstance` 当保存状态方案。

**相关：**
- Demo：[fg-d7-fragment-args](../demos/fragment/fg-d7-fragment-args/)
- [ACT-001,3 | 配置变更与状态](<./ACT-001,3 | 配置变更与状态.md>) — 旋转毁实例；轻量靠 Bundle
- [FG-001,4 | Fragment通信](<./FG-001,4 | Fragment通信.md>) — 通信：VM / Result；本卡是打开时带参
- [FG-001 | Fragment](<./FG-001 | Fragment.md>) — 空构造是恢复前提
- [FG面试题库](./FG面试题库.md) — F12
- [FG错题库](./FG错题库.md)

**参考：**
- [向 Fragment 传递数据](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn#pass-data)
