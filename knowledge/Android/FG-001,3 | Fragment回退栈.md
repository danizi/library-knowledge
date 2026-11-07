# Fragment回退栈

**问：** 系统返回键先弹出 Fragment 回退栈，还是先结束 Activity？

> **一句话：** 回退栈不空时，系统先弹出 Fragment。回退栈空时，系统结束 Activity。`addToBackStack` 把这次事务记入回退栈。

**例：** `fg-d1-backstack` 从 Home 进入 Detail。按系统返回后回到 Home。宿主 hash 不变。Logcat 标签 `HOST` 只有 Detail 的 `onDestroy`。没有 `MainActivity onDestroy`。`act-d5-host-fragment` 没有调用 `addToBackStack`。同样的返回操作直接退出应用。

<details>
<summary>答</summary>

系统返回先检查这个 Activity 里的 Fragment 回退栈。
回退栈不空时，系统弹出上一层 Fragment。
回退栈空时，系统对 Activity 调用 `finish`。
`replace` 时调用 `addToBackStack`。系统把替换之前的状态记入回退栈。
不调用 `addToBackStack` 时，回退栈是空的。
回退栈是空的时，返回结束 Activity。
Fragment 回退栈和 Activity 任务栈不是同一层。
任务栈保存多个 Activity。
Fragment 回退栈保存同一个 Activity 里的 Fragment 历史。
第一次放入的根 Fragment 不要调用 `addToBackStack`。
根 Fragment 入栈后再弹出，容器会变空。应用仍会退出。

</details>

<details>
<summary>类比</summary>

先撤掉房间里的隔板。隔板撤完，才关闭整栋楼。

</details>

<details>
<summary>易错</summary>

- 不要把 Fragment 回退栈和 `singleTask` 当成同一层。不要把它和任务栈当成同一层。
- 不要给根页面调用 `addToBackStack`。返回会弹出根 Fragment。容器变空，或者应用退出。
- 不要在同一笔事务里同时调用 `addToBackStack` 和 `runOnCommit`。系统抛出 `IllegalStateException`。
- 点击「Home」时，不要只调用 `replace` 而不调用 `popBackStack`。回退栈会继续堆积。
- 系统返回不会自动修改 ViewModel 里的文案。`fg-d1` 的 ViewModel 可能仍保留 Detail 的文字。回退栈没有丢失。

</details>

<details>
<summary>相关</summary>

- Demo：[fg-d1-backstack](../demos/fragment/fg-d1-backstack/) — 这次事务入栈。对照 [act-d5-host-fragment](../demos/activity/act-d5-host-fragment/)：没有入栈时，返回会退出
- [FG-001,5 | Fragment与Activity关联](<./FG-001,5 | Fragment与Activity关联.md>) — 更换 Fragment 时 Activity 还在。那张卡的 Demo 没有入栈
- [ACT-001,4 | 启动模式与任务栈](<./ACT-001,4 | 启动模式与任务栈.md>) — 那一层是 Activity 任务栈
- [FG-001,1 | Fragment生命周期](<./FG-001,1 | Fragment生命周期.md>) — 弹出时 Detail 走销毁
- [FG-001,2 | DialogFragment](<./FG-001,2 | DialogFragment.md>) — 对话框默认也占用一层返回
- [FG-001,6 | Fragment事务](<./FG-001,6 | Fragment事务.md>) — 三种切换。`runOnCommit` 不能和回退栈写在同一笔
- [FG-001,8 | 嵌套Fragment](<./FG-001,8 | 嵌套Fragment.md>) — 子回退栈加上 `setPrimaryNavigationFragment`。返回先弹子层
- [FG-001,10 | Navigation与Fragment](<./FG-001,10 | Navigation与Fragment.md>) — Navigation 管理回退栈。规则仍是先弹出 Fragment
- [FG面试题库](./FG面试题库.md) — F6
- [FG错题库](./FG错题库.md) — F6

</details>

<details>
<summary>参考</summary>

- [Fragment 回退栈](https://developer.android.com/guide/fragments/fragmentmanager?hl=zh-cn#back-stack)

</details>
