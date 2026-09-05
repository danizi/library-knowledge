# Fragment回退栈

**问：**
系统返回键先弹谁？`addToBackStack` 干什么？为啥 act-d5 一按返回就退出 App？

**答：**
系统返回先问这扇窗里的 **Fragment 回退栈**：不空就弹出上一层隔断；空了才 `finish` 整栋 Activity。  
`replace` 时加上 `addToBackStack`，等于把「盖上去之前的样子」压进这层栈。没加，栈是空的，返回只能拆大楼。  
这和 Activity **任务栈**不是一层：任务栈是多扇系统窗口；回退栈是同一扇窗里的隔断史。根隔断（第一次 Home）不要入栈，否则弹出 Home 容器空了，还是退出。

一句话：返回先拆隔断，栈空了才拆楼；入栈靠 `addToBackStack`。

**例 / 类比：**
- 例子（`fg-d1-backstack`）：Home → Detail → 系统返回，回到 Home，宿主 hash 不变；Logcat `HOST` 只有 Detail `onDestroy`，没有 `MainActivity onDestroy`。act-d5 没入栈，同样操作直接退出。
- 类比：房间里的撤板凳（回退栈）撤完，才轮到整栋楼关门（任务栈）。

**易错：**
- 和 `singleTask` / 任务栈混为一谈。
- 根页面也 `addToBackStack` → 返回弹出根，一片空白或退出。
- `addToBackStack` 和 `runOnCommit` 写在同一笔事务会崩（`IllegalStateException`）。
- 点「Home」再 `replace` 而不 `popBackStack`，栈会越积越乱。
- 系统返回不会自动改你的 ViewModel 文案（fg-d1 信箱可能还写着 Detail）——不是栈丢了。

**相关：**
- Demo：[fg-d1-backstack](../demos/fragment/fg-d1-backstack/) — 入栈；对照 [act-d5-host-fragment](../demos/activity/act-d5-host-fragment/) 没入栈会退出
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 换隔断，楼还在；那张卡的 Demo 没入栈
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 系统窗口那一层栈
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 弹出时 Detail 走销毁
- [FG-001,2 | DialogFragment](<./FG-001,2 | DialogFragment.md>) — 对话框默认也占一层返回
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — 三种切法；`runOnCommit` 不能配回退栈
- [FG-001,8 | 嵌套Fragment](<./FG-001,8 | 嵌套Fragment.md>) — 子栈 + primaryNavigation，返回先弹子层
- [FG面试题库](./FG面试题库.md) — F6
- [FG错题库](./FG错题库.md) — F6

**参考：**
- [Fragment 回退栈](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn#back-stack)
